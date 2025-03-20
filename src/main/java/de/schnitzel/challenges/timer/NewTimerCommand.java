package de.schnitzel.challenges.timer;

import de.schnitzel.challenges.timer.subcommand.*;
import dev.jorel.commandapi.CommandAPICommand;


public class NewTimerCommand extends CommandAPICommand {
    public NewTimerCommand(String commandName) {
        super(commandName);

        withPermission("challenges.timer.command");
        withSubcommands(
                new NewTimerStartCommand("start"),
                new NewTimerStopCommand("stop"),
                new NewTimerPauseCommand("pause"),
                new NewTimerResetCommand("reset"),
                new NewTimerResumeCommand("resume")
        );
    }
}
