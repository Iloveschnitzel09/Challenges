package de.schnitzel.challenges.Timer.subcommand;

import de.schnitzel.challenges.Challenges;
import de.schnitzel.challenges.Timer.MyTimer;
import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;


public class NewTimerStopCommand extends CommandAPICommand {
    public NewTimerStopCommand(String commandName) {
        super(commandName);

        executesPlayer((player, args) -> {
            MyTimer timer = Challenges.getInstance().timer;

            if (!timer.isRunning()) {
                player.sendMessage(Component.text("Der Timer läuft nicht.", NamedTextColor.RED));
                return;
            }
            timer.setRunning(false);

            player.sendMessage(Component.text("Der Timer wurde pausiert.", NamedTextColor.GREEN));
        });
    }
}
