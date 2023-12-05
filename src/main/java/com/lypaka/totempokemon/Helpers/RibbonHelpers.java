package com.lypaka.totempokemon.Helpers;

import com.lypaka.lypakautils.FancyText;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.MutableRibbonData;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.Ribbon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.RibbonRegistry;

import java.time.LocalDateTime;

public class RibbonHelpers {

    public static Ribbon ribbon;

    public static void loadRibbon() {

        MutableRibbonData ribbonData = new MutableRibbonData();
        ribbonData.setPrefix(FancyText.getFormattedText("&eTotem "));
        ribbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), ribbonData);

    }

}
