package de.schnitzel.challenges.listener;

import de.schnitzel.challenges.Challenges;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;


public class OnPhantomRideListener implements Listener {

    @EventHandler
    public void onPhantomRide(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();
        Player player = e.getPlayer();

        if (entity instanceof Phantom p) {
            entity.addPassenger(player);
            entity.setGravity(false);
            p.setAggressive(false);
            new PhantomBukkitRunnable(entity, player).runTaskTimer(Challenges.getInstance(), 0, 1);
        }
    }

}
