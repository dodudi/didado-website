package com.didado.armory.domain.profile.application;

import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.profile.domain.ArmoryProfile;
import com.didado.armory.domain.profile.domain.Stat;
import com.didado.armory.domain.profile.domain.Tendency;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
import com.didado.armory.domain.profile.dto.StatParameter;
import com.didado.armory.domain.profile.dto.TendencyParameter;
import com.didado.armory.domain.profile.exception.AlreadyExistProfileException;
import com.didado.armory.domain.profile.exception.NotFoundProfileException;
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
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public void save(ArmoryProfileParameter armoryProfileParameter) {
        if (armoryProfileRepository.existsByCharacterName(armoryProfileParameter.getCharacterName())) {
            log.debug("Profile Update Logic Start");

            //Profile Update
            update(armoryProfileParameter);
        } else {
            log.debug("Profile Save Logic Start");

            //Profile Save
            ArmoryProfile armoryProfile = convertProfile(armoryProfileParameter);
            armoryProfileRepository.save(armoryProfile);

            List<Stat> convertStats = convertStats(armoryProfileParameter.getStats());
            List<Tendency> convertTendencies = convertTendencies(armoryProfileParameter.getTendencies());

            //Stat Save
            saveStat(armoryProfile, convertStats);

            //Tendency Save
            saveTendency(armoryProfile, convertTendencies);
        }
    }

    @Transactional
    public void update(ArmoryProfileParameter armoryProfileParameter) {
        ArmoryProfile armoryProfile = armoryProfileRepository.findByCharacterName(armoryProfileParameter.getCharacterName())
                .orElseThrow(() -> new NotFoundProfileException("존재하지 않는 캐릭터 이름입니다.", armoryProfileParameter.getCharacterName()));

        ArmoryProfile convertProfile = convertProfile(armoryProfileParameter);

        //Stat Update
        updateStat(armoryProfile, convertProfile);

        //Tendency Update
        updateTendency(armoryProfile, convertProfile);

        armoryProfile.changeData(armoryProfile);
    }

    private ArmoryProfile convertProfile(ArmoryProfileParameter armoryProfileParameter) {
        return armoryProfileParameter.toArmoryProfile();
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

    private void saveStat(ArmoryProfile armoryProfile, List<Stat> convertStats) {
        convertStats.forEach(stat -> stat.changeArmoryProfile(armoryProfile));
        armoryStatRepository.saveAll(convertStats);
    }

    private void saveTendency(ArmoryProfile armoryProfile, List<Tendency> convertTendencies) {
        convertTendencies.forEach(tendency -> tendency.changeArmoryProfile(armoryProfile));
        armoryTendencyRepository.saveAll(convertTendencies);
    }

    private void updateStat(ArmoryProfile oldProfile, ArmoryProfile newProfile) {
        List<Stat> oldStats = armoryStatRepository.findStatByProfileId(oldProfile.getId());
        Map<String, Stat> statMap = newProfile.getStats().stream()
                .collect(Collectors.toMap(Stat::getType, stat -> stat));
        oldStats.forEach(
                stat -> {
                    if (statMap.containsKey(stat.getType())) {
                        Stat newStat = statMap.get(stat.getType());
                        stat.changeData(newStat);
                    }
                }
        );
    }

    private void updateTendency(ArmoryProfile armoryProfile, ArmoryProfile convertProfile) {
        List<Tendency> oldTendencies = armoryTendencyRepository.findTendencyByProfileId(armoryProfile.getId());
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
