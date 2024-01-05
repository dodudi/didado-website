package com.didado.armory.domain.card.api;

import com.didado.armory.domain.card.application.CardService;
import com.didado.armory.domain.card.dto.ArmoryCardParameter;
import com.didado.armory.domain.card.exception.NotFoundArmoryCardException;
import com.didado.armory.domain.core.domain.ArmoryType;
import com.didado.armory.domain.core.dto.CoreSaveParameter;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/lostark/armory/{characterName}/cards")
    public ArmoryCardParameter card(@PathVariable("characterName") String characterName) {
        return cardService.search(characterName);
    }

    @ExceptionHandler(NotFoundArmoryCardException.class)
    public ResponseEntity<ArmoryCardParameter> handler(NotFoundArmoryCardException e) {
        log.error("{}", e, e);
        CoreSaveParameter saveParameter = new CoreSaveParameter(ArmoryType.CARD, e.getCharacterName());
        ArmoryCardParameter saveData = WebClient.create()
                .post()
                .uri("http://localhost:8080/lostark/armory/core")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(saveParameter)
                .retrieve()
                .bodyToMono(ArmoryCardParameter.class)
                .block();

        log.debug("Save Data={}", saveData);
        return ResponseEntity.ok(saveData);
    }
}
