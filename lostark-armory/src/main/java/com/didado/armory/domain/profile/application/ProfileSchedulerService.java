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
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileSchedulerService {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    private final ArmoryProfileRepository armoryProfileRepository;
    private final ArmoryStatRepository armoryStatRepository;
    private final ArmoryTendencyRepository armoryTendencyRepository;

    public void search(String characterName) {
        ArmoryProfileParameter newParameter = getProfile(characterName);
        List<StatParameter> newStats = newParameter.getStats();
        List<TendencyParameter> newTendencies = newParameter.getTendencies();

        ArmoryProfile armoryProfile = armoryProfileRepository.findByCharacterName(characterName)
                .orElseGet(() -> null);

        if (armoryProfile == null) {
            log.debug("Armory Profile Not Exist {}", characterName);
            ArmoryProfile convertProfile = newParameter.toArmoryProfile();
            armoryProfileRepository.save(convertProfile);

            List<Stat> convertStats = newStats.stream()
                    .map(statParameter -> statParameter.toStat())
                    .map(stat -> stat.changeArmoryProfile(convertProfile))
                    .toList();

            armoryStatRepository.saveAll(convertStats);

            List<Tendency> convertTendencies = newTendencies.stream()
                    .map(TendencyParameter::toTendency)
                    .map(tendency -> tendency.updateArmoryProfile(convertProfile))
                    .toList();
            armoryTendencyRepository.saveAll(convertTendencies);
        } else {
            log.debug("Armory Profile Exist {}", characterName);
//            armoryProfile.getStats().clear();
//            armoryProfile.getTendencies().clear();

            Long profileId = armoryProfile.getId();

            List<Stat> oldStats = armoryStatRepository.findStatByProfileId(profileId);
            List<Stat> updateStats = oldStats.stream()
                    .peek(stat -> newStats.stream()
                            .filter(statParameter -> statParameter.getType().equals(stat.getType()))
                            .findFirst()
                            .ifPresent(stat::updateData)).toList();
            armoryStatRepository.saveAll(updateStats);


            List<Tendency> oldTendencies = armoryTendencyRepository.findTendencyByProfileId(profileId);
            List<Tendency> updateTendencies = oldTendencies.stream()
                    .peek(tendency -> newTendencies.stream()
                            .filter(tendencyParameter -> tendencyParameter.getType().equals(tendency.getType()))
                            .findFirst()
                            .ifPresent(tendency::updateData)
                    ).toList();
            armoryTendencyRepository.saveAll(updateTendencies);

            log.debug("Update Stats={}", updateStats);
            log.debug("Update Tendencies={}", updateTendencies);
        }
    }

    public ArmoryProfileParameter getProfile(String username) {
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
