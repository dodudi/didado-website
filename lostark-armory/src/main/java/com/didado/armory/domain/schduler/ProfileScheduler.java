package com.didado.armory.domain.schduler;

import com.didado.armory.domain.avatar.application.AvatarSchedulerService;
import com.didado.armory.domain.equipment.application.EquipmentSchedulerService;
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

    @Scheduled(cron = "0/10 * * * * *")
    public void test() {
//        profileSchedulerService.search("디다도두");
//        equipmentSchedulerService.search("디다도두");
//        avatarSchedulerService.search("디다도두");

        skillSchedulerService.search("디다도두");
    }
}
