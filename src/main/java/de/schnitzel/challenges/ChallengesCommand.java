package de.schnitzel.challenges;

import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.entity.Player;

public class ChallengesCommand {

    public ChallengesCommand(Challenges plugin) {

        new CommandAPICommand("challenges")
            .executes((sender, args) -> {
                if (sender instanceof Player player) {
                    try {
                        ChallengesUI challengesUI = new ChallengesUI(plugin);
                        player.openInventory(challengesUI.getInventory());
                    } catch (Exception e) {
                        player.sendMessage("Error: " + e.getMessage());
                    }
                }
            })
            .register();
    }

}
