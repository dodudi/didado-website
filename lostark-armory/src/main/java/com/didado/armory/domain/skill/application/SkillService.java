package com.didado.armory.domain.skill.application;

import com.didado.armory.domain.skill.domain.ArmorySkill;
import com.didado.armory.domain.skill.domain.SkillInfo;
import com.didado.armory.domain.skill.domain.SkillRune;
import com.didado.armory.domain.skill.domain.SkillTripod;
import com.didado.armory.domain.skill.dto.ArmorySkillParameter;
import com.didado.armory.domain.skill.dto.SkillInfoParameter;
import com.didado.armory.domain.skill.dto.SkillRuneParameter;
import com.didado.armory.domain.skill.dto.SkillTripodParameter;
import com.didado.armory.domain.skill.exception.NotFoundArmorySkillException;
import com.didado.armory.domain.skill.repository.ArmorySkillRepository;
import com.didado.armory.domain.skill.repository.SkillInfoRepository;
import com.didado.armory.domain.skill.repository.SkillRuneRepository;
import com.didado.armory.domain.skill.repository.SkillTripodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillInfoRepository skillInfoRepository;
    private final ArmorySkillRepository armorySkillRepository;
    private final SkillRuneRepository skillRuneRepository;
    private final SkillTripodRepository skillTripodRepository;


    public SkillInfoParameter search(String characterName) {
        SkillInfo skillInfo = skillInfoRepository.findByCharacterName(characterName)
                .orElseThrow(() -> new NotFoundArmorySkillException("존재 하지 않는 캐릭터 이름입니다.", characterName));

        SkillInfoParameter skillInfoParameter = new SkillInfoParameter(characterName);

        List<ArmorySkill> armorySkills = armorySkillRepository.findBySkillInfoId(skillInfo.getId());

        armorySkills.forEach(armorySkill -> {
            ArmorySkillParameter armorySkillParameter = new ArmorySkillParameter(armorySkill);

            SkillRune skillRune = skillRuneRepository.findByArmorySkillId(armorySkill.getId())
                    .orElseThrow(() -> new NotFoundArmorySkillException("존재 하지 않는 룬입니다.", characterName));
            SkillRuneParameter convertSkillRune = new SkillRuneParameter(skillRune);
            armorySkillParameter.changeSkillRuneParameter(convertSkillRune);

            List<SkillTripod> skillTripods = skillTripodRepository.findByArmorySkillId(armorySkill.getId());
            List<SkillTripodParameter> convertTripods = skillTripods.stream()
                    .map(SkillTripodParameter::new)
                    .toList();

            armorySkillParameter.getTripods().addAll(convertTripods);

            skillInfoParameter.getArmorySkills().add(armorySkillParameter);
        });

        return skillInfoParameter;
    }


}
