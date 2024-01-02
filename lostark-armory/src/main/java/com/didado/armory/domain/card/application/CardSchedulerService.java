package com.didado.armory.domain.card.application;

import com.didado.armory.domain.card.domain.ArmoryCard;
import com.didado.armory.domain.card.domain.Card;
import com.didado.armory.domain.card.domain.CardEffect;
import com.didado.armory.domain.card.domain.Effect;
import com.didado.armory.domain.card.dto.ArmoryCardParameter;
import com.didado.armory.domain.card.dto.CardEffectParameter;
import com.didado.armory.domain.card.dto.CardParameter;
import com.didado.armory.domain.card.exception.NotFoundArmoryCardException;
import com.didado.armory.domain.card.repository.ArmoryCardRepository;
import com.didado.armory.domain.card.repository.CardEffectRepository;
import com.didado.armory.domain.card.repository.CardRepository;
import com.didado.armory.domain.card.repository.EffectRepository;
import com.didado.armory.domain.dto.LostarkProperty;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardSchedulerService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    private final ArmoryCardRepository armoryCardRepository;
    private final CardRepository cardRepository;
    private final CardEffectRepository cardEffectRepository;
    private final EffectRepository effectRepository;

    public void save(String characterName, ArmoryCardParameter armoryCardParameter) {
        armoryCardRepository.findByCharacterName(characterName);

        if (armoryCardRepository.existsByCharacterName(characterName)) {
            //update
            ArmoryCard armoryCard = armoryCardRepository.findByCharacterName(characterName)
                    .orElseThrow(() -> new NotFoundArmoryCardException("존재 하지 않는 캐릭터 이름입니다.", characterName));

            updateCards(armoryCardParameter, armoryCard);

            cardEffectRepository.deleteByArmoryCardId(armoryCard.getId());
            List<CardEffect> cardEffects = armoryCardParameter.toArmoryCard().getEffects();
            cardEffects.forEach(cardEffect -> cardEffect.changeArmoryCard(armoryCard));
            cardEffectRepository.saveAll(cardEffects);

        } else {
            //save

        }

    }

    private void updateCards(ArmoryCardParameter newCard, ArmoryCard armoryCard) {
        List<Card> oldCard = cardRepository.findByArmoryCardId(armoryCard.getId());

        Map<String, Card> cardMap = newCard.getCards().stream()
                .map(CardParameter::toCard)
                .collect(Collectors.toMap(Card::getSlot, card -> card));

        oldCard.forEach(card -> {
            if (cardMap.containsKey(card.getSlot()))
                card.changeData(cardMap.get(card.getSlot()));
        });
    }

    private void updateEffects() {

    }

    private ArmoryCardParameter getCardParameter(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName + "/cards";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ArmoryCardParameter> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }
}
