package com.didado.armory.domain.card.application;

import com.didado.armory.domain.card.domain.ArmoryCard;
import com.didado.armory.domain.card.domain.Card;
import com.didado.armory.domain.card.domain.CardEffect;
import com.didado.armory.domain.card.domain.Effect;
import com.didado.armory.domain.card.dto.ArmoryCardParameter;
import com.didado.armory.domain.card.dto.CardEffectParameter;
import com.didado.armory.domain.card.dto.CardParameter;
import com.didado.armory.domain.card.dto.EffectParameter;

public class CardConverter {
    public static ArmoryCard convertToEntity(String characterName, ArmoryCardParameter parameter) {
        ArmoryCard armoryCard = parameter.toArmoryCard(characterName);

        for (CardParameter cardParameter : parameter.getCards()) {
            Card card = cardParameter.toCard();
            card.changeArmoryCard(armoryCard);
        }

        for (CardEffectParameter cardEffectParameter : parameter.getEffects()) {
            CardEffect cardEffect = convertToCardEffectEntity(cardEffectParameter);
            cardEffect.changeArmoryCard(armoryCard);
        }

        return armoryCard;
    }

    private static CardEffect convertToCardEffectEntity(CardEffectParameter cardEffectParameter) {
        CardEffect cardEffect = cardEffectParameter.toCardEffect();

        for (EffectParameter effectParameter : cardEffectParameter.getItems()) {
            Effect effect = effectParameter.toEffect();
            effect.changeCardEffect(cardEffect);
        }

        return cardEffect;
    }
}
