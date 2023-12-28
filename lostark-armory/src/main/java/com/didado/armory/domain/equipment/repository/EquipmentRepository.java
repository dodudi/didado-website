package com.didado.armory.domain.equipment.repository;

import com.didado.armory.domain.equipment.domain.ArmoryEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<ArmoryEquipment, Long> {
//    List<ArmoryEquipment> findArmoryEquipmentByCharacterName(String characterName);
}
