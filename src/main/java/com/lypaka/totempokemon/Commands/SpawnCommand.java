package com.lypaka.totempokemon.Commands;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.lypaka.totempokemon.ConfigGetters;
import com.lypaka.totempokemon.Helpers.NBTHelpers;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.pixelmonmod.pixelmon.api.command.PixelmonCommandUtils;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

public class SpawnCommand {

    public SpawnCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : TotemPokemonCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("spawn")
                                            .then(
                                                    Commands.argument("player", EntityArgument.players())
                                                            .then(
                                                                    Commands.argument("pokemon", StringArgumentType.word())
                                                                            .suggests(
                                                                                    (context, builder) -> ISuggestionProvider.suggest(PixelmonCommandUtils.tabCompletePokemon(), builder)
                                                                            )
                                                                            .executes(c -> {

                                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                                    if (!PermissionHandler.hasPermission(player, "totempokemon.command.admin")) {

                                                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUniqueID());
                                                                                        return 0;

                                                                                    }

                                                                                }

                                                                                ServerPlayerEntity target = EntityArgument.getPlayer(c, "player");
                                                                                Pokemon pokemon;
                                                                                String species = StringArgumentType.getString(c, "pokemon");
                                                                                if (species.equalsIgnoreCase("random")) {

                                                                                    pokemon = PokemonBuilder.builder().species(PixelmonSpecies.getRandomSpecies()).build();

                                                                                } else {

                                                                                    pokemon = PokemonBuilder.builder().species(species).build();

                                                                                }
                                                                                int min = pokemon.getForm().minLevel;
                                                                                int max = pokemon.getForm().maxLevel;
                                                                                pokemon.setLevel(RandomHelper.getRandomNumberBetween(min, max));
                                                                                PixelmonEntity pixelmon = pokemon.getOrCreatePixelmon(target.world, target.getPosX(), target.getPosY(), target.getPosZ());
                                                                                target.world.addEntity(pixelmon);
                                                                                NBTHelpers.setTotem(pixelmon);
                                                                                target.sendMessage(FancyText.getFormattedText(ConfigGetters.spawnMessage.replace("%pokemonName%", pixelmon.getPokemon().getSpecies().getName())), target.getUniqueID());
                                                                                return 1;

                                                                            })
                                                            )
                                            )
                            )
            );

        }

    }

}
