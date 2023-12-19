package com.didado.character;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CharacterApplication {
    public static void main(String[] args) {
        SpringApplication.run(CharacterApplication.class, args);
    }
}
