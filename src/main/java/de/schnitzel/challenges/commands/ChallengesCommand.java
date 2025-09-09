package de.schnitzel.challenges.commands;

import de.schnitzel.challenges.gui.NewChallengesMenu;
import dev.jorel.commandapi.CommandAPICommand;

public class ChallengesCommand extends CommandAPICommand {

    public ChallengesCommand(String commandName) {
        super(commandName);

        executesPlayer((player, args) -> {
            try {
                NewChallengesMenu challengesMenu = new NewChallengesMenu();
                challengesMenu.show(player);
            } catch (Exception e) {
                player.sendMessage("Error: " + e.getMessage());
            }
        });

    }

}
