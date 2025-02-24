package de.schnitzel.challenges.Timer;

import de.schnitzel.challenges.Challenges;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TimerCommand implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        MyTimer timer = Challenges.getInstance().getTimer();

        if(args.length == 0){
            sendHelp(sender);
            return true;
        }

        switch (args[0].toLowerCase()){
            case "start":
                if (timer.isRunning()) {
                    sender.sendMessage(ChatColor.RED + "Der timer läuft bereits.");
                    break;
                }
                Player player = (Player) sender;
                World world = player.getLocation().getWorld();
                world.setTime(1000);
                timer.setTime(0);
                world.setClearWeatherDuration(1);

                timer.setRunning(true);
                sender.sendMessage(ChatColor.GREEN + "Der timer wurde gestartet.");
                break;
            case "stop", "pause":
                if (!timer.isRunning()) {
                    sender.sendMessage(ChatColor.RED + "Der timer läuft nicht.");
                    break;
                }
                timer.setRunning(false);
                sender.sendMessage(ChatColor.GREEN + "Der timer wurde gestoppt.");
                break;
            case "reset":
                timer.setRunning(false);
                timer.setTime(0);
                sender.sendMessage(ChatColor.GREEN + "Der timer wurde zurückgesetzt.");
                break;
            case "resume":
                if (timer.isRunning()) {
                    sender.sendMessage(ChatColor.RED + "Der timer läuft bereits.");
                    break;
                }
                timer.setRunning(true);
                sender.sendMessage(ChatColor.GREEN + "Der timer wurde gestartet.");
                break;
            default:
                sendHelp(sender);
                break;
        }
        return false;
    }

    private void sendHelp(CommandSender sender){
        sender.sendMessage("Bitte benutze /timer <start/stop/reset/pause/resume>");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return List.of();
    }
}
