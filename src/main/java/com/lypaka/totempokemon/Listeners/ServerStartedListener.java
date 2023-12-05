package com.lypaka.totempokemon.Listeners;

import com.lypaka.totempokemon.Helpers.ParticleHelpers;
import com.lypaka.totempokemon.Helpers.RibbonHelpers;
import com.lypaka.totempokemon.TotemPokemon;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@Mod.EventBusSubscriber(modid = TotemPokemon.MOD_ID)
public class ServerStartedListener {

    @SubscribeEvent
    public static void onServerStarted (FMLServerStartedEvent event) {

        ParticleHelpers.loadParticleData();
        RibbonHelpers.loadRibbon();

        Pixelmon.EVENT_BUS.register(new BattleEndListeners());
        Pixelmon.EVENT_BUS.register(new BattleStartListener());
        Pixelmon.EVENT_BUS.register(new CaptureListener());
        Pixelmon.EVENT_BUS.register(new DropsListener());
        Pixelmon.EVENT_BUS.register(new SpawnListener());
        Pixelmon.EVENT_BUS.register(new UpdateListener());

    }

}
