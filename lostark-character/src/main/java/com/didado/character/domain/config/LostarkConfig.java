package com.didado.character.domain.config;

import com.didado.character.domain.lostark.domain.LostarkProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
//@PropertySource("classpath:lostark.properties")
public class LostarkConfig {
    @Value("${lostark.url}")
    private String url;

    @Value("${lostark.api.key}")
    private String apiKey;

    @Bean
    public LostarkProperty getLostarkProperty(){
        log.debug("Lostark Url={}", url);
        log.debug("Lostark apiKey={}", apiKey);
        return new LostarkProperty(url, apiKey);
    }
}