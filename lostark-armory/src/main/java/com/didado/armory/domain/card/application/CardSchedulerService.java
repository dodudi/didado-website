package com.didado.armory.domain.card.application;

import com.didado.armory.domain.card.domain.ArmoryCard;
import com.didado.armory.domain.card.domain.Card;
import com.didado.armory.domain.card.domain.CardEffect;
import com.didado.armory.domain.card.domain.Effect;
import com.didado.armory.domain.card.dto.ArmoryCardParameter;
import com.didado.armory.domain.card.dto.CardEffectParameter;
import com.didado.armory.domain.card.dto.CardParameter;
import com.didado.armory.domain.card.dto.EffectParameter;
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
            ArmoryCard armoryCard = armoryCardRepository.findByCharacterName(characterName)
                    .orElseThrow(() -> new NotFoundArmoryCardException("존재 하지 않는 캐릭터 이름입니다.", characterName));

            //Delete Old Card Effect
            deleteCardEffects(armoryCard);

            //Update Old Card Effect
            updateCardEffects(armoryCardParameter, armoryCard);

            //Update Old Card
            updateCards(armoryCardParameter, armoryCard);

            armoryCardRepository.save(armoryCard);
        } else {
            ArmoryCard armoryCard = new ArmoryCard(characterName);
            armoryCardRepository.save(armoryCard);

            //Save New Card
            saveCards(armoryCardParameter, armoryCard);

            //Save New Card Effect
            saveCardEffects(armoryCardParameter, armoryCard);
        }

    }

    private void saveCards(ArmoryCardParameter armoryCardParameter, ArmoryCard armoryCard) {
        List<CardParameter> cards = armoryCardParameter.getCards();
        List<Card> convertCards = cards.stream()
                .map(CardParameter::toCard)
                .toList();
        convertCards.forEach(card -> {
            card.changeArmoryCard(armoryCard);
            cardRepository.save(card);
        });
    }

    private void saveCardEffects(ArmoryCardParameter armoryCardParameter, ArmoryCard armoryCard) {
        List<CardEffectParameter> cardEffects = armoryCardParameter.getEffects();
        cardEffects.forEach(cardEffectParameter -> {
            CardEffect convertCardEffect = cardEffectParameter.toCardEffect();
            convertCardEffect.changeArmoryCard(armoryCard);
            cardEffectRepository.save(convertCardEffect);

            List<EffectParameter> items = cardEffectParameter.getItems();
            List<Effect> convertEffects = items.stream().map(EffectParameter::toCardEffect)
                    .toList();
            convertEffects.forEach(effect -> {
                effect.changeCardEffect(convertCardEffect);
                effectRepository.save(effect);
            });
        });
    }

    private void updateCardEffects(ArmoryCardParameter newArmoryCard, ArmoryCard oldArmoryCard) {
        List<CardEffect> newCardEffect = newArmoryCard.toArmoryCard(oldArmoryCard.getCharacterName()).getEffects();
        newCardEffect.forEach(cardEffect -> {
            List<Effect> newEffects = cardEffect.getItems();
            newEffects.forEach(effect -> {
                effect.changeCardEffect(cardEffect);
                effectRepository.save(effect);
            });

            cardEffect.changeArmoryCard(oldArmoryCard);
            cardEffectRepository.save(cardEffect);
        });
    }

    private void deleteCardEffects(ArmoryCard oldArmoryCard) {
        List<CardEffect> cardEffects = cardEffectRepository.findByArmoryCardId(oldArmoryCard.getId());
        cardEffects.forEach(cardEffect -> {
            //Effect delete
            List<Effect> effects = effectRepository.findByCardEffectId(cardEffect.getId());
            effects.forEach(effect -> {
                effect.deleteCardEffect(cardEffect);
                effectRepository.delete(effect);
            });

            //CardEffect delete
            cardEffect.deleteArmoryCard(oldArmoryCard);
            cardEffectRepository.delete(cardEffect);
        });
    }

    private void updateCards(ArmoryCardParameter newCard, ArmoryCard oldArmoryCard) {
        List<Card> oldCard = cardRepository.findByArmoryCardId(oldArmoryCard.getId());
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
