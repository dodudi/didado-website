package com.didado.armory.domain.info.application;


import com.didado.armory.domain.avatar.domain.ArmoryAvatar;
import com.didado.armory.domain.avatar.dto.ArmoryAvatarParameter;
import com.didado.armory.domain.avatar.repository.AvatarRepository;
import com.didado.armory.domain.card.domain.ArmoryCard;
import com.didado.armory.domain.card.domain.Card;
import com.didado.armory.domain.card.domain.CardEffect;
import com.didado.armory.domain.card.domain.Effect;
import com.didado.armory.domain.card.dto.ArmoryCardParameter;
import com.didado.armory.domain.card.dto.CardEffectParameter;
import com.didado.armory.domain.card.dto.CardParameter;
import com.didado.armory.domain.card.dto.EffectParameter;
import com.didado.armory.domain.card.repository.ArmoryCardRepository;
import com.didado.armory.domain.card.repository.CardEffectRepository;
import com.didado.armory.domain.card.repository.CardRepository;
import com.didado.armory.domain.card.repository.EffectRepository;
import com.didado.armory.domain.collectible.domain.Collectible;
import com.didado.armory.domain.collectible.domain.CollectiblePoint;
import com.didado.armory.domain.collectible.dto.CollectibleParameter;
import com.didado.armory.domain.collectible.dto.CollectiblePointParameter;
import com.didado.armory.domain.collectible.repository.CollectiblePointRepository;
import com.didado.armory.domain.collectible.repository.CollectibleRepository;
import com.didado.armory.domain.colosseum.domain.*;
import com.didado.armory.domain.colosseum.dto.*;
import com.didado.armory.domain.colosseum.repository.*;
import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.engraving.domain.ArmoryEngraving;
import com.didado.armory.domain.engraving.domain.Engraving;
import com.didado.armory.domain.engraving.domain.EngravingEffect;
import com.didado.armory.domain.engraving.dto.ArmoryEngravingParameter;
import com.didado.armory.domain.engraving.dto.EngravingEffectParameter;
import com.didado.armory.domain.engraving.dto.EngravingParameter;
import com.didado.armory.domain.engraving.repository.ArmoryEngravingRepository;
import com.didado.armory.domain.engraving.repository.EngravingEffectRepository;
import com.didado.armory.domain.engraving.repository.EngravingRepository;
import com.didado.armory.domain.equipment.domain.ArmoryEquipment;
import com.didado.armory.domain.equipment.dto.ArmoryEquipmentParameter;
import com.didado.armory.domain.equipment.repository.EquipmentRepository;
import com.didado.armory.domain.gem.domain.ArmoryGem;
import com.didado.armory.domain.gem.domain.Gem;
import com.didado.armory.domain.gem.domain.GemEffect;
import com.didado.armory.domain.gem.dto.ArmoryGemParameter;
import com.didado.armory.domain.gem.dto.GemEffectParameter;
import com.didado.armory.domain.gem.dto.GemParameter;
import com.didado.armory.domain.gem.repository.ArmoryGemRepository;
import com.didado.armory.domain.gem.repository.GemEffectRepository;
import com.didado.armory.domain.gem.repository.GemRepository;
import com.didado.armory.domain.info.domain.Armory;
import com.didado.armory.domain.info.dto.ArmoryParameter;
import com.didado.armory.domain.info.repository.ArmoryRepository;
import com.didado.armory.domain.profile.domain.ArmoryProfile;
import com.didado.armory.domain.profile.domain.Stat;
import com.didado.armory.domain.profile.domain.Tendency;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
import com.didado.armory.domain.profile.dto.StatParameter;
import com.didado.armory.domain.profile.dto.TendencyParameter;
import com.didado.armory.domain.profile.repository.ArmoryProfileRepository;
import com.didado.armory.domain.profile.repository.ArmoryStatRepository;
import com.didado.armory.domain.profile.repository.ArmoryTendencyRepository;
import com.didado.armory.domain.skill.domain.ArmorySkill;
import com.didado.armory.domain.skill.domain.SkillRune;
import com.didado.armory.domain.skill.domain.SkillTripod;
import com.didado.armory.domain.skill.dto.ArmorySkillParameter;
import com.didado.armory.domain.skill.dto.SkillRuneParameter;
import com.didado.armory.domain.skill.dto.SkillTripodParameter;
import com.didado.armory.domain.skill.repository.ArmorySkillRepository;
import com.didado.armory.domain.skill.repository.SkillRuneRepository;
import com.didado.armory.domain.skill.repository.SkillTripodRepository;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArmorySchedulerService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    private final ArmoryRepository armoryRepository;

    //profile
    private final ArmoryProfileRepository armoryProfileRepository;
    private final ArmoryStatRepository armoryStatRepository;
    private final ArmoryTendencyRepository armoryTendencyRepository;

    //equipment
    private final EquipmentRepository equipmentRepository;

    //avatar
    private final AvatarRepository avatarRepository;

    //Card
    private final ArmoryCardRepository armoryCardRepository;
    private final CardRepository cardRepository;
    private final CardEffectRepository cardEffectRepository;
    private final EffectRepository effectRepository;

    //Gem
    private final ArmoryGemRepository armoryGemRepository;
    private final GemRepository gemRepository;
    private final GemEffectRepository gemEffectRepository;

    //Engraving
    private final ArmoryEngravingRepository armoryEngravingRepository;
    private final EngravingRepository engravingRepository;
    private final EngravingEffectRepository engravingEffectRepository;

    //Skill
    private final ArmorySkillRepository armorySkillRepository;
    private final SkillTripodRepository skillTripodRepository;
    private final SkillRuneRepository skillRuneRepository;

    //Collectible
    private final CollectibleRepository collectibleRepository;
    private final CollectiblePointRepository collectiblePointRepository;

    //Colosseum
    private final ColosseumInfoRepository colosseumInfoRepository;
    private final ColosseumRepository colosseumRepository;
    private final AggregationEliminationRepository aggregationEliminationRepository;
    private final AggregationTeamDeathMatchRankRepository aggregationTeamDeathMatchRankRepository;
    private final CoOpBattleAggregationRepository coOpBattleAggregationRepository;
    private final DeathMatchAggregationRepository deathMatchAggregationRepository;
    private final TeamDeathMatchAggregationRepository teamDeathMatchAggregationRepository;

    public void search(String characterName) {

        ArmoryParameter parameter = getParameter(characterName);

        Armory armory = armoryRepository.findArmoryByCharacterName(characterName)
                .orElseGet(() -> parameter.toArmory(characterName));

        if (armory.getId() == null)
            armoryRepository.save(armory);


        ArmoryProfile armoryProfile = armoryProfiles(armory, parameter);
        List<ArmoryEquipment> armoryEquipments = armoryEquipments(armory, parameter);
        List<ArmoryAvatar> armoryAvatars = armoryAvatars(armory, parameter);
        ArmoryCard armoryCard = armoryCard(armory, parameter);
        ArmoryGem armoryGem = armoryGem(armory, parameter);
        ArmoryEngraving armoryEngraving = armoryEngraving(armory, parameter);
        List<ArmorySkill> armorySkills = armorySkills(armory, parameter);
        List<Collectible> collectibles = armoryCollectibles(armory, parameter);
        ColosseumInfo colosseumInfo = armoryColosseum(armory, parameter);


    }

    /**
     * API 에서 새로운 Armory Profile 저장.
     *
     * @param armory
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private ArmoryProfile armoryProfiles(Armory armory, ArmoryParameter parameter) {

        //New Armory Profile
        ArmoryProfileParameter armoryProfileParameter = parameter.getArmoryProfile();
        ArmoryProfile convertProfile = armoryProfileRepository.findArmoryProfileByArmoryId(armory.getId())
                .orElseGet(armoryProfileParameter::toArmoryProfile);

        //New
        if (convertProfile.getId() == null) {
            convertProfile.changeArmory(armory);
            armoryProfileRepository.save(convertProfile);
        }


        List<Stat> stats = armoryStatRepository.findStatByProfileId(convertProfile.getId());
        if (stats.isEmpty()) {
            stats = armoryProfileParameter.convertAndAddStats(convertProfile);
        } else {
            List<StatParameter> statParameters = armoryProfileParameter.getStats();
            Map<String, StatParameter> statTypeMap = statParameters.stream()
                    .collect(Collectors.toMap(StatParameter::getType, statParameter -> statParameter));

            stats.forEach(stat -> {
                if (statTypeMap.containsKey(stat.getType())) {
                    StatParameter statParameter = statTypeMap.get(stat.getType());
                    stat.updateData(
                            statParameter.getType(),
                            statParameter.getValue(),
                            statParameter.getToolTip()
                    );
                }
            });
        }

        List<Tendency> tendencies = armoryTendencyRepository.findTendencyByProfileId(convertProfile.getId());
        if (tendencies.isEmpty()) {
            tendencies = armoryProfileParameter.convertAndAddTendencies(convertProfile);
        } else {
            List<TendencyParameter> tendencyParameters = armoryProfileParameter.getTendencies();
            Map<String, TendencyParameter> tendencyTypeMap = tendencyParameters.stream()
                    .collect(Collectors.toMap(TendencyParameter::getType, tendencyParameter -> tendencyParameter));

            tendencies.forEach(tendency -> {
                if (tendencyTypeMap.containsKey(tendency.getType())) {
                    TendencyParameter tendencyParameter = tendencyTypeMap.get(tendency.getType());
                    tendency.updateData(
                            tendencyParameter.getType(),
                            tendencyParameter.getPoint(),
                            tendencyParameter.getMaxPoint()
                    );
                }
            });
        }

        armoryStatRepository.saveAll(stats);
        armoryTendencyRepository.saveAll(tendencies);
        return convertProfile;
    }

    /**
     * API 에서 새로운 Armory Equipment 저장.
     *
     * @param armory
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private List<ArmoryEquipment> armoryEquipments(Armory armory, ArmoryParameter parameter) {
        List<ArmoryEquipmentParameter> armoryEquipment = parameter.getArmoryEquipment();

        if (armoryEquipment != null) {
            List<ArmoryEquipment> convertArmoryEquipment = armoryEquipment.stream()
                    .map(ArmoryEquipmentParameter::toArmoryEquipment)
                    .peek(armoryEquipment1 -> armoryEquipment1.changeArmory(armory))
                    .toList();

            equipmentRepository.saveAll(convertArmoryEquipment);
            return convertArmoryEquipment;
        }

        return Collections.emptyList();
    }

    /**
     * API 에서 새로운 Armory Card 저장.
     *
     * @param armory
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private ArmoryCard armoryCard(Armory armory, ArmoryParameter parameter) {
        ArmoryCardParameter armoryCardParameter = parameter.getArmoryCard();
        ArmoryCard armoryCard = armoryCardParameter.toArmoryCard();
        armoryCard.changeArmory(armory);
        armoryCardRepository.save(armoryCard);

        //Card Convert
        List<CardParameter> cards = armoryCardParameter.getCards();
        List<Card> convertCards = cards.stream()
                .map(CardParameter::toCard)
                .peek(card -> card.changeArmoryCard(armoryCard))
                .toList();
        cardRepository.saveAll(convertCards);

        //Card Effect Convert
        List<CardEffectParameter> cardEffectParameters = armoryCardParameter.getEffects();
        List<CardEffect> convertCardEffects = new ArrayList<>();
        for (CardEffectParameter cardEffectParameter : cardEffectParameters) {
            CardEffect convertCardEffect = cardEffectParameter.toCardEffect();
            convertCardEffect.changeArmoryCard(armoryCard);
            cardEffectRepository.save(convertCardEffect);

            //Inner Effect Convert
            List<EffectParameter> effects = cardEffectParameter.getItems();
            List<Effect> convertEffects = effects.stream()
                    .map(EffectParameter::toCardEffect)
                    .peek(effect -> effect.changeCardEffect(convertCardEffect))
                    .toList();
            effectRepository.saveAll(convertEffects);
            convertCardEffects.add(convertCardEffect);
        }

        //ArmoryCard -> Convert Data Add
        armoryCard.getCards().addAll(convertCards);
        armoryCard.getEffects().addAll(convertCardEffects);
        return armoryCard;
    }

    /**
     * API 에서 새로운 Armory Gem 저장.
     *
     * @param armory
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private ArmoryGem armoryGem(Armory armory, ArmoryParameter parameter) {
        ArmoryGemParameter armoryGemParameter = parameter.getArmoryGem();
        ArmoryGem armoryGem = armoryGemParameter.toArmoryGem();
        armoryGem.changeArmory(armory);
        armoryGemRepository.save(armoryGem);

        List<GemParameter> gemParameters = armoryGemParameter.getGems();
        List<Gem> convertGems = gemParameters.stream().map(GemParameter::toGem)
                .peek(gem -> gem.changeArmoryGem(armoryGem))
                .toList();
        gemRepository.saveAll(convertGems);

        List<GemEffectParameter> effectParameters = armoryGemParameter.getEffects();
        List<GemEffect> convertGemEffects = effectParameters.stream()
                .map(GemEffectParameter::toGemEffect)
                .peek(gemEffect -> gemEffect.changeArmoryGem(armoryGem))
                .toList();
        gemEffectRepository.saveAll(convertGemEffects);

        return armoryGem;
    }


    /**
     * API 에서 새로운 Armory Engraving 저장.
     *
     * @param armory
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private ArmoryEngraving armoryEngraving(Armory armory, ArmoryParameter parameter) {
        ArmoryEngravingParameter armoryEngravingParameter = parameter.getArmoryEngraving();
        ArmoryEngraving armoryEngraving = armoryEngravingParameter.toArmoryEngraving();
        armoryEngraving.changeArmory(armory);
        armoryEngravingRepository.save(armoryEngraving);

        List<EngravingParameter> engravings = armoryEngravingParameter.getEngravings();
        List<Engraving> convertEngravings = engravings.stream()
                .map(EngravingParameter::toEngraving)
                .peek(engraving -> engraving.changeArmoryEngraving(armoryEngraving))
                .toList();
        engravingRepository.saveAll(convertEngravings);


        List<EngravingEffectParameter> engravingEffectParameters = armoryEngravingParameter.getEffects();
        List<EngravingEffect> convertEngravingEffects = engravingEffectParameters.stream()
                .map(EngravingEffectParameter::toEngravingEffect)
                .peek(engravingEffect -> engravingEffect.changeArmoryEngraving(armoryEngraving))
                .toList();
        engravingEffectRepository.saveAll(convertEngravingEffects);

        return armoryEngraving;
    }

    /**
     * API 에서 새로운 Armory Colosseum 저장.
     *
     * @param armory
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private ColosseumInfo armoryColosseum(Armory armory, ArmoryParameter parameter) {
        ColosseumInfoParameter colosseumInfoParameter = parameter.getColosseumInfo();
        ColosseumInfo convertColosseumInfo = colosseumInfoParameter.toColosseumInfo();
        convertColosseumInfo.changeArmory(armory);
        colosseumInfoRepository.save(convertColosseumInfo);

        List<ColosseumParameter> colosseumParameters = colosseumInfoParameter.getColosseums();
        for (ColosseumParameter colosseumParameter : colosseumParameters) {
            Colosseum colosseum = colosseumParameter.toColosseum();
            colosseumRepository.save(colosseum);

            AggregationTeamDeathMatchRankParameter competitive = colosseumParameter.getCompetitive();
            if (competitive != null) {
                AggregationTeamDeathMatchRank convertTeamDeathMatchRank = competitive.toAggregationTeamDeathMatchRank();
                aggregationTeamDeathMatchRankRepository.save(convertTeamDeathMatchRank);
                colosseum.changeAggregationTeamDeathMatchRank(convertTeamDeathMatchRank);
            }


            AggregationParameter teamDeathmatch = colosseumParameter.getTeamDeathmatch();
            if (teamDeathmatch != null) {
                TeamDeathmatchAggregation teamDeathmatchAggregation = teamDeathmatch.toTeamDeathmatchAggregation();
                teamDeathMatchAggregationRepository.save(teamDeathmatchAggregation);
                colosseum.changeTeamDeathmatchAggregation(teamDeathmatchAggregation);
            }

            AggregationParameter deathmatch = colosseumParameter.getDeathmatch();
            if (deathmatch != null) {
                DeathmatchAggregation deathmatchAggregation = deathmatch.toDeathmatchAggregation();
                deathMatchAggregationRepository.save(deathmatchAggregation);
                colosseum.changeDeathmatchAggregation(deathmatchAggregation);
            }

            AggregationParameter coOpBattle = colosseumParameter.getCoOpBattle();
            if (coOpBattle != null) {
                CoOpBattleAggregation coOpBattleAggregation = coOpBattle.toCoOpBattleAggregation();
                coOpBattleAggregationRepository.save(coOpBattleAggregation);
                colosseum.changeCoOpBattleAggregation(coOpBattleAggregation);
            }

            AggregationEliminationParameter teamElimination = colosseumParameter.getTeamElimination();
            if (teamElimination != null) {
                AggregationElimination aggregationElimination = teamElimination.toAggregationElimination();
                aggregationEliminationRepository.save(aggregationElimination);
                colosseum.changeTeamElimination(aggregationElimination);
            }

            convertColosseumInfo.getColosseums().add(colosseum);
        }
        return convertColosseumInfo;
    }


    /**
     * API 에서 새로운 Armory Avatars 저장.
     *
     * @param armory
     * @param parameter API 에서 가져온 ArmoryParameter
     */
    private List<ArmoryAvatar> armoryAvatars(Armory armory, ArmoryParameter parameter) {
        List<ArmoryAvatarParameter> armoryAvatars = parameter.getArmoryAvatars();
        List<ArmoryAvatar> convertArmoryAvatars = armoryAvatars.stream()
                .map(ArmoryAvatarParameter::toArmoryAvatar)
                .peek(armoryAvatar -> armoryAvatar.changeArmory(armory))
                .toList();

        avatarRepository.saveAll(convertArmoryAvatars);
        return convertArmoryAvatars;
    }


    private List<Collectible> armoryCollectibles(Armory armory, ArmoryParameter parameter) {
        List<CollectibleParameter> collectibleParameters = parameter.getCollectibles();
        List<Collectible> convertCollectible = new ArrayList<>();
        for (CollectibleParameter collectibleParameter : collectibleParameters) {

            Collectible collectible = collectibleParameter.toCollectible();
            collectible.changeArmory(armory);
            collectibleRepository.save(collectible);

            List<CollectiblePointParameter> collectiblePointParameters = collectibleParameter.getCollectiblePoints();
            List<CollectiblePoint> convertCollectiblePoints = collectiblePointParameters.stream()
                    .map(CollectiblePointParameter::toCollectiblePoint)
                    .peek(collectiblePoint -> collectiblePoint.changeCollectible(collectible))
                    .toList();
            collectiblePointRepository.saveAll(convertCollectiblePoints);
        }

        return convertCollectible;
    }

    private List<ArmorySkill> armorySkills(Armory armory, ArmoryParameter parameter) {
        List<ArmorySkillParameter> armorySkills = parameter.getArmorySkills();
        List<ArmorySkill> convertArmorySkills = new ArrayList<>();
        for (ArmorySkillParameter armorySkillParameter : armorySkills) {

            ArmorySkill armorySkill = armorySkillParameter.toArmorySkill();
            armorySkill.changeArmory(armory);
            armorySkillRepository.save(armorySkill);

            SkillRuneParameter rune = armorySkillParameter.getRune();
            if (rune != null) {
                SkillRune convertRune = rune.toSkillRune();
                convertRune.changeArmorySkill(armorySkill);
                skillRuneRepository.save(convertRune);
            }


            List<SkillTripodParameter> tripods = armorySkillParameter.getTripods();
            List<SkillTripod> convertTripods = tripods.stream()
                    .map(SkillTripodParameter::toSkillTripod)
                    .peek(skillTripod -> skillTripod.changeArmorySkill(armorySkill))
                    .toList();
            skillTripodRepository.saveAll(convertTripods);

            convertArmorySkills.add(armorySkill);
        }

        return convertArmorySkills;
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
