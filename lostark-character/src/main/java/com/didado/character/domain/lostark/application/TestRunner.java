package com.didado.character.domain.lostark.application;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestRunner implements ApplicationRunner {

    private final CharacterApiService characterApiService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 2; i++)
            characterApiService.search("디다도두");
    }
}
