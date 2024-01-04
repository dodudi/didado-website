package com.didado.armory.domain.collectible.application;

import com.didado.armory.domain.collectible.domain.Collectible;
import com.didado.armory.domain.collectible.domain.CollectibleInfo;
import com.didado.armory.domain.collectible.domain.CollectiblePoint;
import com.didado.armory.domain.collectible.dto.CollectibleInfoParameter;
import com.didado.armory.domain.collectible.dto.CollectibleParameter;
import com.didado.armory.domain.collectible.dto.CollectiblePointParameter;
import com.didado.armory.domain.collectible.exception.NotFoundCollectibleInfoException;
import com.didado.armory.domain.collectible.repository.CollectibleInfoRepository;
import com.didado.armory.domain.collectible.repository.CollectiblePointRepository;
import com.didado.armory.domain.collectible.repository.CollectibleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectibleService {
    private final CollectibleInfoRepository collectibleInfoRepository;
    private final CollectibleRepository collectibleRepository;
    private final CollectiblePointRepository collectiblePointRepository;

    public CollectibleInfoParameter search(String characterName) {
        CollectibleInfo collectibleInfo = collectibleInfoRepository.findByCharacterName(characterName)
                .orElseThrow(() -> new NotFoundCollectibleInfoException("존재 하지 않는 캐릭터 이름입니다.", characterName));

        List<Collectible> collectibles = collectibleRepository.findByCollectibleInfoId(collectibleInfo.getId());
        Map<Long, List<CollectiblePoint>> collectiblePointToMap = collectibles.stream()
                .flatMap(collectible -> collectiblePointRepository.findByCollectibleId(collectible.getId()).stream())
                .collect(Collectors.groupingBy(collectiblePoint -> collectiblePoint.getCollectible().getId()));

        List<CollectibleParameter> collectibleParameters = collectibles.stream()
                .map(collectible -> {
                    List<CollectiblePoint> collectiblePoints = collectiblePointToMap.get(collectible.getId());
                    List<CollectiblePointParameter> convertCollectiblePointParameters = collectiblePoints.stream()
                            .map(CollectiblePointParameter::new)
                            .toList();

                    CollectibleParameter collectibleParameter = new CollectibleParameter(collectible);
                    collectibleParameter.getCollectiblePoints().addAll(convertCollectiblePointParameters);
                    return collectibleParameter;
                }).toList();

        return new CollectibleInfoParameter(characterName, collectibleParameters);
    }
}
