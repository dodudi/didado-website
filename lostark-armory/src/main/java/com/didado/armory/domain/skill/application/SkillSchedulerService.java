package com.didado.armory.domain.skill.application;

import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.skill.domain.ArmorySkill;
import com.didado.armory.domain.skill.domain.SkillInfo;
import com.didado.armory.domain.skill.domain.SkillRune;
import com.didado.armory.domain.skill.domain.SkillTripod;
import com.didado.armory.domain.skill.dto.ArmorySkillParameter;
import com.didado.armory.domain.skill.dto.SkillRuneParameter;
import com.didado.armory.domain.skill.dto.SkillTripodParameter;
import com.didado.armory.domain.skill.exception.NotFoundArmorySkillException;
import com.didado.armory.domain.skill.repository.ArmorySkillRepository;
import com.didado.armory.domain.skill.repository.SkillInfoRepository;
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

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillSchedulerService {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    private final SkillInfoRepository skillInfoRepository;
    private final ArmorySkillRepository armorySkillRepository;
    private final SkillRuneRepository skillRuneRepository;
    private final SkillTripodRepository skillTripodRepository;

    public void search(String characterName, List<ArmorySkillParameter> armorySkillParameters) {
        if (skillInfoRepository.existsByCharacterName(characterName)) {
            //update
            SkillInfo oldSkillInfo = skillInfoRepository.findByCharacterName(characterName)
                    .orElseThrow(() -> new NotFoundArmorySkillException("존재 하지 않는 캐릭터 이름입니다.", characterName));

            //Delete Old Skill
            deleteSkillInfo(characterName, oldSkillInfo);

            //Convert Map Key -> Armory Skill Name
            Map<String, SkillRuneParameter> skillRuneMap = convertSkillRunesToMap(armorySkillParameters);

            //Convert Map Key -> Armory Skill Name
            Map<String, List<SkillTripodParameter>> tripodMap = convertSkillTripodsToMap(armorySkillParameters);

            //Convert Armory Skill
            List<ArmorySkill> convertArmorySkills = convertArmorySkills(armorySkillParameters);

            //Save
            save(convertArmorySkills, oldSkillInfo, skillRuneMap, tripodMap);
        } else {
            //Save Skill Info
            SkillInfo skillInfo = new SkillInfo(characterName);
            skillInfoRepository.save(skillInfo);

            //Convert Map Key -> Armory Skill Name
            Map<String, SkillRuneParameter> skillRuneMap = convertSkillRunesToMap(armorySkillParameters);

            //Convert Map Key -> Armory Skill Name
            Map<String, List<SkillTripodParameter>> tripodMap = convertSkillTripodsToMap(armorySkillParameters);

            //Convert Armory Skill
            List<ArmorySkill> convertArmorySkills = convertArmorySkills(armorySkillParameters);

            //Save
            save(convertArmorySkills, skillInfo, skillRuneMap, tripodMap);
        }

    }

    private void save(List<ArmorySkill> convertArmorySkill, SkillInfo skillInfo, Map<String, SkillRuneParameter> skillRuneMap, Map<String, List<SkillTripodParameter>> tripodMap) {
        convertArmorySkill.forEach(armorySkill -> {
            //Save Armory Skill
            armorySkill.changeSkillInfo(skillInfo);
            armorySkillRepository.save(armorySkill);

            //Save Skill Rune
            saveSkillRune(armorySkill, skillRuneMap);

            //Save Tripod
            saveTripods(armorySkill, tripodMap);
        });
    }

    private void saveSkillRune(ArmorySkill armorySkill, Map<String, SkillRuneParameter> skillRuneMap) {
        SkillRuneParameter skillRuneParameter = skillRuneMap.get(armorySkill.getName());
        SkillRune skillRune = skillRuneParameter.toSkillRune();
        skillRune.changeArmorySkill(armorySkill);

        skillRuneRepository.save(skillRune);
    }

    private void saveTripods(ArmorySkill armorySkill, Map<String, List<SkillTripodParameter>> tripodMap) {
        List<SkillTripodParameter> skillTripodParameters = tripodMap.get(armorySkill.getName());

        //Convert Tripod
        List<SkillTripod> convertTripods = skillTripodParameters.stream()
                .map(skillTripodParameter -> {
                    SkillTripod skillTripod = skillTripodParameter.toSkillTripod();
                    skillTripod.changeArmorySkill(armorySkill);
                    return skillTripod;
                })
                .toList();

        skillTripodRepository.saveAll(convertTripods);
    }

    private static List<ArmorySkill> convertArmorySkills(List<ArmorySkillParameter> armorySkillParameters) {
        return armorySkillParameters.stream()
                .map(ArmorySkillParameter::toArmorySkill)
                .toList();
    }

    private static Map<String, List<SkillTripodParameter>> convertSkillTripodsToMap(List<ArmorySkillParameter> armorySkillParameters) {
        return armorySkillParameters.stream()
                .collect(Collectors.toMap(ArmorySkillParameter::getName, ArmorySkillParameter::getTripods));
    }

    private static Map<String, SkillRuneParameter> convertSkillRunesToMap(List<ArmorySkillParameter> armorySkillParameters) {
        return armorySkillParameters.stream()
                .collect(Collectors.toMap(ArmorySkillParameter::getName, skillParameter -> {
                    return Optional.ofNullable(skillParameter.getRune()).orElseGet(() -> new SkillRuneParameter(null));
                }));
    }

    private void deleteSkillInfo(String characterName, SkillInfo oldSkillInfo) {
        List<ArmorySkill> oldArmorySkill = armorySkillRepository.findBySkillInfoId(oldSkillInfo.getId());
        oldArmorySkill.forEach(armorySkill -> {
            SkillRune oldSkillRune = skillRuneRepository.findByArmorySkillId(armorySkill.getId())
                    .orElseThrow(() -> new NotFoundArmorySkillException("존재 하지 않는 스킬 정보 입니다.", characterName));
            oldSkillRune.deleteArmorySkill();
            skillRuneRepository.delete(oldSkillRune);

            List<SkillTripod> oldSkillTripods = skillTripodRepository.findByArmorySkillId(armorySkill.getId());
            oldSkillTripods.forEach(SkillTripod::deleteArmorySkill);
            skillTripodRepository.deleteAll(oldSkillTripods);

            armorySkill.deleteSkillInfo();
            armorySkillRepository.delete(armorySkill);
        });
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
