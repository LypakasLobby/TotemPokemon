package com.lypaka.totempokemon.Listeners;

import com.lypaka.totempokemon.ConfigGetters;
import com.lypaka.totempokemon.Helpers.NBTHelpers;
import com.pixelmonmod.pixelmon.api.events.DropEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Element;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.registries.PixelmonItems;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DropsListener {

    @SubscribeEvent
    public void onPokemonDefeat (DropEvent event) {

        if (!event.isPokemon()) return;

        Pokemon pokemon = ((PixelmonEntity) event.entity).getPokemon();
        if (NBTHelpers.isTotem(pokemon)) {

            if (RandomHelper.getRandomChance(ConfigGetters.zCrystalDropChance)) {

                event.addDrop(getStandardCrystalForPokemon(pokemon));

            }
            if (ConfigGetters.specialCrystalDropChance > 0) {

                ItemStack specialCrystal = getSpecialCrystalOrNull(pokemon);
                if (specialCrystal != null) {

                    if (RandomHelper.getRandomChance(ConfigGetters.specialCrystalDropChance)) {

                        event.addDrop(specialCrystal);

                    }

                }

            }

        }

    }

    private static ItemStack getSpecialCrystalOrNull (Pokemon pokemon) {

        if (pokemon.getSpecies().getName().equalsIgnoreCase("Raichu")) {

            if (pokemon.getForm().isAlolan()) {

                return new ItemStack(PixelmonItems.aloraichium_z, 1);

            }

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Decidueye")) {

            if (!pokemon.getForm().isHisuian()) {

                return new ItemStack(PixelmonItems.decidium_z, 1);

            }

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Eevee")) {

            return new ItemStack(PixelmonItems.eevium_z, 1);

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Incineroar")) {

            return new ItemStack(PixelmonItems.incinium_z, 1);

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Kommo-o") || pokemon.getSpecies().getName().equalsIgnoreCase("Kommoo")) {

            return new ItemStack(PixelmonItems.kommonium_z, 1);

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Lunala")) {

            return new ItemStack(PixelmonItems.lunalium_z, 1);

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Lycanroc")) {

            return new ItemStack(PixelmonItems.lycanium_z, 1);

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Marshadow")) {

            return new ItemStack(PixelmonItems.marshadium_z, 1);

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Mew")) {

            return new ItemStack(PixelmonItems.mewnium_z, 1);

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Mimikyu")) {

            return new ItemStack(PixelmonItems.mimikium_z, 1);

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Pikachu")) {

            if (RandomHelper.getRandomChance(20)) {

                return new ItemStack(PixelmonItems.pikashunium_z, 1);

            } else {

                return new ItemStack(PixelmonItems.pikanium_z, 1);

            }

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Primarina")) {

            return new ItemStack(PixelmonItems.primarium_z, 1);

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Snorlax")) {

            return new ItemStack(PixelmonItems.snorlium_z, 1);

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Solgaleo")) {

            return new ItemStack(PixelmonItems.solganium_z, 1);

        } else if (pokemon.getSpecies().getName().contains("Tapu")) {

            return new ItemStack(PixelmonItems.tapunium_z, 1);

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Necrozma")) {

            if (pokemon.getForm().getName().contains("Dusk")) {

                return new ItemStack(PixelmonItems.solganium_z, 1);

            } else if (pokemon.getForm().getName().contains("Dawn")) {

                return new ItemStack(PixelmonItems.lunalium_z, 1);

            } else if (pokemon.getForm().getName().contains("Ultra")) {

                return new ItemStack(PixelmonItems.ultranecrozium_z, 1);

            }

        }

        return null;

    }

    // I don't feel like fucking with this right now, but if enough people request it as a feature then I can always revisit it
    /*private static List<String> getEvolutionLine (Pokemon pokemon) {

        List<String> species = new ArrayList<>();
        if (pokemon.getSpecies().getName().contains("Kommo")) {

            species.add("Jangmo-o");
            species.add("Hakamo-o");

        } else if (pokemon.getSpecies().getName().contains("Jangmo")) {

            species.add("Kommo-o");
            species.add("Hakamo-o");

        } else if (pokemon.getSpecies().getName().contains("Hakamo")) {

            species.add("Kommo-o");
            species.add("Jangmo-o");

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Snorlax")) {

            species.add("Munchlax");

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Munchlax")) {

            species.add("Snorlax");

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Litten")) {

            species.add("Torracat");
            species.add("Incineroar");

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Torracat")) {

            species.add("Litten");
            species.add("Incineroar");

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Incineroar")) {

            species.add("Litten");
            species.add("Torracat");

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Rowlet")) {

            species.add("Dartrix");
            species.add("Decidueye");

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Dartrix")) {

            species.add("Rowlet");
            species.add("Decidueye");

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Decidueye")) {

            species.add("Rowlet");
            species.add("Dartrix");

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Popplio")) {

            species.add("Brionne");
            species.add("Primarina");

        } else if (pokemon.getSpecies().getName().equalsIgnoreCase("Brionne")) {

            species.add("Popplio");
            species.add("Primarina");

        }

    }*/

    private static ItemStack getStandardCrystalForPokemon (Pokemon pokemon) {

        Element type = pokemon.getForm().getTypes().get(0);
        switch (type) {

            case BUG:
                return new ItemStack(PixelmonItems.buginium_z, 1);

            case DARK:
                return new ItemStack(PixelmonItems.darkinium_z, 1);

            case DRAGON:
                return new ItemStack(PixelmonItems.dragonium_z, 1);

            case ELECTRIC:
                return new ItemStack(PixelmonItems.electrium_z, 1);

            case FAIRY:
                return new ItemStack(PixelmonItems.fairium_z, 1);

            case FIGHTING:
                return new ItemStack(PixelmonItems.fightinium_z, 1);

            case FIRE:
                return new ItemStack(PixelmonItems.firium_z, 1);

            case FLYING:
                return new ItemStack(PixelmonItems.flyinium_z, 1);

            case GHOST:
                return new ItemStack(PixelmonItems.ghostium_z, 1);

            case GRASS:
                return new ItemStack(PixelmonItems.grassium_z, 1);

            case GROUND:
                return new ItemStack(PixelmonItems.groundium_z, 1);

            case ICE:
                return new ItemStack(PixelmonItems.icium_z, 1);

            case NORMAL:
            case MYSTERY:
            default:
                return new ItemStack(PixelmonItems.normalium_z, 1);

            case POISON:
                return new ItemStack(PixelmonItems.poisonium_z, 1);

            case PSYCHIC:
                return new ItemStack(PixelmonItems.psychium_z, 1);

            case ROCK:
                return new ItemStack(PixelmonItems.rockium_z, 1);

            case STEEL:
                return new ItemStack(PixelmonItems.steelium_z, 1);

            case WATER:
                return new ItemStack(PixelmonItems.waterium_z, 1);

        }

    }

}
