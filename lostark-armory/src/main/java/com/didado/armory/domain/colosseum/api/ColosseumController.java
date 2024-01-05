package com.didado.armory.domain.colosseum.api;

import com.didado.armory.domain.colosseum.application.ColosseumSchedulerService;
import com.didado.armory.domain.colosseum.application.ColosseumService;
import com.didado.armory.domain.colosseum.dto.ColosseumInfoParameter;
import com.didado.armory.domain.colosseum.exception.NotFoundColosseumInfoException;
import com.didado.armory.domain.core.domain.ArmoryType;
import com.didado.armory.domain.core.dto.CoreSaveParameter;
import com.didado.armory.domain.skill.dto.SkillInfoParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequiredArgsConstructor
public class ColosseumController {
    private final ColosseumService colosseumService;

    @GetMapping("/lostark/armory/{characterName}/colosseum")
    public ResponseEntity<ColosseumInfoParameter> colosseum(@PathVariable String characterName) {
        return ResponseEntity.ok(colosseumService.search(characterName));
    }

    @ExceptionHandler(NotFoundColosseumInfoException.class)
    public ResponseEntity<ColosseumInfoParameter> handler(NotFoundColosseumInfoException e) {
        CoreSaveParameter saveParameter = new CoreSaveParameter(ArmoryType.COLOSSEUM, e.getCharacterName());
        ColosseumInfoParameter saveData = WebClient.create()
                .post()
                .uri("http://localhost:8080/lostark/armory/core")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(saveParameter)
                .retrieve()
                .bodyToMono(ColosseumInfoParameter.class)
                .block();

        return ResponseEntity.ok(saveData);
    }
}
