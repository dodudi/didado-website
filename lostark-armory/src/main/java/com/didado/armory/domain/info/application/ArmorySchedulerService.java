package com.didado.armory.domain.info.application;


import com.didado.armory.domain.avatar.domain.ArmoryAvatar;
import com.didado.armory.domain.avatar.dto.ArmoryAvatarParameter;
import com.didado.armory.domain.card.domain.ArmoryCard;
import com.didado.armory.domain.card.domain.Card;
import com.didado.armory.domain.card.domain.CardEffect;
import com.didado.armory.domain.card.domain.Effect;
import com.didado.armory.domain.card.dto.ArmoryCardParameter;
import com.didado.armory.domain.card.dto.CardEffectParameter;
import com.didado.armory.domain.card.dto.CardParameter;
import com.didado.armory.domain.card.dto.EffectParameter;
import com.didado.armory.domain.collectible.domain.Collectible;
import com.didado.armory.domain.collectible.domain.CollectiblePoint;
import com.didado.armory.domain.collectible.dto.CollectibleParameter;
import com.didado.armory.domain.collectible.dto.CollectiblePointParameter;
import com.didado.armory.domain.colosseum.domain.*;
import com.didado.armory.domain.colosseum.dto.*;
import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.engraving.domain.ArmoryEngraving;
import com.didado.armory.domain.engraving.domain.Engraving;
import com.didado.armory.domain.engraving.domain.EngravingEffect;
import com.didado.armory.domain.engraving.dto.ArmoryEngravingParameter;
import com.didado.armory.domain.engraving.dto.EngravingEffectParameter;
import com.didado.armory.domain.engraving.dto.EngravingParameter;
import com.didado.armory.domain.equipment.domain.ArmoryEquipment;
import com.didado.armory.domain.equipment.dto.ArmoryEquipmentParameter;
import com.didado.armory.domain.gem.domain.ArmoryGem;
import com.didado.armory.domain.gem.domain.Gem;
import com.didado.armory.domain.gem.domain.GemEffect;
import com.didado.armory.domain.gem.dto.ArmoryGemParameter;
import com.didado.armory.domain.gem.dto.GemEffectParameter;
import com.didado.armory.domain.gem.dto.GemParameter;
import com.didado.armory.domain.info.domain.Armory;
import com.didado.armory.domain.info.dto.ArmoryParameter;
import com.didado.armory.domain.info.repository.ArmoryRepository;
import com.didado.armory.domain.profile.domain.ArmoryProfile;
import com.didado.armory.domain.profile.domain.Stat;
import com.didado.armory.domain.profile.domain.Tendency;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
import com.didado.armory.domain.profile.repository.ArmoryProfileRepository;
import com.didado.armory.domain.profile.repository.ArmoryStatRepository;
import com.didado.armory.domain.profile.repository.ArmoryTendencyRepository;
import com.didado.armory.domain.skill.domain.ArmorySkill;
import com.didado.armory.domain.skill.domain.SkillRune;
import com.didado.armory.domain.skill.domain.SkillTripod;
import com.didado.armory.domain.skill.dto.ArmorySkillParameter;
import com.didado.armory.domain.skill.dto.SkillRuneParameter;
import com.didado.armory.domain.skill.dto.SkillTripodParameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArmorySchedulerService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    private final ArmoryRepository armoryRepository;

    private final ArmoryProfileRepository armoryProfileRepository;
    private final ArmoryStatRepository armoryStatRepository;
    private final ArmoryTendencyRepository armoryTendencyRepository;

    public void search(String characterName) {
        ArmoryParameter parameter = getParameter(characterName);

        Armory armory = parameter.toArmory(characterName);
        armoryRepository.save(armory);

        ArmoryProfile armoryProfile = armoryProfiles(parameter);
        List<ArmoryEquipment> armoryEquipments = armoryEquipments(parameter);
        List<ArmoryAvatar> armoryAvatars = armoryAvatars(parameter);
        ArmoryCard armoryCard = armoryCard(parameter);
        ArmoryGem armoryGem = armoryGem(parameter);
        ArmoryEngraving armoryEngraving = armoryEngraving(parameter);
        List<ArmorySkill> armorySkills = armorySkills(parameter);
        List<Collectible> collectibles = armoryCollectibles(parameter);

        ColosseumInfoParameter colosseumInfoParameter = parameter.getColosseumInfo();
        ColosseumInfo convertColosseumInfo = colosseumInfoParameter.toColosseumInfo();
        List<ColosseumParameter> colosseumParameters = colosseumInfoParameter.getColosseums();
        for (ColosseumParameter colosseumParameter : colosseumParameters) {
            Colosseum colosseum = colosseumParameter.toColosseum();

            AggregationTeamDeathMatchRank convertTeamDeathMatchRank = colosseumParameter.getCompetitive()
                    .toAggregationTeamDeathMatchRank();
            log.debug("Save={}", convertTeamDeathMatchRank);

            colosseum.changeAggregationTeamDeathMatchRank(convertTeamDeathMatchRank);


            TeamDeathmatchAggregation teamDeathmatchAggregation = colosseumParameter.getTeamDeathmatch()
                    .toTeamDeathmatchAggregation();
            log.debug("Save={}", teamDeathmatchAggregation);
            colosseum.changeTeamDeathmatchAggregation(teamDeathmatchAggregation);

            DeathmatchAggregation deathmatchAggregation = colosseumParameter.getDeathmatch()
                    .toDeathmatchAggregation();
            log.debug("Save={}", deathmatchAggregation);
            colosseum.changeDeathmatchAggregation(deathmatchAggregation);

            CoOpBattleAggregation coOpBattleAggregation = colosseumParameter.getCoOpBattle()
                    .toCoOpBattleAggregation();
            log.debug("Save={}", coOpBattleAggregation);
            colosseum.changeCoOpBattleAggregation(coOpBattleAggregation);

            AggregationElimination teamElimination = colosseumParameter.getTeamElimination().toAggregationElimination();
            log.debug("Save={}", teamElimination);
            colosseum.changeTeamElimination(teamElimination);

            log.debug("Save={}", colosseum);
            convertColosseumInfo.getColosseums().add(colosseum);
        }
    }

    private List<Collectible> armoryCollectibles(ArmoryParameter parameter) {
        List<CollectibleParameter> collectibleParameters = parameter.getCollectibles();
        List<Collectible> convertCollectible = new ArrayList<>();
        for (CollectibleParameter collectibleParameter : collectibleParameters) {

            Collectible collectible = collectibleParameter.toCollectible();

            List<CollectiblePointParameter> collectiblePointParameters = collectibleParameter.getCollectiblePoints();
            List<CollectiblePoint> convertCollectiblePoints = collectiblePointParameters.stream()
                    .map(CollectiblePointParameter::toCollectiblePoint)
                    .peek(collectiblePoint -> collectiblePoint.changeCollectible(collectible))
                    .toList();
        }

        return convertCollectible;
    }

    private List<ArmorySkill> armorySkills(ArmoryParameter parameter) {
        List<ArmorySkillParameter> armorySkills = parameter.getArmorySkills();
        List<ArmorySkill> convertArmorySkills = new ArrayList<>();
        for (ArmorySkillParameter armorySkillParameter : armorySkills) {

            ArmorySkill armorySkill = armorySkillParameter.toArmorySkill();

            SkillRuneParameter rune = armorySkillParameter.getRune();
            SkillRune convertRune = rune.toSkillRune();
            convertRune.changeArmorySkill(armorySkill);

            List<SkillTripodParameter> tripods = armorySkillParameter.getTripods();
            List<SkillTripod> convertTripods = tripods.stream()
                    .map(SkillTripodParameter::toSkillTripod)
                    .peek(skillTripod -> skillTripod.changeArmorySkill(armorySkill))
                    .toList();

            convertArmorySkills.add(armorySkill);
        }

        return convertArmorySkills;
    }

    /**
     * API 에서 새로운 Armory Engraving 저장.
     *
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private ArmoryEngraving armoryEngraving(ArmoryParameter parameter) {
        ArmoryEngravingParameter armoryEngravingParameter = parameter.getArmoryEngraving();
        ArmoryEngraving armoryEngraving = armoryEngravingParameter.toArmoryEngraving();

        List<EngravingParameter> engravings = armoryEngravingParameter.getEngravings();
        List<Engraving> convertEngravings = engravings.stream()
                .map(EngravingParameter::toEngraving)
                .peek(engraving -> engraving.changeArmoryEngraving(armoryEngraving))
                .toList();

        List<EngravingEffectParameter> engravingEffectParameters = armoryEngravingParameter.getEffects();
        List<EngravingEffect> convertEngravingEffects = engravingEffectParameters.stream()
                .map(EngravingEffectParameter::toEngravingEffect)
                .peek(engravingEffect -> engravingEffect.changeArmoryEngraving(armoryEngraving))
                .toList();

        return armoryEngraving;
    }

    /**
     * API 에서 새로운 Armory Gem 저장.
     *
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private ArmoryGem armoryGem(ArmoryParameter parameter) {
        ArmoryGemParameter armoryGemParameter = parameter.getArmoryGem();
        ArmoryGem armoryGem = armoryGemParameter.toArmoryGem();

        List<GemParameter> gemParameters = armoryGemParameter.getGems();
        List<Gem> convertGems = gemParameters.stream().map(GemParameter::toGem)
                .peek(gem -> gem.changeArmoryGem(armoryGem))
                .toList();

        List<GemEffectParameter> effectParameters = armoryGemParameter.getEffects();
        List<GemEffect> convertGemEffects = effectParameters.stream()
                .map(GemEffectParameter::toGemEffect)
                .peek(gemEffect -> gemEffect.changeArmoryGem(armoryGem))
                .toList();

        return armoryGem;
    }

    /**
     * API 에서 새로운 Armory Card 저장.
     *
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private ArmoryCard armoryCard(ArmoryParameter parameter) {
        ArmoryCardParameter armoryCardParameter = parameter.getArmoryCard();
        ArmoryCard armoryCard = armoryCardParameter.toArmoryCard();

        //Card Convert
        List<CardParameter> cards = armoryCardParameter.getCards();
        List<Card> convertCards = cards.stream()
                .map(CardParameter::toCard)
                .peek(card -> card.changeArmoryCard(armoryCard))
                .toList();

        //Card Effect Convert
        List<CardEffectParameter> cardEffectParameters = armoryCardParameter.getEffects();
        List<CardEffect> convertCardEffects = new ArrayList<>();
        for (CardEffectParameter cardEffectParameter : cardEffectParameters) {
            CardEffect convertCardEffect = cardEffectParameter.toCardEffect();

            //Inner Effect Convert
            List<EffectParameter> effects = cardEffectParameter.getItems();
            List<Effect> convertEffects = effects.stream()
                    .map(EffectParameter::toCardEffect)
                    .peek(effect -> effect.changeCardEffect(convertCardEffect))
                    .toList();

            convertCardEffects.add(convertCardEffect);
        }

        //ArmoryCard -> Convert Data Add
        armoryCard.getCards().addAll(convertCards);
        armoryCard.getEffects().addAll(convertCardEffects);
        return armoryCard;
    }

    /**
     * API 에서 새로운 Armory Profile 저장.
     *
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private ArmoryProfile armoryProfiles(ArmoryParameter parameter) {
        //New Armory Profile
        ArmoryProfileParameter armoryProfileParameter = parameter.getArmoryProfile();

        //New Armory Profile Save
        ArmoryProfile convertProfile = armoryProfileParameter.toArmoryProfile();
        armoryProfileRepository.save(convertProfile);

        //Armory Profile Stats, tendencies Convert And Get
        List<Stat> stats = armoryProfileParameter.convertAndAddStats(convertProfile);
        List<Tendency> tendencies = armoryProfileParameter.convertAndAddTendencies(convertProfile);

        armoryStatRepository.saveAll(stats);
        armoryTendencyRepository.saveAll(tendencies);

        return convertProfile;
    }

    /**
     * API 에서 새로운 Armory Equipment 저장.
     *
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private List<ArmoryEquipment> armoryEquipments(ArmoryParameter parameter) {
        List<ArmoryEquipmentParameter> armoryEquipment = parameter.getArmoryEquipment();
        return armoryEquipment.stream().map(ArmoryEquipmentParameter::toArmoryEquipment)
                .toList();
    }

    /**
     * API 에서 새로운 Armory Avatars 저장.
     *
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private List<ArmoryAvatar> armoryAvatars(ArmoryParameter parameter) {
        List<ArmoryAvatarParameter> armoryAvatars = parameter.getArmoryAvatars();
        return armoryAvatars.stream()
                .map(ArmoryAvatarParameter::toArmoryAvatar)
                .toList();
    }

    private ArmoryParameter getParameter(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName;
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ArmoryParameter> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }
}
