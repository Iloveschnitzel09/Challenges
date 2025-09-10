package de.schnitzel.challenges;

import de.schnitzel.challenges.commands.ChallengesCommand;
import de.schnitzel.challenges.listener.BlockBreakListener;
import de.schnitzel.challenges.listener.MobKillListener;
import de.schnitzel.challenges.listener.OnPhantomRideListener;
import de.schnitzel.challenges.timer.MyTimer;
import de.schnitzel.challenges.timer.NewTimerCommand;
import de.schnitzel.challenges.util.ConfigService;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Challenges extends JavaPlugin {
    public MyTimer timer;
    ConfigService configService = new ConfigService();

    public static Challenges getInstance() {
        return getPlugin(Challenges.class);
    }

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
        configService.load(this);
    }

    @Override
    public void onDisable() {
        getConfig().set("timer.time", timer.getTime());
        configService.save(this);

        getLogger().info("Alles gestoppt!"); // Geile nachricht ~Red
    }
}