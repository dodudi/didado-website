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
    public Object search(@PathVariable String username){
        return armoryService.search(username);
    }
}
