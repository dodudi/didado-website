package com.didado.armory.domain.skill.application;

import com.didado.armory.domain.skill.domain.ArmorySkill;
import com.didado.armory.domain.skill.dto.ArmorySkillParameter;
import com.didado.armory.domain.skill.repository.ArmorySkillRepository;
import com.didado.armory.domain.skill.repository.SkillRuneRepository;
import com.didado.armory.domain.skill.repository.SkillTripodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillService {

    private final ArmorySkillRepository armorySkillRepository;
    private final SkillRuneRepository skillRuneRepository;
    private final SkillTripodRepository skillTripodRepository;


    public List<ArmorySkillParameter> search(String characterName) {
//        List<ArmorySkill> skills = armorySkillRepository.findArmorySkillByCharacterName(characterName);
//        return skills.stream().map(ArmorySkillParameter::new)
//                .toList();
        return null;
    }


}
