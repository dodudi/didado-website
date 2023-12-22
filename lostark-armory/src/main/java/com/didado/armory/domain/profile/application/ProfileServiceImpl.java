package com.didado.armory.domain.profile.application;


import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.dto.armory.*;
import com.didado.armory.domain.profile.domain.ArmoryProfile;
import com.didado.armory.domain.profile.domain.Stat;
import com.didado.armory.domain.profile.domain.Tendency;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
import com.didado.armory.domain.profile.dto.StatParameter;
import com.didado.armory.domain.profile.dto.TendencyParameter;
import com.didado.armory.domain.profile.repository.ArmoryProfileRepository;
import com.didado.armory.domain.profile.repository.ArmoryStatRepository;
import com.didado.armory.domain.profile.repository.ArmoryTendencyRepository;
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
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl {
    private final ArmoryProfileRepository armoryProfileRepository;
    private final ArmoryStatRepository armoryStatRepository;
    private final ArmoryTendencyRepository armoryTendencyRepository;

    public ArmoryProfileParameter search(String characterName) {
        ArmoryProfile armoryProfiles = armoryProfileRepository.findByCharacterName(characterName)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 캐릭터명이 아닙니다."));

        List<Stat> stats = armoryStatRepository.findStatByProfileId(armoryProfiles.getId());
        List<StatParameter> convertStats = stats.stream()
                .map(StatParameter::new)
                .toList();


        List<Tendency> tendencies = armoryTendencyRepository.findTendencyByProfileId(armoryProfiles.getId());
        List<TendencyParameter> convertTendencies = tendencies.stream()
                .map(TendencyParameter::new)
                .toList();

        return new ArmoryProfileParameter(armoryProfiles, convertStats, convertTendencies);
    }

    public List<StatParameter> searchStats(String characterName) {
        ArmoryProfile armoryProfiles = armoryProfileRepository.findByCharacterName(characterName)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 캐릭터명이 아닙니다."));

        List<Stat> stats = armoryStatRepository.findStatByProfileId(armoryProfiles.getId());
        return stats.stream()
                .map(StatParameter::new)
                .toList();
    }

//    public Object search(String username) {
//
//        String url = property.url() + "/armories/characters/" + username;
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("authorization", property.apiKey());
//
//
//        ResponseEntity<Armory> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        return new ArmoryResponse("캐릭터 상세 정보 조회에 성공", 200, response.getBody());
//    }
//
//
//    public Object equipment(String username) {
//        String url = property.url() + "/armories/characters/" + username + "/equipment";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("authorization", property.apiKey());
//
//
//        ResponseEntity<List<ArmoryEquipment>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        return new ArmoryResponse("캐릭터 착용 장비 조회에 성공", 200, response.getBody());
//    }
//
//    public Object avatars(String username) {
//        String url = property.url() + "/armories/characters/" + username + "/avatars";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("authorization", property.apiKey());
//
//
//        ResponseEntity<List<ArmoryAvatar>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        return new ArmoryResponse("캐릭터 착용 아바타 조회에 성공", 200, response.getBody());
//    }
//
//    public Object combatSkills(String username) {
//        String url = property.url() + "/armories/characters/" + username + "/combat-skills";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("authorization", property.apiKey());
//
//
//        ResponseEntity<List<ArmorySkill>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        return new ArmoryResponse("캐릭터 착용 스킬 조회에 성공", 200, response.getBody());
//    }
//
//    public Object engravings(String username) {
//        String url = property.url() + "/armories/characters/" + username + "/engravings";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("authorization", property.apiKey());
//
//
//        ResponseEntity<ArmoryEngraving> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        return new ArmoryResponse("캐릭터 착용 각인 조회에 성공", 200, response.getBody());
//    }
//
//    public Object cards(String username) {
//        String url = property.url() + "/armories/characters/" + username + "/cards";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("authorization", property.apiKey());
//
//
//        ResponseEntity<ArmoryCard> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        return new ArmoryResponse("캐릭터 착용 카드 조회에 성공", 200, response.getBody());
//    }
//
//    public Object gems(String username) {
//        String url = property.url() + "/armories/characters/" + username + "/gems";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("authorization", property.apiKey());
//
//
//        ResponseEntity<ArmoryGem> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        return new ArmoryResponse("캐릭터 착용 보석 조회에 성공", 200, response.getBody());
//    }
//
//    public Object colosseums(String username) {
//        String url = property.url() + "/armories/characters/" + username + "/colosseums";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("authorization", property.apiKey());
//
//
//        ResponseEntity<ColosseumInfo> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        return new ArmoryResponse("캐릭터 결투장 정보 조회에 성공", 200, response.getBody());
//    }
//
//    public Object collectibles(String username) {
//        String url = property.url() + "/armories/characters/" + username + "/collectibles";
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("authorization", property.apiKey());
//
//
//        ResponseEntity<List<Collectible>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        return new ArmoryResponse("캐릭터 수집물 조회에 성공", 200, response.getBody());
//    }
}
