package com.website.didado.domain.lostark.api;

import com.website.didado.domain.lostark.application.ArmoryServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArmoryController {

    private final ArmoryServiceImpl armoryService;

    @GetMapping("/lostark/armory/{username}")
    public Object search(@PathVariable String username) {
        return armoryService.search(username);
    }

    @GetMapping("/lostark/armory/{username}/profiles")
    public Object profiles(@PathVariable String username) {
        return armoryService.profiles(username);
    }

    @GetMapping("/lostark/armory/{username}/equipment")
    public Object equipments(@PathVariable String username) {
        return armoryService.equipment(username);
    }

    @GetMapping("/lostark/armory/{username}/avatars")
    public Object avatars(@PathVariable String username) {
        return armoryService.avatars(username);
    }

    @GetMapping("/lostark/armory/{username}/combat-skills")
    public Object combatSkills(@PathVariable String username) {
        return armoryService.combatSkills(username);
    }

    @GetMapping("/lostark/armory/{username}/engravings")
    public Object engravings(@PathVariable String username) {
        return armoryService.engravings(username);
    }
}
