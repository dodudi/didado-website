package com.didado.armory.domain.skill.application;

import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.skill.domain.ArmorySkill;
import com.didado.armory.domain.skill.domain.SkillRune;
import com.didado.armory.domain.skill.domain.SkillTripod;
import com.didado.armory.domain.skill.dto.ArmorySkillParameter;
import com.didado.armory.domain.skill.dto.SkillRuneParameter;
import com.didado.armory.domain.skill.dto.SkillTripodParameter;
import com.didado.armory.domain.skill.repository.ArmorySkillRepository;
import com.didado.armory.domain.skill.repository.SkillRuneRepository;
import com.didado.armory.domain.skill.repository.SkillTripodRepository;
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
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillSchedulerService {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    private final ArmorySkillRepository armorySkillRepository;
    private final SkillRuneRepository skillRuneRepository;
    private final SkillTripodRepository skillTripodRepository;

    public void search(String characterName) {
//        List<ArmorySkillParameter> skillParameters = searchSkills(characterName);
//        List<ArmorySkill> skills = armorySkillRepository.findArmorySkillByCharacterName(characterName);
//
//        if (!skills.isEmpty())
//            armorySkillRepository.deleteAllById(skills.stream().map(ArmorySkill::getId).toList());
//
//        for (ArmorySkillParameter skillParameter : skillParameters) {
//            ArmorySkill armorySkill = skillParameter.toArmorySkill();
//            armorySkillRepository.save(armorySkill);
//
//            if (skillParameter.getRune() != null) {
//                SkillRuneParameter rune = skillParameter.getRune();
//                SkillRune convertSkillRune = rune.toSkillRune();
//                skillRuneRepository.save(convertSkillRune);
//            }
//
//
//            List<SkillTripodParameter> tripods = skillParameter.getTripods();
//            List<SkillTripod> convertTripods = tripods.stream().map(SkillTripodParameter::toSkillTripod)
//                    .toList();
//            skillTripodRepository.saveAll(convertTripods);
//        }
    }

    private List<ArmorySkillParameter> searchSkills(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName + "/combat-skills";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<List<ArmorySkillParameter>> response = restTemplate.exchange(
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
