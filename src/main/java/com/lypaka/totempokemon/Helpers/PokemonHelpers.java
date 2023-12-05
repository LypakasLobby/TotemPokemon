package com.lypaka.totempokemon.Helpers;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;

import java.util.HashMap;
import java.util.Map;

public class PokemonHelpers {

    // Thank you, good ole Pixelmon Generations, for providing me with this handy little method I apparently wrote (judging by how it has "my" formatting) once upon a time that I would think Reforged would have had in 1.16....but I guess not. XD
    public static BattleStatsType[] getHighestStats (Pokemon pokemon) {

        Map<BattleStatsType, Integer> baseStats = new HashMap<>();
        BattleStatsType[] stats = new BattleStatsType[]{BattleStatsType.HP, BattleStatsType.ATTACK, BattleStatsType.DEFENSE, BattleStatsType.SPECIAL_ATTACK, BattleStatsType.SPECIAL_DEFENSE, BattleStatsType.SPEED};
        for (BattleStatsType t : stats) {

            baseStats.put(t, pokemon.getStat(t));

        }
        int defaultMax = baseStats.get(BattleStatsType.HP);
        int arrayIndex = 0;
        BattleStatsType[] returnedStats = new BattleStatsType[6];
        for (Map.Entry<BattleStatsType, Integer> entry : baseStats.entrySet()) {

            if (entry.getValue() >= defaultMax) {

                defaultMax = entry.getValue();
                returnedStats[arrayIndex] = entry.getKey();
                arrayIndex++;

            }

        }

        return returnedStats;

    }

}
