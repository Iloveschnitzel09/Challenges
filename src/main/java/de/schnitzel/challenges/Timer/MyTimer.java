package de.schnitzel.challenges.Timer;

import de.schnitzel.challenges.Challenges;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class MyTimer {
    private boolean running;
    private int time;

    public MyTimer(boolean running, int time) {
        @NotNull FileConfiguration config = Challenges.getInstance().getConfig();
        this.running = false;
        this.time = config.getInt("timer.time", 0);

        run();
    }

    public void sendActionBar(){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(!isRunning()){
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Timer ist Pausiert"));
                continue;
            }

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD.toString() + ChatColor.BOLD + getTime()));
        }
    }



    private void run(){
        new BukkitRunnable(){
            @Override
            public void run() {

                sendActionBar();

                if ( !isRunning()){
                    return;
                }

                setTime( getTime() + 1);
            }
        }.runTaskTimer(Challenges.getInstance(), 20, 20);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
