package com.didado.armory.domain.core.application;


import com.didado.armory.domain.avatar.application.AvatarSchedulerService;
import com.didado.armory.domain.avatar.repository.AvatarRepository;
import com.didado.armory.domain.card.application.CardSchedulerService;
import com.didado.armory.domain.card.repository.ArmoryCardRepository;
import com.didado.armory.domain.card.repository.CardEffectRepository;
import com.didado.armory.domain.card.repository.CardRepository;
import com.didado.armory.domain.card.repository.EffectRepository;
import com.didado.armory.domain.collectible.application.CollectibleSchedulerService;
import com.didado.armory.domain.collectible.repository.CollectiblePointRepository;
import com.didado.armory.domain.collectible.repository.CollectibleRepository;
import com.didado.armory.domain.colosseum.application.ColosseumSchedulerService;
import com.didado.armory.domain.colosseum.repository.*;
import com.didado.armory.domain.dto.LostarkProperty;
import com.didado.armory.domain.engraving.repository.ArmoryEngravingRepository;
import com.didado.armory.domain.engraving.repository.EngravingEffectRepository;
import com.didado.armory.domain.engraving.repository.EngravingRepository;
import com.didado.armory.domain.equipment.repository.EquipmentRepository;
import com.didado.armory.domain.gem.repository.ArmoryGemRepository;
import com.didado.armory.domain.gem.repository.GemEffectRepository;
import com.didado.armory.domain.gem.repository.GemRepository;
import com.didado.armory.domain.core.dto.ArmoryParameter;
import com.didado.armory.domain.profile.application.ProfileSchedulerService;
import com.didado.armory.domain.skill.application.SkillSchedulerService;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class ArmorySchedulerService {
    private final LostarkProperty property;
    private final RestTemplate restTemplate;

    private final ProfileSchedulerService profileSchedulerService;
    private final AvatarSchedulerService avatarSchedulerService;
    private final CardSchedulerService cardSchedulerService;
    private final SkillSchedulerService skillSchedulerService;
    private final CollectibleSchedulerService collectibleSchedulerService;
    private final ColosseumSchedulerService colosseumSchedulerService;
    //equipment
    private final EquipmentRepository equipmentRepository;

    //Gem
    private final ArmoryGemRepository armoryGemRepository;
    private final GemRepository gemRepository;
    private final GemEffectRepository gemEffectRepository;

    //Engraving
    private final ArmoryEngravingRepository armoryEngravingRepository;
    private final EngravingRepository engravingRepository;
    private final EngravingEffectRepository engravingEffectRepository;

    public void save(String characterName) {
        ArmoryParameter parameter = getParameter(characterName);
//        profileSchedulerService.save(parameter.getArmoryProfile());
//        avatarSchedulerService.save(characterName, parameter.getArmoryAvatars());
//        cardSchedulerService.save(characterName, parameter.getArmoryCard());
//        skillSchedulerService.search(characterName, parameter.getArmorySkills());
//        collectibleSchedulerService.save(characterName, parameter.getCollectibles());
        colosseumSchedulerService.save(characterName, parameter.getColosseumInfo());


//        parameter.getArmorySkills();
//        List<ArmoryEquipment> armoryEquipments = armoryEquipments(armory, parameter);
//        List<ArmoryAvatar> armoryAvatars = armoryAvatars(armory, parameter);
//        ArmoryCard armoryCard = armoryCard(armory, parameter);
//        ArmoryGem armoryGem = armoryGem(armory, parameter);
//        ArmoryEngraving armoryEngraving = armoryEngraving(armory, parameter);
//        List<ArmorySkill> armorySkills = armorySkills(armory, parameter);
//        List<Collectible> collectibles = armoryCollectibles(armory, parameter);
//        ColosseumInfo colosseumInfo = armoryColosseum(armory, parameter);
    }
//    /**
//     * API 에서 새로운 Armory Equipment 저장.
//     *
//     * @param armory
//     * @param parameter API 에서 가져온 ArmoryParameter
//     */
//    private List<ArmoryEquipment> armoryEquipments(Armory armory, ArmoryParameter parameter) {
//        List<ArmoryEquipmentParameter> armoryEquipment = parameter.getArmoryEquipment();
//
//        if (armoryEquipment != null) {
//            List<ArmoryEquipment> convertArmoryEquipment = armoryEquipment.stream()
//                    .map(ArmoryEquipmentParameter::toArmoryEquipment)
//                    .peek(armoryEquipment1 -> armoryEquipment1.changeArmory(armory))
//                    .toList();
//
//            equipmentRepository.saveAll(convertArmoryEquipment);
//            return convertArmoryEquipment;
//        }
//
//        return Collections.emptyList();
//    }

//    /**
//     * API 에서 새로운 Armory Card 저장.
//     *
//     * @param armory
//     * @param parameter API 에서 가져온 ArmoryParameter
//     */
//    private ArmoryCard armoryCard(Armory armory, ArmoryParameter parameter) {
//        ArmoryCardParameter armoryCardParameter = parameter.getArmoryCard();
//        ArmoryCard armoryCard = armoryCardParameter.toArmoryCard();
//        armoryCard.changeArmory(armory);
//        armoryCardRepository.save(armoryCard);
//
//        Card Convert
//        List<CardParameter> cards = armoryCardParameter.getCards();
//        List<Card> convertCards = cards.stream()
//                .map(CardParameter::toCard)
//                .peek(card -> card.changeArmoryCard(armoryCard))
//                .toList();
//        cardRepository.saveAll(convertCards);
//
//        Card Effect Convert
//        List<CardEffectParameter> cardEffectParameters = armoryCardParameter.getEffects();
//        List<CardEffect> convertCardEffects = new ArrayList<>();
//        for (CardEffectParameter cardEffectParameter : cardEffectParameters) {
//            CardEffect convertCardEffect = cardEffectParameter.toCardEffect();
//            convertCardEffect.changeArmoryCard(armoryCard);
//            cardEffectRepository.save(convertCardEffect);
//
//            Inner Effect Convert
//            List<EffectParameter> effects = cardEffectParameter.getItems();
//            List<Effect> convertEffects = effects.stream()
//                    .map(EffectParameter::toCardEffect)
//                    .peek(effect -> effect.changeCardEffect(convertCardEffect))
//                    .toList();
//            effectRepository.saveAll(convertEffects);
//            convertCardEffects.add(convertCardEffect);
//        }

    //ArmoryCard -> Convert Data Add
//        armoryCard.getCards().addAll(convertCards);
//        armoryCard.getEffects().addAll(convertCardEffects);
//        return armoryCard;
//    }

    //    /**
//     * API 에서 새로운 Armory Gem 저장.
//     *
//     * @param armory
//     * @param parameter API 에서 가져온 ArmoryParameter
//     */
//    private ArmoryGem armoryGem(Armory armory, ArmoryParameter parameter) {
//        ArmoryGemParameter armoryGemParameter = parameter.getArmoryGem();
//        ArmoryGem armoryGem = armoryGemParameter.toArmoryGem();
//        armoryGem.changeArmory(armory);
//        armoryGemRepository.save(armoryGem);
//
//        List<GemParameter> gemParameters = armoryGemParameter.getGems();
//        List<Gem> convertGems = gemParameters.stream().map(GemParameter::toGem)
//                .peek(gem -> gem.changeArmoryGem(armoryGem))
//                .toList();
//        gemRepository.saveAll(convertGems);
//
//        List<GemEffectParameter> effectParameters = armoryGemParameter.getEffects();
//        List<GemEffect> convertGemEffects = effectParameters.stream()
//                .map(GemEffectParameter::toGemEffect)
//                .peek(gemEffect -> gemEffect.changeArmoryGem(armoryGem))
//                .toList();
//        gemEffectRepository.saveAll(convertGemEffects);
//
//        return armoryGem;
//    }
//
//
//    /**
//     * API 에서 새로운 Armory Engraving 저장.
//     *
//     * @param armory
//     * @param parameter API 에서 가져온 ArmoryParameter
//     */
//    private ArmoryEngraving armoryEngraving(Armory armory, ArmoryParameter parameter) {
//        ArmoryEngravingParameter armoryEngravingParameter = parameter.getArmoryEngraving();
//        ArmoryEngraving armoryEngraving = armoryEngravingParameter.toArmoryEngraving();
//        armoryEngraving.changeArmory(armory);
//        armoryEngravingRepository.save(armoryEngraving);
//
//        List<EngravingParameter> engravings = armoryEngravingParameter.getEngravings();
//        List<Engraving> convertEngravings = engravings.stream()
//                .map(EngravingParameter::toEngraving)
//                .peek(engraving -> engraving.changeArmoryEngraving(armoryEngraving))
//                .toList();
//        engravingRepository.saveAll(convertEngravings);
//
//
//        List<EngravingEffectParameter> engravingEffectParameters = armoryEngravingParameter.getEffects();
//        List<EngravingEffect> convertEngravingEffects = engravingEffectParameters.stream()
//                .map(EngravingEffectParameter::toEngravingEffect)
//                .peek(engravingEffect -> engravingEffect.changeArmoryEngraving(armoryEngraving))
//                .toList();
//        engravingEffectRepository.saveAll(convertEngravingEffects);
//
//        return armoryEngraving;
//    }
//
//    /**
//     * API 에서 새로운 Armory Colosseum 저장.
//     *
//     * @param armory
//     * @param parameter API 에서 가져온 ArmoryParameter
//     */
//    private ColosseumInfo armoryColosseum(Armory armory, ArmoryParameter parameter) {
//        ColosseumInfoParameter colosseumInfoParameter = parameter.getColosseumInfo();
//        ColosseumInfo convertColosseumInfo = colosseumInfoParameter.toColosseumInfo();
//        convertColosseumInfo.changeArmory(armory);
//        colosseumInfoRepository.save(convertColosseumInfo);
//
//        List<ColosseumParameter> colosseumParameters = colosseumInfoParameter.getColosseums();
//        for (ColosseumParameter colosseumParameter : colosseumParameters) {
//            Colosseum colosseum = colosseumParameter.toColosseum();
//            colosseumRepository.save(colosseum);
//
//            AggregationTeamDeathMatchRankParameter competitive = colosseumParameter.getCompetitive();
//            if (competitive != null) {
//                AggregationTeamDeathMatchRank convertTeamDeathMatchRank = competitive.toAggregationTeamDeathMatchRank();
//                aggregationTeamDeathMatchRankRepository.save(convertTeamDeathMatchRank);
//                colosseum.changeAggregationTeamDeathMatchRank(convertTeamDeathMatchRank);
//            }
//
//
//            AggregationParameter teamDeathmatch = colosseumParameter.getTeamDeathmatch();
//            if (teamDeathmatch != null) {
//                TeamDeathmatchAggregation teamDeathmatchAggregation = teamDeathmatch.toTeamDeathmatchAggregation();
//                teamDeathMatchAggregationRepository.save(teamDeathmatchAggregation);
//                colosseum.changeTeamDeathmatchAggregation(teamDeathmatchAggregation);
//            }
//
//            AggregationParameter deathmatch = colosseumParameter.getDeathmatch();
//            if (deathmatch != null) {
//                DeathmatchAggregation deathmatchAggregation = deathmatch.toDeathmatchAggregation();
//                deathMatchAggregationRepository.save(deathmatchAggregation);
//                colosseum.changeDeathmatchAggregation(deathmatchAggregation);
//            }
//
//            AggregationParameter coOpBattle = colosseumParameter.getCoOpBattle();
//            if (coOpBattle != null) {
//                CoOpBattleAggregation coOpBattleAggregation = coOpBattle.toCoOpBattleAggregation();
//                coOpBattleAggregationRepository.save(coOpBattleAggregation);
//                colosseum.changeCoOpBattleAggregation(coOpBattleAggregation);
//            }
//
//            AggregationEliminationParameter teamElimination = colosseumParameter.getTeamElimination();
//            if (teamElimination != null) {
//                AggregationElimination aggregationElimination = teamElimination.toAggregationElimination();
//                aggregationEliminationRepository.save(aggregationElimination);
//                colosseum.changeTeamElimination(aggregationElimination);
//            }
//
//            convertColosseumInfo.getColosseums().add(colosseum);
//        }
//        return convertColosseumInfo;
//    }
//
//
//    /**
//     * API 에서 새로운 Armory Avatars 저장.
//     *
//     * @param armory
//     * @param parameter API 에서 가져온 ArmoryParameter
//     */
//    private List<ArmoryAvatar> armoryAvatars(Armory armory, ArmoryParameter parameter) {
//        List<ArmoryAvatarParameter> armoryAvatars = parameter.getArmoryAvatars();
//        List<ArmoryAvatar> convertArmoryAvatars = armoryAvatars.stream()
//                .map(ArmoryAvatarParameter::toArmoryAvatar)
//                .peek(armoryAvatar -> armoryAvatar.changeArmory(armory))
//                .toList();
//
//        avatarRepository.saveAll(convertArmoryAvatars);
//        return convertArmoryAvatars;
//    }
//
//
//    private List<Collectible> armoryCollectibles(Armory armory, ArmoryParameter parameter) {
//        List<CollectibleParameter> collectibleParameters = parameter.getCollectibles();
//        List<Collectible> convertCollectible = new ArrayList<>();
//        for (CollectibleParameter collectibleParameter : collectibleParameters) {
//
//            Collectible collectible = collectibleParameter.toCollectible();
//            collectible.changeArmory(armory);
//            collectibleRepository.save(collectible);
//
//            List<CollectiblePointParameter> collectiblePointParameters = collectibleParameter.getCollectiblePoints();
//            List<CollectiblePoint> convertCollectiblePoints = collectiblePointParameters.stream()
//                    .map(CollectiblePointParameter::toCollectiblePoint)
//                    .peek(collectiblePoint -> collectiblePoint.changeCollectible(collectible))
//                    .toList();
//            collectiblePointRepository.saveAll(convertCollectiblePoints);
//        }
//
//        return convertCollectible;
//    }
//
//    private List<ArmorySkill> armorySkills(Armory armory, ArmoryParameter parameter) {
//        List<ArmorySkillParameter> armorySkills = parameter.getArmorySkills();
//        List<ArmorySkill> convertArmorySkills = new ArrayList<>();
//        for (ArmorySkillParameter armorySkillParameter : armorySkills) {
//
//            ArmorySkill armorySkill = armorySkillParameter.toArmorySkill();
//            armorySkill.changeArmory(armory);
//            armorySkillRepository.save(armorySkill);
//
//            SkillRuneParameter rune = armorySkillParameter.getRune();
//            if (rune != null) {
//                SkillRune convertRune = rune.toSkillRune();
//                convertRune.changeArmorySkill(armorySkill);
//                skillRuneRepository.save(convertRune);
//            }
//
//
//            List<SkillTripodParameter> tripods = armorySkillParameter.getTripods();
//            List<SkillTripod> convertTripods = tripods.stream()
//                    .map(SkillTripodParameter::toSkillTripod)
//                    .peek(skillTripod -> skillTripod.changeArmorySkill(armorySkill))
//                    .toList();
//            skillTripodRepository.saveAll(convertTripods);
//
//            convertArmorySkills.add(armorySkill);
//        }
//
//        return convertArmorySkills;
//    }
//
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
