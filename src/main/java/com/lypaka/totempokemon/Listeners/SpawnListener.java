package com.lypaka.totempokemon.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.totempokemon.API.TotemSpawnEvent;
import com.lypaka.totempokemon.ConfigGetters;
import com.lypaka.totempokemon.Helpers.NBTHelpers;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import com.pixelmonmod.pixelmon.spawning.PlayerTrackingSpawner;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SpawnListener {

    @SubscribeEvent
    public void onSpawn (SpawnEvent event) {

        if (event.action.getOrCreateEntity() instanceof PixelmonEntity) {

            if (event.spawner instanceof PlayerTrackingSpawner) {

                ServerPlayerEntity player = ((PlayerTrackingSpawner) event.spawner).getTrackedPlayer();
                if (player != null) {

                    PixelmonEntity pixelmon = (PixelmonEntity) event.action.getOrCreateEntity();
                    if (RandomHelper.getRandomChance(ConfigGetters.spawnChance)) {

                        TotemSpawnEvent totemSpawnEvent = new TotemSpawnEvent(player, pixelmon.getPokemon());
                        MinecraftForge.EVENT_BUS.post(totemSpawnEvent);
                        if (!totemSpawnEvent.isCanceled()) {

                            NBTHelpers.setTotem(pixelmon);
                            player.sendMessage(FancyText.getFormattedText(ConfigGetters.spawnMessage.replace("%pokemonName%", pixelmon.getPokemon().getSpecies().getName())), player.getUniqueID());

                        }

                    }

                }

            }

        }

    }

}
