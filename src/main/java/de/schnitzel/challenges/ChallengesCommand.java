package de.schnitzel.challenges;

import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.plugin.java.JavaPlugin;


public class ChallengesCommand extends CommandAPICommand {

    public ChallengesCommand(String commandName) {
        super(commandName);

        executesPlayer((player, args) -> {
            try {
                ChallengesUI challengesUI = new ChallengesUI(Challenges.getInstance());
                player.openInventory(challengesUI.getInventory());
            } catch (Exception e) {
                player.sendMessage("Error: " + e.getMessage());
            }
        });

    }

}
