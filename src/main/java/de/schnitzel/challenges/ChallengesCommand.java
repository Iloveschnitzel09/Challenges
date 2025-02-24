package de.schnitzel.challenges;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChallengesCommand implements CommandExecutor {

    private final de.schnitzel.challenges.Challenges Challenges;

    public ChallengesCommand(de.schnitzel.challenges.Challenges plugin) {
        this.Challenges = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                ChallengesUI challengesUI = new ChallengesUI(Challenges);
                player.openInventory(challengesUI.getInventory());
            } catch (Exception e) {
                player.sendMessage("" + e);
            }
        }
        return false;
    }
}
