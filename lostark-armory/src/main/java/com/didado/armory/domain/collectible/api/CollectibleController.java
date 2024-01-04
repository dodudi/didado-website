package com.didado.armory.domain.collectible.api;

import com.didado.armory.domain.collectible.application.CollectibleService;
import com.didado.armory.domain.collectible.domain.CollectibleInfo;
import com.didado.armory.domain.collectible.dto.CollectibleInfoParameter;
import com.didado.armory.domain.collectible.dto.CollectibleParameter;
import com.didado.armory.domain.collectible.exception.NotFoundCollectibleInfoException;
import com.didado.armory.domain.core.domain.ArmoryType;
import com.didado.armory.domain.core.dto.CoreSaveParameter;
import com.didado.armory.domain.skill.dto.SkillInfoParameter;
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
public class CollectibleController {

    private final CollectibleService collectibleService;

    @GetMapping("/lostark/armory/{characterName}/collectible")
    public CollectibleInfoParameter collectible(@PathVariable("characterName") String characterName) {
        return collectibleService.search(characterName);
    }

    @ExceptionHandler(NotFoundCollectibleInfoException.class)
    public ResponseEntity<CollectibleInfoParameter> handler(NotFoundCollectibleInfoException e) {
        log.error("{}", e, e);
        CoreSaveParameter saveParameter = new CoreSaveParameter(ArmoryType.COLLECTIBLE, e.getCharacterName());
        CollectibleInfoParameter saveData = WebClient.create()
                .post()
                .uri("http://localhost:8080/lostark/armory/core")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(saveParameter)
                .retrieve()
                .bodyToMono(CollectibleInfoParameter.class)
                .block();

        log.debug("Save Data={}", saveData);
        return ResponseEntity.ok(saveData);
    }
}
