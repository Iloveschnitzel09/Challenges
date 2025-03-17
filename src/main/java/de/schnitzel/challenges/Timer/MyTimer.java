package de.schnitzel.challenges.Timer;

import de.schnitzel.challenges.Challenges;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
public class MyTimer extends BukkitRunnable {
    private boolean running;
    private int time;

    public MyTimer() {
        @NotNull FileConfiguration config = Challenges.getInstance().getConfig();
        this.running = false;
        this.time = config.getInt("timer.time", 0);

        run();
    }

    @Override
    public void run() {
        sendActionBar();

        if (!isRunning()) {
            this.runTaskTimer(Challenges.getInstance(), 20, 20);
            return;
        }

        setTime(getTime() + 1);
    }

    public void sendActionBar() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!isRunning()) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Timer ist pausiert"));
                continue;
            }

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD.toString() + ChatColor.BOLD + getTime()));
        }
    }

}
