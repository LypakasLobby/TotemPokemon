package com.lypaka.totempokemon;

import com.lypaka.lypakautils.ConfigurationLoaders.BasicConfigManager;
import com.lypaka.lypakautils.ConfigurationLoaders.ConfigUtils;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("totempokemon")
public class TotemPokemon {

    public static final String MOD_ID = "totempokemon";
    public static final String MOD_NAME = "TotemPokemon";
    public static final Logger logger = LogManager.getLogger(MOD_NAME);
    public static BasicConfigManager configManager;

    public TotemPokemon() throws IOException {

        Path dir = ConfigUtils.checkDir(Paths.get("./config/totempokemon"));
        String[] files = new String[]{"totempokemon.conf"};
        configManager = new BasicConfigManager(files, dir, TotemPokemon.class, MOD_NAME, MOD_ID, logger);
        configManager.init();
        ConfigGetters.load();

    }

}
