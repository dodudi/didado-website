package com.didado.character.domain.config;

import com.didado.character.domain.lostark.domain.LostarkProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Configuration
public class LostarkConfig {
    @Value("${lostark.url}")
    private String url;

    @Value("${lostark.api.key}")
    private String apiKey;

    @Bean
    @Profile("default")
    public LostarkProperty getLostarkPropertyDefault(){
        log.debug("Lostark Url={}", url);
        log.debug("Lostark apiKey={}", apiKey);
        return new LostarkProperty(url, apiKey);
    }

    @Bean
    @Profile("dev")
    public LostarkProperty getLostarkPropertyDev() throws IOException {
        log.debug("Lostark Url={}", readLostarkUrl());
        log.debug("Lostark apiKey={}", readLostarkApiKey());
        return new LostarkProperty(readLostarkUrl(), readLostarkApiKey());
    }

    private String readLostarkUrl() throws IOException {
        return readFileContent(url);
    }

    private String readLostarkApiKey() throws IOException {
        return readFileContent(apiKey);
    }

    private String readFileContent(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readString(path);
    }
}
