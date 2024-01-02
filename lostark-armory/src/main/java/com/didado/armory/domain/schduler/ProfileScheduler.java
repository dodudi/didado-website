package com.didado.armory.domain.schduler;

import com.didado.armory.domain.avatar.application.AvatarSchedulerService;
import com.didado.armory.domain.card.application.CardSchedulerService;
import com.didado.armory.domain.collectible.application.CollectibleSchedulerService;
import com.didado.armory.domain.colosseum.application.ColosseumSchedulerService;
import com.didado.armory.domain.engraving.application.EngravingSchedulerService;
import com.didado.armory.domain.equipment.application.EquipmentSchedulerService;
import com.didado.armory.domain.gem.application.GemSchedulerService;
import com.didado.armory.domain.core.application.ArmorySchedulerService;
import com.didado.armory.domain.profile.application.ProfileSchedulerService;
import com.didado.armory.domain.skill.application.SkillSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ProfileScheduler {
    private final ProfileSchedulerService profileSchedulerService;
    private final EquipmentSchedulerService equipmentSchedulerService;
    private final AvatarSchedulerService avatarSchedulerService;
    private final SkillSchedulerService skillSchedulerService;

    private final GemSchedulerService gemSchedulerService;

    private final CardSchedulerService cardSchedulerService;

    private final CollectibleSchedulerService collectibleSchedulerService;

    private final ColosseumSchedulerService colosseumSchedulerService;
    private final EngravingSchedulerService engravingSchedulerService;
    private final ArmorySchedulerService armorySchedulerService;
    @Scheduled(cron = "0/10 * * * * *")
    public void test() {
//        profileSchedulerService.search("Nelip");
//        equipmentSchedulerService.search("Nelip");
//        avatarSchedulerService.search("Nelip");
//        skillSchedulerService.search("Nelip");
//        gemSchedulerService.search("Nelip");
//        cardSchedulerService.search("Nelip");
//        collectibleSchedulerService.search("Nelip");
//        colosseumSchedulerService.search("Nelip");
//        engravingSchedulerService.search("Nelip");
//        armorySchedulerService.save("Nelip");
    }
}
