package com.didado.armory.domain.colosseum.application;

import com.didado.armory.domain.colosseum.domain.*;
import com.didado.armory.domain.colosseum.dto.*;
import com.didado.armory.domain.colosseum.exception.NotFoundColosseumInfoException;
import com.didado.armory.domain.colosseum.repository.*;
import com.didado.armory.domain.dto.LostarkProperty;
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
public class ColosseumSchedulerService {
    private final RestTemplate restTemplate;
    private final LostarkProperty property;

    private final ColosseumInfoRepository colosseumInfoRepository;
    private final ColosseumRepository colosseumRepository;

    private final AggregationTeamDeathMatchRankRepository aggregationTeamDeathMatchRankRepository;
    private final AggregationEliminationRepository aggregationEliminationRepository;
    private final DeathMatchAggregationRepository deathMatchAggregationRepository;
    private final TeamDeathMatchAggregationRepository teamDeathMatchAggregationRepository;
    private final CoOpBattleAggregationRepository coOpBattleAggregationRepository;

    public void save(String characterName, ColosseumInfoParameter colosseumInfoParameter) {
        if (colosseumInfoRepository.existsByCharacterName(characterName)) {
            //update
            ColosseumInfo oldColosseumInfo = colosseumInfoRepository.findByCharacterName(characterName)
                    .orElseThrow(() -> new NotFoundColosseumInfoException("존재 하지 않는 캐릭터 이름입니다.", characterName));
            ColosseumInfo newColosseumInfo = colosseumInfoParameter.toColosseumInfo(characterName);
            oldColosseumInfo.changeData(newColosseumInfo);

            List<Colosseum> oldColosseums = colosseumRepository.findByColosseumInfoId(oldColosseumInfo.getId());

            Map<String, Colosseum> colossenumMap = colosseumInfoParameter.getColosseums().stream()
                    .collect(Collectors.toMap(ColosseumParameter::getSeasonName, ColosseumParameter::toColosseum));

            Map<String, AggregationTeamDeathMatchRankParameter> teamDeathMatchRankParameterMap = colosseumInfoParameter.getColosseums().stream()
                    .collect(Collectors.toMap(ColosseumParameter::getSeasonName, ColosseumParameter::getCompetitive));

            Map<String, AggregationParameter> deathMatchAggragationParameterMap = colosseumInfoParameter.getColosseums().stream()
                    .collect(Collectors.toMap(ColosseumParameter::getSeasonName, ColosseumParameter::getDeathmatch));

            Map<String, AggregationParameter> coOpAggregationParameterMap = colosseumInfoParameter.getColosseums().stream()
                    .collect(Collectors.toMap(ColosseumParameter::getSeasonName, ColosseumParameter::getCoOpBattle));

            Map<String, AggregationParameter> teamDeathMatchAggregationParameterMap = colosseumInfoParameter.getColosseums().stream()
                    .collect(Collectors.toMap(ColosseumParameter::getSeasonName, ColosseumParameter::getTeamDeathmatch));

            Map<String, AggregationEliminationParameter> eliminationParameterMap = colosseumInfoParameter.getColosseums().stream()
                    .collect(Collectors.toMap(ColosseumParameter::getSeasonName, ColosseumParameter::getTeamElimination));

            oldColosseums.forEach(colosseum -> {
                //Delete Old Colossenum
                AggregationElimination teamElimination = colosseum.getTeamElimination();
                AggregationTeamDeathMatchRank competitive = colosseum.getCompetitive();
                DeathmatchAggregation deathmatch = colosseum.getDeathmatch();
                TeamDeathmatchAggregation teamDeathmatch = colosseum.getTeamDeathmatch();
                CoOpBattleAggregation coOpBattle = colosseum.getCoOpBattle();

                //Delete Not Found Data
                if (!colossenumMap.containsKey(colosseum.getSeasonName())) {
                    colosseum.changeTeamElimination(null);
                    colosseum.changeAggregationTeamDeathMatchRank(null);
                    colosseum.changeDeathmatchAggregation(null);
                    colosseum.changeTeamDeathmatchAggregation(null);
                    colosseum.changeCoOpBattleAggregation(null);
                    colosseum.deleteColosseumInfo();
                    colosseumRepository.delete(colosseum);
                    return;
                }

                teamElimination.changeData(eliminationParameterMap.get(colosseum.getSeasonName()).toAggregationElimination());
                competitive.changeData(teamDeathMatchRankParameterMap.get(colosseum.getSeasonName()).toAggregationTeamDeathMatchRank());
                deathmatch.changeData(deathMatchAggragationParameterMap.get(colosseum.getSeasonName()).toDeathmatchAggregation());
                teamDeathmatch.changeData(teamDeathMatchAggregationParameterMap.get(colosseum.getSeasonName()).toTeamDeathmatchAggregation());
                coOpBattle.changeData(coOpAggregationParameterMap.get(colosseum.getSeasonName()).toCoOpBattleAggregation());
            });
        } else {
            //save
            ColosseumInfo colosseumInfo = colosseumInfoParameter.toColosseumInfo(characterName);
            colosseumInfoRepository.save(colosseumInfo);

            List<ColosseumParameter> colosseums = colosseumInfoParameter.getColosseums();
            colosseums.forEach(colosseumParameter -> {
                Colosseum colosseum = colosseumParameter.toColosseum();
                colosseum.changeColosseumInfo(colosseumInfo);

                //Save 1
                if (colosseumParameter.getCompetitive() != null) {
                    AggregationTeamDeathMatchRankParameter competitive = colosseumParameter.getCompetitive();
                    AggregationTeamDeathMatchRank aggregationTeamDeathMatchRank = competitive.toAggregationTeamDeathMatchRank();
                    aggregationTeamDeathMatchRankRepository.save(aggregationTeamDeathMatchRank);
                    colosseum.changeAggregationTeamDeathMatchRank(aggregationTeamDeathMatchRank);
                }

                //Save 2
                if (colosseumParameter.getTeamElimination() != null) {
                    AggregationEliminationParameter teamElimination = colosseumParameter.getTeamElimination();
                    AggregationElimination aggregationElimination = teamElimination.toAggregationElimination();
                    aggregationEliminationRepository.save(aggregationElimination);
                    colosseum.changeTeamElimination(aggregationElimination);
                }

                //Save 3
                if (colosseumParameter.getDeathmatch() != null) {
                    AggregationParameter deathmatch = colosseumParameter.getDeathmatch();
                    DeathmatchAggregation deathmatchAggregation = deathmatch.toDeathmatchAggregation();
                    deathMatchAggregationRepository.save(deathmatchAggregation);
                    colosseum.changeDeathmatchAggregation(deathmatchAggregation);
                }

                //Save 4
                if (colosseumParameter.getTeamDeathmatch() != null) {
                    AggregationParameter teamDeathmatch = colosseumParameter.getTeamDeathmatch();
                    TeamDeathmatchAggregation teamDeathmatchAggregation = teamDeathmatch.toTeamDeathmatchAggregation();
                    teamDeathMatchAggregationRepository.save(teamDeathmatchAggregation);
                    colosseum.changeTeamDeathmatchAggregation(teamDeathmatchAggregation);
                }

                //Save 5

                if (colosseumParameter.getCoOpBattle() != null) {
                    AggregationParameter coOpBattle = colosseumParameter.getCoOpBattle();
                    CoOpBattleAggregation coOpBattleAggregation = coOpBattle.toCoOpBattleAggregation();
                    coOpBattleAggregationRepository.save(coOpBattleAggregation);
                    colosseum.changeCoOpBattleAggregation(coOpBattleAggregation);
                }


                colosseumRepository.save(colosseum);
            });
        }

    }

    public void search(String characterName) {
        ColosseumInfoParameter colosseums = getColosseums(characterName);
        log.debug("{}", colosseums);
    }

    private ColosseumInfoParameter getColosseums(String characterName) {
        String url = property.url() + "/armories/characters/" + characterName + "/colosseums";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", property.apiKey());


        ResponseEntity<ColosseumInfoParameter> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();

    }
}
