package com.lypaka.totempokemon.Helpers;

import com.lypaka.totempokemon.ConfigGetters;
import net.minecraft.particles.RedstoneParticleData;

public class ParticleHelpers {

    public static RedstoneParticleData particleData;

    public static void loadParticleData() {

        String[] rgb = ConfigGetters.particles;
        float r = Float.parseFloat(rgb[0]) / 1000;
        float g = Float.parseFloat(rgb[1]) / 1000;
        float b = Float.parseFloat(rgb[2]) / 1000;
        particleData = new RedstoneParticleData(r, g, b, 1.0f);

    }

}
