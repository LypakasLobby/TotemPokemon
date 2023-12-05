package com.lypaka.totempokemon.Listeners;

import com.lypaka.totempokemon.API.TotemDefeatEvent;
import com.lypaka.totempokemon.ConfigGetters;
import com.lypaka.totempokemon.Helpers.NBTHelpers;
import com.pixelmonmod.pixelmon.api.events.BeatWildPixelmonEvent;
import com.pixelmonmod.pixelmon.api.events.ExperienceGainEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.battles.controller.BattleController;
import com.pixelmonmod.pixelmon.battles.controller.participants.PlayerParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.WildPixelmonParticipant;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BattleEndListeners {

    @SubscribeEvent
    public void onTotemDefeat (BeatWildPixelmonEvent event) {

        ServerPlayerEntity player = event.player;
        Pokemon pokemon = event.wpp.controlledPokemon.get(0).pokemon;

        if (NBTHelpers.isTotem(pokemon)) {

            TotemDefeatEvent defeatEvent = new TotemDefeatEvent(player, pokemon);
            MinecraftForge.EVENT_BUS.post(defeatEvent);

        }

    }

    @SubscribeEvent
    public void onBattleEnd (ExperienceGainEvent event) {

        if (ConfigGetters.expMod <= 0) return;

        if (event.isFromBattle()) {

            BattleController bc = event.getBattleController();
            WildPixelmonParticipant wpp = null;
            PlayerParticipant pp = null;

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

                int original = event.getExperience();
                int updated = (int) (original * ConfigGetters.expMod);
                event.setExperience(updated);

            }

        }

    }

}
