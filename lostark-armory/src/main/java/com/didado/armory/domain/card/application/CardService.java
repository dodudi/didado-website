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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {
    private final ArmoryCardRepository armoryCardRepository;
    private final CardRepository cardRepository;
    private final CardEffectRepository cardEffectRepository;
    private final EffectRepository effectRepository;

    public ArmoryCardParameter search(String characterName) {
        ArmoryCard armoryCard = armoryCardRepository.findByCharacterName(characterName)
                .orElseThrow(() -> new NotFoundArmoryCardException("존재 하지 않는 캐릭터 이름입니다.", characterName));

        List<Card> cards = cardRepository.findByArmoryCardId(armoryCard.getId());
        List<CardParameter> convertCards = cards.stream().map(CardParameter::new)
                .toList();

        List<CardEffect> cardEffects = cardEffectRepository.findByArmoryCardId(armoryCard.getId());
        List<CardEffectParameter> convertCardEffects = new ArrayList<>();
        cardEffects.forEach(cardEffect -> {
            CardEffectParameter cardEffectParameter = new CardEffectParameter(cardEffect);

            List<Effect> effects = effectRepository.findByCardEffectId(cardEffect.getId());
            List<EffectParameter> convertEffects = effects.stream()
                    .map(effect -> new EffectParameter(effect))
                    .toList();

            cardEffectParameter.getItems().addAll(convertEffects);
            convertCardEffects.add(cardEffectParameter);
        });


        ArmoryCardParameter armoryCardParameter = new ArmoryCardParameter();
        armoryCardParameter.getCards().addAll(convertCards);
        armoryCardParameter.getEffects().addAll(convertCardEffects);
        return armoryCardParameter;
    }
}
