package com.didado.armory.domain.equipment.application;

import com.didado.armory.domain.equipment.domain.ArmoryEquipment;
import com.didado.armory.domain.equipment.dto.ArmoryEquipmentParameter;
import com.didado.armory.domain.equipment.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public List<ArmoryEquipmentParameter> search(String characterName) {
//        List<ArmoryEquipment> armoryEquipments = equipmentRepository.findArmoryEquipmentByCharacterName(characterName);
//        return armoryEquipments.stream()
//                .map(ArmoryEquipmentParameter::new)
//                .toList();
        return null;
    }
}
