package com.github.riking.mcmmotop;

import org.bukkit.plugin.java.JavaPlugin;

public class MTPPlugin extends JavaPlugin {
    public Config config;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        try {
            config = new Config(this);
        } catch (ConfigurationVerifyException ex) {
            getLogger().severe("Problems in the configuration! mcMMO Top Prefixes cannot start.");
            for (String reason : ex.problems) {
                getLogger().severe(reason);
            }
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }
}
