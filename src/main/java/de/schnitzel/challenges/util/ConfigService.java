package de.schnitzel.challenges.util;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigService {

    ChallengeData challengeData = ChallengeData.Companion.getInstance();

    public void load(JavaPlugin plugin) {
        ConfigurationSection sec = plugin.getConfig().getConfigurationSection("setting:");
        if (sec == null) return;

        challengeData.setRandomBlockDropsEnabled(sec.getBoolean("isRandomBlockDropsEnabled:"));
        challengeData.setSuperRandomBlockDropsEnabled(sec.getBoolean("isSuperRandomBlockDropsEnabled:"));
        challengeData.setRandomMobDropsEnabled(sec.getBoolean("isRandomMobDropsEnabled:"));
        challengeData.setSuperRandomMobDropsEnabled(sec.getBoolean("isSuperRandomMobDropsEnabled:"));
        challengeData.setRandomMobSpawnsEnabled(sec.getBoolean("isRandomMobSpawnsEnabled:"));
        challengeData.setSuperRandomMobSpawnsEnabled(sec.getBoolean("isSuperRandomMobSpawnsEnabled:"));
    }

    public void save(JavaPlugin plugin) {
        ConfigurationSection sec = plugin.getConfig().getConfigurationSection("setting:");
        if (sec == null) return;

        sec.set("isRandomBlockDropsEnabled:", challengeData.isRandomBlockDropsEnabled());
        sec.set("isSuperRandomBlockDropsEnabled:", challengeData.isSuperRandomBlockDropsEnabled());
        sec.set("isRandomMobDropsEnabled:", challengeData.isRandomMobDropsEnabled());
        sec.set("isSuperRandomMobDropsEnabled:", challengeData.isSuperRandomMobDropsEnabled());
        sec.set("isRandomMobSpawnsEnabled:", challengeData.isRandomMobSpawnsEnabled());
        sec.set("isSuperRandomMobSpawnsEnabled:", challengeData.isSuperRandomMobSpawnsEnabled());

        plugin.saveConfig();
    }
}
