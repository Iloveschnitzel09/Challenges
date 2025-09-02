package de.schnitzel.challenges;

import de.schnitzel.challenges.commands.ChallengesCommand;
import de.schnitzel.challenges.listener.BlockBreakListener;
import de.schnitzel.challenges.listener.MobKillListener;
import de.schnitzel.challenges.listener.OnPhantomRideListener;
import de.schnitzel.challenges.timer.MyTimer;
import de.schnitzel.challenges.timer.NewTimerCommand;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Challenges extends JavaPlugin {
    public MyTimer timer;

    @Override
    public void onEnable() {
        PluginManager manager = getServer().getPluginManager();

        manager.registerEvents(new BlockBreakListener(), this);
        manager.registerEvents(new MobKillListener(), this);
        manager.registerEvents(new OnPhantomRideListener(), this);

        new ChallengesCommand("challenges").register();
        new NewTimerCommand("timer").register();

        timer = new MyTimer();

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getConfig().set("timer.time", timer.getTime());

        saveConfig();

        getLogger().info("Alles gestoppt!"); // Geile nachricht
    }

    public static Challenges getInstance() {
        return getPlugin(Challenges.class);
    }
}