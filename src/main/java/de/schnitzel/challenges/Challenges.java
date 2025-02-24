package de.schnitzel.challenges;

import de.schnitzel.challenges.Listener.BlockBreakListener;
import de.schnitzel.challenges.Listener.MobKillListener;
import de.schnitzel.challenges.Timer.MyTimer;
import de.schnitzel.challenges.Timer.TimerCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Challenges extends JavaPlugin {

    public boolean[] data = {false, true, false, false, false, true, false, false, false};

    private static Challenges instance;

    private MyTimer timer;


    @Override
    public void onEnable() {
        instance = this;
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new BlockBreakListener(), this);
        manager.registerEvents(new MobKillListener(), this);
        manager.registerEvents(new ChallengesUI(this), this);
        this.getCommand("challenges").setExecutor(new ChallengesCommand(this));
        this.getCommand("timer").setExecutor(new TimerCommand());
        timer = new MyTimer(false,0);
        saveDefaultConfig();


        getLogger().info("Alles gestartet!");
    }

    @Override
    public void onDisable() {
        getConfig().set("timer.time", timer.getTime());
        saveConfig();
        getLogger().info("Alles gestoppt!");

    }
    public MyTimer getTimer() {
        return timer;
    }

    public static Challenges getInstance() {
        return instance;
    }
}
