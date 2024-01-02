package com.didado.armory.domain.core.api;

import com.didado.armory.domain.avatar.application.AvatarService;
import com.didado.armory.domain.core.application.ArmorySchedulerService;
import com.didado.armory.domain.core.domain.ArmoryType;
import com.didado.armory.domain.core.dto.ArmoryParameter;
import com.didado.armory.domain.core.dto.CoreSaveParameter;
import com.didado.armory.domain.profile.application.ProfileServiceImpl;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
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
    private final AvatarService avatarService;

    @PostMapping("/lostark/armory/core")
    public ResponseEntity<Object> save(@RequestBody CoreSaveParameter saveParameter) {
        armorySchedulerService.save(saveParameter.getCharacterName());
        if (saveParameter.getArmoryType().equals(ArmoryType.AVATAR)) {
            return ResponseEntity.ok(avatarService.search(saveParameter.getCharacterName()));
        } else if (saveParameter.getArmoryType().equals(ArmoryType.PROFILE)) {
            return ResponseEntity.ok(profileService.search(saveParameter.getCharacterName()));
        }

        return ResponseEntity.ofNullable(null);
    }
}
