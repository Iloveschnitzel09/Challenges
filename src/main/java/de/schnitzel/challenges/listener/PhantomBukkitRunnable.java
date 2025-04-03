package de.schnitzel.challenges.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PhantomBukkitRunnable extends BukkitRunnable {

    private final Entity entity;
    private final Player player;

    public PhantomBukkitRunnable(Entity entity, Player player) {
        this.entity = entity;
        this.player = player;
    }

    @Override
    public void run() {
        if (entity.isDead() || entity.getPassengers().isEmpty()) {
            entity.setGravity(true);
            cancel();
            return;
        }
        // Bewegung durch Gucken
        entity.setVelocity(player.getLocation().getDirection().multiply(0.5));
        entity.setRotation(player.getLocation().getYaw(), -player.getLocation().getPitch());
        entity.setFallDistance(0);
    }
}
