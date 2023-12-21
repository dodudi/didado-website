package com.didado.armory.domain.schduler;

import com.didado.armory.domain.profile.application.ProfileSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ProfileScheduler {
    private final ProfileSchedulerService profileSchedulerService;

    @Scheduled(cron = "0/10 * * * * *")
    public void test(){
        profileSchedulerService.search("디다도두");
    }
}
