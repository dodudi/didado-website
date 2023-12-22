package com.didado.armory.domain.equipment.api;

import com.didado.armory.domain.equipment.application.EquipmentService;
import com.didado.armory.domain.equipment.dto.ArmoryEquipmentParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping("/lostark/armory/{characterName}/equipments")
    public ResponseEntity<List<ArmoryEquipmentParameter>> equipments(@PathVariable String characterName) {
        return ResponseEntity.ok(equipmentService.search(characterName));
    }
}
