package de.schnitzel.challenges;

import de.schnitzel.challenges.Listener.BlockBreakListener;
import de.schnitzel.challenges.Listener.MobKillListener;
import de.schnitzel.challenges.Timer.MyTimer;
import de.schnitzel.challenges.Timer.NewTimerCommand;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Challenges extends JavaPlugin {
    public boolean[] data = {false, true, false, false, false, true, false, false, false};
    public MyTimer timer;

    @Override
    public void onEnable() {
        PluginManager manager = getServer().getPluginManager();

        manager.registerEvents(new BlockBreakListener(), this);
        manager.registerEvents(new MobKillListener(), this);
        manager.registerEvents(new ChallengesUI(this), this);

        new ChallengesCommand("challenges").register();
        new NewTimerCommand("timer").register();

        timer = new MyTimer(false, 0);

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