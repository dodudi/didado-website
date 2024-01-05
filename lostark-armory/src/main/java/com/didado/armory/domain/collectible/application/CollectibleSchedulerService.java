package com.didado.armory.domain.collectible.application;

import com.didado.armory.domain.collectible.domain.Collectible;
import com.didado.armory.domain.collectible.domain.CollectibleInfo;
import com.didado.armory.domain.collectible.domain.CollectiblePoint;
import com.didado.armory.domain.collectible.dto.CollectibleParameter;
import com.didado.armory.domain.collectible.dto.CollectiblePointParameter;
import com.didado.armory.domain.collectible.exception.NotFoundCollectibleInfoException;
import com.didado.armory.domain.collectible.repository.CollectibleInfoRepository;
import com.didado.armory.domain.collectible.repository.CollectiblePointRepository;
import com.didado.armory.domain.collectible.repository.CollectibleRepository;
import com.didado.armory.domain.dto.LostarkProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectibleSchedulerService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    private final CollectibleInfoRepository collectibleInfoRepository;
    private final CollectibleRepository collectibleRepository;
    private final CollectiblePointRepository collectiblePointRepository;

    public void save(String characterName, List<CollectibleParameter> collectibleParameters) {
        if (collectibleInfoRepository.existsByCharacterName(characterName)) {
            //update
            CollectibleInfo oldInfo = collectibleInfoRepository.findByCharacterName(characterName)
                    .orElseThrow(() -> new NotFoundCollectibleInfoException("존재 하지 않는 캐릭터 이름입니다.", characterName));

            //Find Old Collectibles
            List<Collectible> oldCollectibles = collectibleRepository.findByCollectibleInfoId(oldInfo.getId());

            //Convert New Collectible To Map(Key = type)
            Map<String, Collectible> convertCollectibleToMap = convertCollectibleParameterToMap(collectibleParameters);

            //Convert New CollectiblePoint To Map(Key = pointName)
            Map<String, CollectiblePoint> convertCollectPointToMap = convertCollectiblePointToMap(collectibleParameters);

            //Update Old Collectible Point
            oldCollectibles.forEach(collectible -> {
                updateCollectiblePoint(collectible, convertCollectPointToMap);
                updateCollectible(collectible, convertCollectibleToMap);
            });
        } else {
            //save
            CollectibleInfo collectibleInfo = new CollectibleInfo(characterName);
            collectibleInfoRepository.save(collectibleInfo);

            //Convert New Collectible To Map(Key = type)
            collectibleParameters.forEach(collectibleParameter -> {
                //Save Collectible
                Collectible convertCollectible = collectibleParameter.toCollectible();
                convertCollectible.changeCollectibleInfo(collectibleInfo);
                collectibleRepository.save(convertCollectible);

                //Save Collectible Point
                saveCollectiblePoint(collectibleParameter, convertCollectible);
            });
        }
    }

    private void saveCollectiblePoint(CollectibleParameter collectibleParameter, Collectible convertCollectible) {
        List<CollectiblePointParameter> collectiblePoints = collectibleParameter.getCollectiblePoints();

        //CollectiblePoint Convert
        List<CollectiblePoint> convertCollectiblePoints = collectiblePoints.stream()
                .map(collectiblePointParameter -> {
                    CollectiblePoint collectiblePoint = collectiblePointParameter.toCollectiblePoint();
                    collectiblePoint.changeCollectible(convertCollectible);
                    return collectiblePoint;
                })
                .toList();

        collectiblePointRepository.saveAll(convertCollectiblePoints);
    }

    private void updateCollectible(Collectible collectible, Map<String, Collectible> convertCollectibleToMap) {
        if (convertCollectibleToMap.containsKey(collectible.getType())) {
            collectible.changeData(convertCollectibleToMap.get(collectible.getType()));
        } else {
            collectible.deleteCollectibleInfo();
            collectibleRepository.delete(collectible);
        }
    }

    private void updateCollectiblePoint(Collectible collectible, Map<String, CollectiblePoint> convertCollectPointToMap) {
        List<CollectiblePoint> oldCollectiblePoints = collectiblePointRepository.findByCollectibleId(collectible.getId());
        oldCollectiblePoints.forEach(oldCollectiblePoint -> {
            if (convertCollectPointToMap.containsKey(oldCollectiblePoint.getPointName())) {
                oldCollectiblePoint.changeData(convertCollectPointToMap.get(oldCollectiblePoint.getPointName()));
            } else {
                oldCollectiblePoint.deleteCollectible();
                collectiblePointRepository.delete(oldCollectiblePoint);
            }
        });
    }

    private static Map<String, CollectiblePoint> convertCollectiblePointToMap(List<CollectibleParameter> collectibleParameters) {
        return collectibleParameters.stream()
                .flatMap(collectibleParameter -> collectibleParameter.getCollectiblePoints().stream())
                .collect(Collectors.toMap(CollectiblePointParameter::getPointName, CollectiblePointParameter::toCollectiblePoint));
    }

    private static Map<String, Collectible> convertCollectibleParameterToMap(List<CollectibleParameter> collectibleParameters) {
        return collectibleParameters.stream()
                .collect(Collectors.toMap(CollectibleParameter::getType, CollectibleParameter::toCollectible));
    }

    private List<CollectibleParameter> getCollectibleParameter(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName + "/collectibles";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<List<CollectibleParameter>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return Optional.ofNullable(response.getBody())
                .orElseGet(Collections::emptyList);
    }
}
