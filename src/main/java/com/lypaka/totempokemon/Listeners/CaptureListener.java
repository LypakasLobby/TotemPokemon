package com.lypaka.totempokemon.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.totempokemon.Helpers.NBTHelpers;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CaptureListener {

    @SubscribeEvent
    public void onCatchAttempt (CaptureEvent.StartCapture event) {

        ServerPlayerEntity player = event.getPlayer();
        Pokemon pokemon = event.getPokemon().getPokemon();
        if (NBTHelpers.isTotem(pokemon)) {

            event.setCanceled(true);
            player.sendMessage(FancyText.getFormattedText("&eThe Totem blocked the capture attempt!"), player.getUniqueID());

        }

    }

}
