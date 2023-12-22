package com.didado.armory.domain.profile.api;

import com.didado.armory.domain.profile.application.ProfileServiceImpl;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
import com.didado.armory.domain.profile.dto.StatParameter;
import com.didado.armory.domain.profile.dto.TendencyParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileServiceImpl profileService;

    @GetMapping("/lostark/armory/{characterName}/profiles")
    public ResponseEntity<ArmoryProfileParameter> profiles(@PathVariable String characterName) {
        return ResponseEntity.ok(profileService.search(characterName));
    }

    @GetMapping("/lostark/armory/{characterName}/profiles/stats")
    public ResponseEntity<List<StatParameter>> stats(@PathVariable String characterName) {
        return ResponseEntity.ok(profileService.searchStats(characterName));
    }

    @GetMapping("/lostark/armory/{characterName}/profiles/tendencies")
    public ResponseEntity<List<TendencyParameter>> tendencies(@PathVariable String characterName) {
        return ResponseEntity.ok(profileService.searchTendencies(characterName));
    }

//    @GetMapping("/lostark/armory/{username}")
//    public Object search(@PathVariable String username) {
//        return armoryService.search(username);

//    }

//    @GetMapping("/lostark/armory/{username}/equipment")
//    public Object equipments(@PathVariable String username) {
//        return armoryService.equipment(username);
//    }

//    @GetMapping("/lostark/armory/{username}/avatars")
//    public Object avatars(@PathVariable String username) {
//        return armoryService.avatars(username);
//    }

//    @GetMapping("/lostark/armory/{username}/combat-skills")
//    public Object combatSkills(@PathVariable String username) {
//        return armoryService.combatSkills(username);
//    }

//    @GetMapping("/lostark/armory/{username}/engravings")
//    public Object engravings(@PathVariable String username) {
//        return armoryService.engravings(username);
//    }

//    @GetMapping("/lostark/armory/{username}/cards")
//    public Object cards(@PathVariable String username) {
//        return armoryService.cards(username);
//    }

//    @GetMapping("/lostark/armory/{username}/gems")
//    public Object gems(@PathVariable String username) {
//        return armoryService.gems(username);
//    }

//    @GetMapping("/lostark/armory/{username}/colosseums")
//    public Object colosseums(@PathVariable String username) {
//        return armoryService.colosseums(username);
//    }

//    @GetMapping("/lostark/armory/{username}/collectibles")
//    public Object collectibles(@PathVariable String username) {
//        return armoryService.collectibles(username);
//    }
}
