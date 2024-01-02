package com.didado.armory.domain.profile.application;


import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.dto.armory.*;
import com.didado.armory.domain.profile.domain.ArmoryProfile;
import com.didado.armory.domain.profile.domain.Stat;
import com.didado.armory.domain.profile.domain.Tendency;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
import com.didado.armory.domain.profile.dto.StatParameter;
import com.didado.armory.domain.profile.dto.TendencyParameter;
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
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl {
    private final ArmoryProfileRepository armoryProfileRepository;
    private final ArmoryStatRepository armoryStatRepository;
    private final ArmoryTendencyRepository armoryTendencyRepository;

    public ArmoryProfileParameter search(String characterName) {
        ArmoryProfile armoryProfiles = armoryProfileRepository.findByCharacterName(characterName)
                .orElseThrow(() -> new NotFoundProfileException("존재하는 캐릭터명이 아닙니다.", characterName));

        List<Stat> stats = armoryStatRepository.findStatByProfileId(armoryProfiles.getId());
        List<StatParameter> convertStats = stats.stream()
                .map(StatParameter::new)
                .toList();


        List<Tendency> tendencies = armoryTendencyRepository.findTendencyByProfileId(armoryProfiles.getId());
        List<TendencyParameter> convertTendencies = tendencies.stream()
                .map(TendencyParameter::new)
                .toList();

        ArmoryProfileParameter armoryProfileParameter = new ArmoryProfileParameter(armoryProfiles);
        armoryProfileParameter.changeStats(convertStats);
        armoryProfileParameter.changeTendencies(convertTendencies);
        return armoryProfileParameter;
    }

    public List<StatParameter> searchStats(String characterName) {
        ArmoryProfile armoryProfiles = armoryProfileRepository.findByCharacterName(characterName)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 캐릭터명이 아닙니다."));

        List<Stat> stats = armoryStatRepository.findStatByProfileId(armoryProfiles.getId());
        return stats.stream()
                .map(StatParameter::new)
                .toList();
    }

    public List<TendencyParameter> searchTendencies(String characterName) {
        ArmoryProfile armoryProfiles = armoryProfileRepository.findByCharacterName(characterName)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 캐릭터명이 아닙니다."));

        List<Tendency> tendencies = armoryTendencyRepository.findTendencyByProfileId(armoryProfiles.getId());
        return tendencies.stream()
                .map(TendencyParameter::new)
                .toList();
    }


//
//

//

//

//

//

//

//

//

}
