package com.lypaka.totempokemon.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.totempokemon.ConfigGetters;
import com.lypaka.totempokemon.Helpers.NBTHelpers;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CaptureListener {

    @SubscribeEvent
    public void onCatchAttempt (CaptureEvent.StartCapture event) {

        ServerPlayerEntity player = event.getPlayer();
        PixelmonEntity pixelmon = event.getPokemon();
        Pokemon pokemon = pixelmon.getPokemon();
        if (NBTHelpers.isTotem(pokemon)) {

            // stops legendary Totems from being uncatchable because that'd be hilarious
            if (!PixelmonSpecies.isLegendary(pixelmon.getSpecies()) && !PixelmonSpecies.isMythical(pixelmon.getSpecies()) && !PixelmonSpecies.isUltraBeast(pixelmon.getSpecies())) {

                event.setCanceled(true);
                player.sendMessage(FancyText.getFormattedText("&eThe Totem blocked the capture attempt!"), player.getUniqueID());

            }

        }

    }

    @SubscribeEvent
    public void onCapture (CaptureEvent.SuccessfulCapture event) {

        ServerPlayerEntity player = event.getPlayer();
        PixelmonEntity pixelmon = event.getPokemon();
        Pokemon pokemon = pixelmon.getPokemon();
        if (NBTHelpers.isTotem(pokemon)) {

            // stops legendary Totems from being uncatchable because that'd be hilarious
            if (PixelmonSpecies.isLegendary(pixelmon.getSpecies()) && PixelmonSpecies.isMythical(pixelmon.getSpecies()) && PixelmonSpecies.isUltraBeast(pixelmon.getSpecies())) {

                if (ConfigGetters.specialCrystalDropChance > 0) {

                    ItemStack specialCrystal = DropsListener.getSpecialCrystalOrNull(pokemon);
                    if (specialCrystal != null) {

                        if (RandomHelper.getRandomChance(ConfigGetters.specialCrystalDropChance)) {

                            player.addItemStackToInventory(specialCrystal);

                        }

                    }

                }

            }

        }

    }

}
