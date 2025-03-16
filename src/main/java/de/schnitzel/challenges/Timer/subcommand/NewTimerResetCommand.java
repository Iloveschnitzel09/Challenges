package de.schnitzel.challenges.Timer.subcommand;

import de.schnitzel.challenges.Challenges;
import de.schnitzel.challenges.Timer.MyTimer;
import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class NewTimerResetCommand extends CommandAPICommand {
    public NewTimerResetCommand(String commandName) {
        super(commandName);

        executesPlayer((player, args) -> {
            MyTimer timer = Challenges.getInstance().timer;

            timer.setRunning(false);
            timer.setTime(0);

            player.sendMessage(Component.text("Der Timer wurde zurückgesetzt.", NamedTextColor.GREEN));
        });
    }
}
