package com.didado.armory.domain.skill.api;

import com.didado.armory.domain.skill.application.SkillService;
import com.didado.armory.domain.skill.dto.ArmorySkillParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @GetMapping("/lostark/armory/{characterName}/skills")
    public ResponseEntity<List<ArmorySkillParameter>> skills(@PathVariable String characterName) {
        return ResponseEntity.ok(skillService.search(characterName));
    }

}
