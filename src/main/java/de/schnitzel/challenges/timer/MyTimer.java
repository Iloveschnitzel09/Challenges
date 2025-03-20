package de.schnitzel.challenges.timer;

import de.schnitzel.challenges.Challenges;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
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
                player.sendActionBar(Component.text("timer ist pausiert", NamedTextColor.RED));
                continue;
            }

            player.sendActionBar(Component.text(getTime(), NamedTextColor.GOLD, TextDecoration.BOLD));

        }
    }

}
