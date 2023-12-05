package com.lypaka.totempokemon;

import java.awt.*;

public class ConfigGetters {

    public static boolean doubleHP;
    public static double expMod;
    public static int levelMod;
    public static String spawnMessage;
    public static String[] particles;
    public static float scale;
    public static double spawnChance;
    public static double specialCrystalDropChance;
    public static Color tint;
    public static double zCrystalDropChance;

    public static void load() {

        doubleHP = TotemPokemon.configManager.getConfigNode(0, "Double-HP").getBoolean();
        expMod = TotemPokemon.configManager.getConfigNode(0, "EXP-Modifier").getDouble();
        levelMod = TotemPokemon.configManager.getConfigNode(0, "Level-Modifier").getInt();
        spawnMessage = TotemPokemon.configManager.getConfigNode(0, "Message").getString();
        particles = TotemPokemon.configManager.getConfigNode(0, "Particles").getString().split(", ");
        scale = TotemPokemon.configManager.getConfigNode(0, "Scale").getFloat();
        spawnChance = TotemPokemon.configManager.getConfigNode(0, "Spawn-Chance").getDouble();
        specialCrystalDropChance = TotemPokemon.configManager.getConfigNode(0, "Special-Crystal-Drop-Chance").getDouble();
        String[] tintColor = TotemPokemon.configManager.getConfigNode(0, "Tint").getString().split(", ");
        tint = new Color(Integer.parseInt(tintColor[0]), Integer.parseInt(tintColor[1]), Integer.parseInt(tintColor[2]));
        zCrystalDropChance = TotemPokemon.configManager.getConfigNode(0, "Z-Crystal-Drop-Chance").getDouble();

    }

}
