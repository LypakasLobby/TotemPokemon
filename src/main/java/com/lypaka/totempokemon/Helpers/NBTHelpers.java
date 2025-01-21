package com.lypaka.totempokemon.Helpers;

import com.lypaka.totempokemon.ConfigGetters;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;

public class NBTHelpers {

    public static boolean isTotem (Pokemon pokemon) {

        boolean isTotem = false;
        if (pokemon.getPersistentData().contains("Totem")) {

            isTotem = pokemon.getPersistentData().getBoolean("Totem");

        }
        return isTotem;

    }

    public static void setTotem (PixelmonEntity pixelmon) {

        pixelmon.setPixelmonScale(ConfigGetters.scale);
        pixelmon.setColor(ConfigGetters.tint);
        if (PixelmonSpecies.isLegendary(pixelmon.getSpecies()) || PixelmonSpecies.isMythical(pixelmon.getSpecies()) || PixelmonSpecies.isUltraBeast(pixelmon.getSpecies())) {

            pixelmon.getPokemon().getPersistentData().putBoolean("LegendaryTotem", true);

        } else {

            pixelmon.getPokemon().getPersistentData().putBoolean("Totem", true);
            if (ConfigGetters.doubleHP) {

                int original = pixelmon.getPokemon().getStat(BattleStatsType.HP);
                int updated = original * 2;
                pixelmon.getPokemon().getStats().set(BattleStatsType.HP, updated);

            }

        }
        pixelmon.getPokemon().addRibbon(RibbonHelpers.ribbon);
        pixelmon.getPokemon().setDisplayedRibbon(RibbonHelpers.ribbon);

    }

}
