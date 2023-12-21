package com.didado.armory.domain.profile.application;


import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.dto.armory.*;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    public Object search(String username) {

        String url = property.url() + "/armories/characters/" + username;
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<Armory> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new ArmoryResponse("캐릭터 상세 정보 조회에 성공", 200, response.getBody());
    }


    public Object equipment(String username) {
        String url = property.url() + "/armories/characters/" + username + "/equipment";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<List<ArmoryEquipment>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new ArmoryResponse("캐릭터 착용 장비 조회에 성공", 200, response.getBody());
    }

    public Object avatars(String username) {
        String url = property.url() + "/armories/characters/" + username + "/avatars";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<List<ArmoryAvatar>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new ArmoryResponse("캐릭터 착용 아바타 조회에 성공", 200, response.getBody());
    }

    public Object combatSkills(String username) {
        String url = property.url() + "/armories/characters/" + username + "/combat-skills";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<List<ArmorySkill>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new ArmoryResponse("캐릭터 착용 스킬 조회에 성공", 200, response.getBody());
    }

    public Object engravings(String username) {
        String url = property.url() + "/armories/characters/" + username + "/engravings";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ArmoryEngraving> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new ArmoryResponse("캐릭터 착용 각인 조회에 성공", 200, response.getBody());
    }

    public Object cards(String username) {
        String url = property.url() + "/armories/characters/" + username + "/cards";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ArmoryCard> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new ArmoryResponse("캐릭터 착용 카드 조회에 성공", 200, response.getBody());
    }

    public Object gems(String username) {
        String url = property.url() + "/armories/characters/" + username + "/gems";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ArmoryGem> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new ArmoryResponse("캐릭터 착용 보석 조회에 성공", 200, response.getBody());
    }

    public Object colosseums(String username) {
        String url = property.url() + "/armories/characters/" + username + "/colosseums";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ColosseumInfo> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new ArmoryResponse("캐릭터 결투장 정보 조회에 성공", 200, response.getBody());
    }

    public Object collectibles(String username) {
        String url = property.url() + "/armories/characters/" + username + "/collectibles";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<List<Collectible>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return new ArmoryResponse("캐릭터 수집물 조회에 성공", 200, response.getBody());
    }
}
