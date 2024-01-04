package com.didado.armory.domain.colosseum.application;

import com.didado.armory.domain.colosseum.domain.*;
import com.didado.armory.domain.colosseum.dto.*;
import com.didado.armory.domain.colosseum.exception.NotFoundColosseumInfoException;
import com.didado.armory.domain.colosseum.repository.ColosseumInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ColosseumService {
    private final ColosseumInfoRepository colosseumInfoRepository;

    public ColosseumInfoParameter search(String characterName) {
        ColosseumInfo colosseumInfo = colosseumInfoRepository.findByCharacterName(characterName)
                .orElseThrow(() -> new NotFoundColosseumInfoException("존재 하지 않는 캐릭터 이름입니다.", characterName));
        ColosseumInfoParameter colosseumInfoParameter = new ColosseumInfoParameter(colosseumInfo);


        List<Colosseum> colosseums = colosseumInfo.getColosseums();
        colosseums.forEach(colosseum -> {
            Hibernate.initialize(colosseum.getCompetitive());
            AggregationTeamDeathMatchRank competitive = colosseum.getCompetitive();
            AggregationTeamDeathMatchRankParameter aggregationTeamDeathMatchRankParameter = new AggregationTeamDeathMatchRankParameter(competitive);

            Hibernate.initialize(colosseum.getTeamElimination());
            AggregationElimination teamElimination = colosseum.getTeamElimination();
            AggregationEliminationParameter aggregationEliminationParameter = new AggregationEliminationParameter(teamElimination);

            Hibernate.initialize(colosseum.getDeathmatch());
            DeathmatchAggregation deathmatch = colosseum.getDeathmatch();
            AggregationParameter aggregationParameter1 = new AggregationParameter(deathmatch);

            Hibernate.initialize(colosseum.getTeamDeathmatch());
            TeamDeathmatchAggregation teamDeathmatch = colosseum.getTeamDeathmatch();
            AggregationParameter aggregationParameter2 = new AggregationParameter(teamDeathmatch);

            Hibernate.initialize(colosseum.getCoOpBattle());
            CoOpBattleAggregation coOpBattle = colosseum.getCoOpBattle();
            AggregationParameter aggregationParameter3 = new AggregationParameter(coOpBattle);

            ColosseumParameter colosseum1 = new ColosseumParameter(colosseum);
            colosseum1.changeAggregationTeamDeathMatchRank(aggregationTeamDeathMatchRankParameter);
            colosseum1.changeTeamElimination(aggregationEliminationParameter);
            colosseum1.changeDeathmatchAggregation(aggregationParameter1);
            colosseum1.changeTeamDeathmatchAggregation(aggregationParameter2);
            colosseum1.changeCoOpBattleAggregation(aggregationParameter3);

            colosseumInfoParameter.getColosseums().add(colosseum1);
        });

        return colosseumInfoParameter;
    }
}
