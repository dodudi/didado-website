package com.didado.armory.domain.collectible.dto;

import com.didado.armory.domain.collectible.domain.Collectible;
import com.didado.armory.domain.collectible.domain.CollectibleInfo;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CollectibleInfoParameter {

    private String characterName;

    private List<CollectibleParameter> collectibles = new ArrayList<>();

    protected CollectibleInfoParameter() {
    }

    public CollectibleInfoParameter(String characterName, List<CollectibleParameter> collectibles) {
        this.characterName = characterName;
        this.collectibles = collectibles;
    }
}
