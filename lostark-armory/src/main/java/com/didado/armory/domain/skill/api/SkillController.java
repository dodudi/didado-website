package com.didado.armory.domain.skill.api;

import com.didado.armory.domain.card.dto.ArmoryCardParameter;
import com.didado.armory.domain.core.domain.ArmoryType;
import com.didado.armory.domain.core.dto.CoreSaveParameter;
import com.didado.armory.domain.skill.application.SkillService;
import com.didado.armory.domain.skill.domain.SkillInfo;
import com.didado.armory.domain.skill.dto.ArmorySkillParameter;
import com.didado.armory.domain.skill.dto.SkillInfoParameter;
import com.didado.armory.domain.skill.exception.NotFoundArmorySkillException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @GetMapping("/lostark/armory/{characterName}/skills")
    public ResponseEntity<SkillInfoParameter> skills(@PathVariable String characterName) {
        return ResponseEntity.ok(skillService.search(characterName));
    }


    @ExceptionHandler(NotFoundArmorySkillException.class)
    public ResponseEntity<SkillInfoParameter> skill(NotFoundArmorySkillException e) {
        log.error("{}", e, e);
        CoreSaveParameter saveParameter = new CoreSaveParameter(ArmoryType.SKILL, e.getCharacterName());
        SkillInfoParameter saveData = WebClient.create()
                .post()
                .uri("http://localhost:8080/lostark/armory/core")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(saveParameter)
                .retrieve()
                .bodyToMono(SkillInfoParameter.class)
                .block();

        log.debug("Save Data={}", saveData);
        return ResponseEntity.ok(saveData);
    }


}
