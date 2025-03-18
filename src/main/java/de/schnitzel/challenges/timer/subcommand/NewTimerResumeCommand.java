package de.schnitzel.challenges.timer.subcommand;

import de.schnitzel.challenges.Challenges;
import de.schnitzel.challenges.timer.MyTimer;
import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;


public class NewTimerResumeCommand extends CommandAPICommand {
    public NewTimerResumeCommand(String commandName) {
        super(commandName);

        executesPlayer((player, args) -> {
            MyTimer timer = Challenges.getInstance().timer;

            if (timer.isRunning()) {
                player.sendMessage(Component.text("Der timer läuft bereits.", NamedTextColor.RED));
                return;
            }
            timer.setRunning(true);
            player.sendMessage(Component.text("Der timer wurde fortgesetzt.", NamedTextColor.GREEN));
        });
    }
}
