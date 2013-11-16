package com.github.riking.mcmmotop;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import com.gmail.nossr50.datatypes.skills.SkillType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Config {
    private FileConfiguration config;
    private Map<SkillType, Integer> countMap;

    public Config(MTPPlugin plugin) throws ConfigurationVerifyException {
        config = plugin.getConfig();
        init();
    }

    protected void init() throws ConfigurationVerifyException {
        List<String> reason = Lists.newArrayList();

        if (config.getInt("MinLevel") < 0) {
            reason.add("MinLevel cannot be less than 0");
        }

        if (config.getInt("RefreshInterval") <= 0) {
            reason.add("RefreshInterval cannot be 0");
        }

        // Cap
        if (config.getInt("FirstRefreshDelay") < config.getInt("RefreshInterval") * 20) {
            config.set("FirstRefreshDelay", config.getInt("RefreshInterval") * 20);
        }

        // Calculate
        countMap = Maps.newHashMap();
        ConfigurationSection section = config.getConfigurationSection("Count");
        int def = -1;
        for (String key : section.getKeys(false)) {
            if (key.equalsIgnoreCase("Default")) {
                def = section.getInt(key);
            } else if (key.equalsIgnoreCase("Overall")) {
                countMap.put(null, config.getInt(key));
            } else {
                SkillType type = SkillType.getSkill(key);
                if (type == null) {
                    reason.add(String.format("%s is not a valid SkillType", key));
                    continue;
                }
                countMap.put(type, config.getInt(key));
            }
        }
        if (def == -1) {
            reason.add("Count.Default must be specified");
        }

        if (!countMap.containsKey(null)) {
            countMap.put(null, def);
        }
        for (SkillType type : SkillType.values()) {
            if (!countMap.containsKey(type)) {
                countMap.put(type, def);
            }
        }

        // Throwing
        if (!reason.isEmpty()) {
            throw new ConfigurationVerifyException(reason);
        }
    }

    public int getCount(SkillType type) {
        return countMap.get(type).intValue();
    }

    public int getMinLevel() {
        return config.getInt("MinLevel");
    }

    public int getFirstRefreshDelay() {
        return config.getInt("FirstRefreshDelay");
    }

    public long getRefreshInterval(TimeUnit desired) {
        return TimeUnit.MINUTES.convert(config.getInt("RefreshInterval"), desired);
    }
}
