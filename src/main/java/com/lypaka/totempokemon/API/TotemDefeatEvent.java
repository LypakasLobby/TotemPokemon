package com.lypaka.totempokemon.API;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class TotemDefeatEvent extends Event {

    private final ServerPlayerEntity player;
    private final Pokemon pokemon;

    public TotemDefeatEvent (ServerPlayerEntity player, Pokemon pokemon) {

        this.player = player;
        this.pokemon = pokemon;

    }

    public ServerPlayerEntity getPlayer() {

        return this.player;

    }

    public Pokemon getPokemon() {

        return this.pokemon;

    }

}
