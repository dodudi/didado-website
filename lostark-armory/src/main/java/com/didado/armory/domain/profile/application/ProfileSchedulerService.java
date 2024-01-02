package com.didado.armory.domain.profile.application;

import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.profile.domain.ArmoryProfile;
import com.didado.armory.domain.profile.domain.Stat;
import com.didado.armory.domain.profile.domain.Tendency;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
import com.didado.armory.domain.profile.dto.StatParameter;
import com.didado.armory.domain.profile.dto.TendencyParameter;
import com.didado.armory.domain.profile.repository.ArmoryProfileRepository;
import com.didado.armory.domain.profile.repository.ArmoryStatRepository;
import com.didado.armory.domain.profile.repository.ArmoryTendencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileSchedulerService {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    private final ArmoryProfileRepository armoryProfileRepository;
    private final ArmoryStatRepository armoryStatRepository;
    private final ArmoryTendencyRepository armoryTendencyRepository;

    public void save(ArmoryProfileParameter armoryProfileParameter) {
        ArmoryProfile armoryProfile = armoryProfileRepository.findByCharacterName(armoryProfileParameter.getCharacterName())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 캐릭터 이름입니다."));
        List<Stat> oldStats = armoryStatRepository.findStatByProfileId(armoryProfile.getId());
        List<Tendency> oldTendencies = armoryTendencyRepository.findTendencyByProfileId(armoryProfile.getId());

        ArmoryProfile convertProfile = convertProfile(armoryProfileParameter);

        //Stat Update
        Map<String, Stat> statMap = convertProfile.getStats().stream()
                .collect(Collectors.toMap(Stat::getType, stat -> stat));
        oldStats.forEach(
                stat -> {
                    if (statMap.containsKey(stat.getType())) {
                        Stat newStat = statMap.get(stat.getType());
                        stat.changeData(newStat);
                    }
                }
        );

        //Tendency Update
        Map<String, Tendency> tendencyMap = convertProfile.getTendencies().stream()
                .collect(Collectors.toMap(Tendency::getType, tendency -> tendency));
        oldTendencies.forEach(
                tendency -> {
                    if (tendencyMap.containsKey(tendency.getType())) {
                        Tendency newTendency = tendencyMap.get(tendency.getType());
                        tendency.changeData(newTendency);
                    }
                }
        );

        armoryProfile.changeData(armoryProfile);
    }

    private ArmoryProfile convertProfile(ArmoryProfileParameter armoryProfileParameter) {
        List<StatParameter> stats = armoryProfileParameter.getStats();
        List<TendencyParameter> tendencies = armoryProfileParameter.getTendencies();

        List<Stat> convertStats = convertStats(stats);
        List<Tendency> convertTendencies = convertTendencies(tendencies);

        ArmoryProfile armoryProfile = armoryProfileParameter.toArmoryProfile();

        convertStats.forEach(stat -> stat.changeArmoryProfile(armoryProfile));
        convertTendencies.forEach(tendency -> tendency.changeArmoryProfile(armoryProfile));
        return armoryProfile;
    }

    private List<Stat> convertStats(List<StatParameter> statParameters) {
        return statParameters.stream()
                .map(StatParameter::toStat)
                .toList();
    }

    private List<Tendency> convertTendencies(List<TendencyParameter> tendencies) {
        return tendencies.stream()
                .map(TendencyParameter::toTendency)
                .toList();
    }

    private ArmoryProfileParameter getProfile(String username) {
        String url = property.url() + "/armories/characters/" + username + "/profiles";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ArmoryProfileParameter> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return Optional.ofNullable(response.getBody())
                .orElseThrow(() -> new IllegalArgumentException(username + "의 Profile 데이터가 존재하지 않는다."));
    }
}
