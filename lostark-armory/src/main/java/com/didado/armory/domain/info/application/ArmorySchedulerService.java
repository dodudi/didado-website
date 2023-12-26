package com.didado.armory.domain.info.application;


import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.info.dto.ArmoryParameter;
import com.didado.armory.domain.info.dto.avatar.ArmoryAvatarParameter;
import com.didado.armory.domain.info.dto.card.ArmoryCardParameter;
import com.didado.armory.domain.info.dto.collectible.CollectibleParameter;
import com.didado.armory.domain.info.dto.colosseum.ColosseumInfoParameter;
import com.didado.armory.domain.info.dto.engraving.ArmoryEngravingParameter;
import com.didado.armory.domain.info.dto.equipment.ArmoryEquipmentParameter;
import com.didado.armory.domain.info.dto.gem.ArmoryGemParameter;
import com.didado.armory.domain.info.dto.profile.ArmoryProfileParameter;
import com.didado.armory.domain.info.dto.skill.ArmorySkillParameter;
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
public class ArmorySchedulerService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;


    public void search(String characterName) {
        ArmoryParameter parameter = getParameter(characterName);
        ArmoryProfileParameter armoryProfile = parameter.getArmoryProfile();

//        List<ArmoryAvatarParameter> armoryAvatars = parameter.getArmoryAvatars();
//        ArmoryCardParameter armoryCard = parameter.getArmoryCard();
//        ArmoryGemParameter armoryGem = parameter.getArmoryGem();
//        ArmoryEngravingParameter armoryEngraving = parameter.getArmoryEngraving();
//        List<ArmoryEquipmentParameter> armoryEquipment = parameter.getArmoryEquipment();
//        List<ArmorySkillParameter> armorySkills = parameter.getArmorySkills();
//        List<CollectibleParameter> collectibles = parameter.getCollectibles();
//        ColosseumInfoParameter colosseumInfo = parameter.getColosseumInfo();
    }

    private ArmoryParameter getParameter(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName;
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ArmoryParameter> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }
}
