package com.lypaka.totempokemon.Listeners;

import com.lypaka.totempokemon.ConfigGetters;
import com.lypaka.totempokemon.Helpers.NBTHelpers;
import com.lypaka.totempokemon.Helpers.PokemonHelpers;
import com.pixelmonmod.pixelmon.api.events.battles.BattleStartedEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.battles.controller.BattleController;
import com.pixelmonmod.pixelmon.battles.controller.participants.PlayerParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.WildPixelmonParticipant;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BattleStartListener {

    @SubscribeEvent
    public void onPreBattleStart (BattleStartedEvent.Pre event) {

        WildPixelmonParticipant wpp = null;
        PlayerParticipant pp = null;
        BattleController bc = event.getBattleController();

        if (bc.participants.get(0) instanceof WildPixelmonParticipant && bc.participants.get(1) instanceof PlayerParticipant) {

            wpp = (WildPixelmonParticipant) bc.participants.get(0);
            pp = (PlayerParticipant) bc.participants.get(1);

        } else if (bc.participants.get(0) instanceof PlayerParticipant && bc.participants.get(1) instanceof WildPixelmonParticipant) {

            wpp = (WildPixelmonParticipant) bc.participants.get(1);
            pp = (PlayerParticipant) bc.participants.get(0);

        }

        if (wpp == null || pp == null) return;

        Pokemon pokemon = wpp.controlledPokemon.get(0).pokemon;

        if (NBTHelpers.isTotem(pokemon)) {

            if (ConfigGetters.levelMod > 0) {

                int playersHighest = StorageProxy.getParty(pp.player.getUniqueID()).getHighestLevel();
                int updatedLevel = playersHighest + ConfigGetters.levelMod;
                pokemon.setLevel(updatedLevel);
                wpp.controlledPokemon.get(0).setLevelNum(updatedLevel);

            }
            BattleStatsType[] highestStats = PokemonHelpers.getHighestStats(pokemon);
            for (BattleStatsType stat : highestStats) {

                if (stat != null) {

                    if (stat.getBattleStatIndex() != 7) { // we don't wanna fuck with HP here

                        try {

                            wpp.controlledPokemon.get(0).getBattleStats().modifyStat(1, stat);

                        } catch (ArrayIndexOutOfBoundsException er) {

                            break;

                        }

                    }

                }

            }

        }

    }

}
