package com.lypaka.totempokemon.Commands;

import com.lypaka.totempokemon.TotemPokemon;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = TotemPokemon.MOD_ID)
public class TotemPokemonCommand {

    public static final List<String> ALIASES = Arrays.asList("totempokemon", "totems", "totem", "totempoke");

    @SubscribeEvent
    public static void onCommandRegistration (RegisterCommandsEvent event) {

        new ReloadCommand(event.getDispatcher());
        new SpawnCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());

    }

}
