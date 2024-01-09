package com.didado.armory.domain.core.api;

import com.didado.armory.domain.avatar.application.AvatarQueryService;
import com.didado.armory.domain.card.application.CardService;
import com.didado.armory.domain.collectible.application.CollectibleService;
import com.didado.armory.domain.colosseum.application.ColosseumService;
import com.didado.armory.domain.core.application.ArmorySchedulerService;
import com.didado.armory.domain.core.domain.ArmoryType;
import com.didado.armory.domain.core.dto.CoreSaveParameter;
import com.didado.armory.domain.profile.application.ProfileServiceImpl;
import com.didado.armory.domain.skill.application.SkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CoreController {

    private final ArmorySchedulerService armorySchedulerService;

    private final ProfileServiceImpl profileService;
    private final AvatarQueryService avatarQueryService;
    private final CardService cardService;
    private final SkillService skillService;
    private final CollectibleService collectibleService;
    private final ColosseumService colosseumService;

    @PostMapping("/lostark/armory/core")
    public ResponseEntity<Object> save(@RequestBody CoreSaveParameter saveParameter) {
        armorySchedulerService.save(saveParameter.getCharacterName());

        if (saveParameter.getArmoryType().equals(ArmoryType.AVATAR)) {
            return ResponseEntity.ok(avatarQueryService.search(saveParameter.getCharacterName()));
        } else if (saveParameter.getArmoryType().equals(ArmoryType.PROFILE)) {
            return ResponseEntity.ok(profileService.search(saveParameter.getCharacterName()));
        } else if (saveParameter.getArmoryType().equals(ArmoryType.CARD)) {
            return ResponseEntity.ok(cardService.search(saveParameter.getCharacterName()));
        } else if (saveParameter.getArmoryType().equals(ArmoryType.SKILL)) {
            return ResponseEntity.ok(skillService.search(saveParameter.getCharacterName()));
        } else if (saveParameter.getArmoryType().equals(ArmoryType.COLLECTIBLE)) {
            return ResponseEntity.ok(collectibleService.search(saveParameter.getCharacterName()));
        } else if (saveParameter.getArmoryType().equals(ArmoryType.COLOSSEUM)) {
            return ResponseEntity.ok(colosseumService.search(saveParameter.getCharacterName()));
        }
        return ResponseEntity.ofNullable(null);
    }
}
