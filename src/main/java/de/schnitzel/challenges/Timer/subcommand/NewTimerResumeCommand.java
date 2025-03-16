package de.schnitzel.challenges.Timer.subcommand;

import de.schnitzel.challenges.Challenges;
import de.schnitzel.challenges.Timer.MyTimer;
import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;


public class NewTimerResumeCommand extends CommandAPICommand {
    public NewTimerResumeCommand(String commandName) {
        super(commandName);

        executesPlayer((player, args) -> {
            MyTimer timer = Challenges.getInstance().timer;

            if (timer.isRunning()) {
                player.sendMessage(Component.text("Der Timer läuft bereits.", NamedTextColor.RED));
                return;
            }
            timer.setRunning(true);
            player.sendMessage(Component.text("Der Timer wurde fortgesetzt.", NamedTextColor.GREEN));
        });
    }
}
