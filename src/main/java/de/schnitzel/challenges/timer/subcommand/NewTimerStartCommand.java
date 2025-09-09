package de.schnitzel.challenges.timer.subcommand;

import de.schnitzel.challenges.Challenges;
import de.schnitzel.challenges.timer.MyTimer;
import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.World;


/**
 * timer Start Command
 * <p>
 * Nutzt die AdventureAPI (https://docs.advntr.dev/), um Deprecation zu vermeiden
 * nutzt "executesPlayer", um es nur durch Spieler ausführbar zu machen
 * <p>
 * Registriert wird er im NewTimerCommand.java → withSubcommand(new NewTimerStartCommand("start"));
 */

public class NewTimerStartCommand extends CommandAPICommand {
    public NewTimerStartCommand(String commandName) {
        super(commandName);

        executesPlayer((player, args) -> {
            MyTimer timer = Challenges.getInstance().timer;
            World world = player.getWorld();

            if (timer.isRunning()) {
                player.sendMessage(Component.text("Der timer läuft bereits!", NamedTextColor.RED));
                return;
            }

            world.setTime(1000);
            world.setClearWeatherDuration(1);

            timer.setTime(0);
            timer.setRunning(true);

            player.sendMessage(Component.text("Der timer wurde gestartet.", NamedTextColor.GREEN));
        });
    }
}
