package de.schnitzel.challenges.listener;

import de.schnitzel.challenges.util.ChallengeData;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MobKillListener implements Listener {

    private final SecureRandom random = new SecureRandom();
    private final Map<EntityType, Material> item2drop = new HashMap<>();

    ChallengeData challengeData = ChallengeData.Companion.getInstance();

    @EventHandler
    public void onMobKill(EntityDeathEvent e) {
        Material[] materials = Arrays.stream(Material.values()).filter(Material::isItem).toArray(Material[]::new);
        if (challengeData.isRandomMobDropsEnabled()) {
            EntityType entity = e.getEntity().getType();

            e.getDrops().clear();

            if (item2drop.containsKey(entity)) {
                // Prüft, ob das Material bereits ein Item Typ hat
                e.getDrops().add(new ItemStack(item2drop.get(entity))); //wenn ja, spawnt die Item aus der Map
                return;
            }
            // Wähle ein zufälliges Material und speichere es
            Material randomMaterial = materials[random.nextInt(materials.length)];

            e.getDrops().add(new ItemStack(randomMaterial));
            item2drop.put(entity, randomMaterial); // Speichert die Zuordnung

        } else if (challengeData.isSuperRandomMobDropsEnabled()) {

            e.getDrops().clear();

            Material randomMaterial = materials[random.nextInt(materials.length)];

            e.getDrops().add(new ItemStack(randomMaterial));
        }
    }
}
