package com.didado.armory.domain.equipment.application;

import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.equipment.domain.ArmoryEquipment;
import com.didado.armory.domain.equipment.dto.ArmoryEquipmentParameter;
import com.didado.armory.domain.equipment.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentSchedulerService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    private final EquipmentRepository equipmentRepository;

    public void search(String characterName) {
        List<ArmoryEquipmentParameter> equipmentParameters = equipment(characterName);
        List<ArmoryEquipment> armoryEquipments = equipmentRepository.findArmoryEquipmentByCharacterName(characterName);

        if (armoryEquipments.isEmpty()) {
            log.debug("Armory Equipment Not Exist = {}", characterName);
            List<ArmoryEquipment> convertEquipments = equipmentParameters.stream()
                    .map(ArmoryEquipmentParameter::toArmoryEquipment)
                    .toList();
            equipmentRepository.saveAll(convertEquipments);
        } else {
            log.debug("Armory Equipment Exist = {}", characterName);
            List<ArmoryEquipment> updateEquipment = armoryEquipments.stream()
                    .peek(armoryEquipment -> {
                        ArmoryEquipmentParameter armoryEquipmentParameter = equipmentParameters.stream()
                                .filter(parameter -> parameter.getType().equals(armoryEquipment.getType()))
                                .findAny()
                                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장비 타입입니다."));
                        armoryEquipment.updateData(armoryEquipmentParameter);
                    })
                    .toList();
            equipmentRepository.saveAll(updateEquipment);
        }
    }

    private List<ArmoryEquipmentParameter> equipment(String username) {
        String url = property.url() + "/armories/characters/" + username + "/equipment";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<List<ArmoryEquipmentParameter>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return Optional.ofNullable(response.getBody())
                .orElseGet(Collections::emptyList);
    }
}
