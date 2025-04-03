package de.schnitzel.challenges.listener;

import de.schnitzel.challenges.ChallengeData;
import de.schnitzel.challenges.Challenges;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BlockBreakListener implements Listener {

    private final SecureRandom random = new SecureRandom();
    private final Map<Material, Material> item2drop = new HashMap<>();
    private final Object2ObjectMap<Material, EntityType> entityMaterials = new Object2ObjectOpenHashMap<>();

    ChallengeData challengeData = ChallengeData.Companion.getInstance();
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (challengeData.isRandomBlockDropsEnabled()) {
            Material material = e.getBlock().getType();
            Location location = e.getBlock().getLocation();

            e.setDropItems(false); // Verhindert normale Drops

            if (item2drop.containsKey(material)) {
                // Prüft, ob das Material bereits ein Item Typ hat
                location.getWorld().dropItem(location, new ItemStack(item2drop.get(material))); //wenn ja, spawnt die Item aus der Map
            } else {
                // Wähle ein zufälliges Material und speichere es
                Material[] materials = Arrays.stream(Material.values()).filter(Material::isItem).toArray(Material[]::new);
                Material randomMaterial = materials[random.nextInt(materials.length)];

                item2drop.put(material, randomMaterial); // Speichert die Zuordnung
                location.getWorld().dropItem(location, new ItemStack(randomMaterial));
            }
        } else if (challengeData.isSuperRandomBlockDropsEnabled()) {

            Location location = e.getBlock().getLocation();

            e.setDropItems(false); // Verhindert normale Drops

            Material[] materials = Arrays.stream(Material.values()).filter(Material::isItem).toArray(Material[]::new);
            Material randomMaterial = materials[random.nextInt(materials.length)];

            location.getWorld().dropItem(location, new ItemStack(randomMaterial));
        }
        if (challengeData.isRandomMobSpawnsEnabled()) {
            Material material = e.getBlock().getType();
            Location location = e.getBlock().getLocation();
            EntityType selected = Arrays.stream(EntityType.values()).toList().get(random.nextInt(EntityType.values().length)); // Wenn nicht, sucht er sich eine random entity aus Entity.values()

            location.add(0.5, 0, 0.5);

            if (entityMaterials.containsKey(material)) { // Prüft, ob das Material bereits ein Entity Typ hat
                location.getWorld().spawnEntity(location, entityMaterials.get(material)); //wenn ja, spawnt die entity aus der Map
            } else {

                location.getWorld().spawnEntity(location, selected); // -> Spawnt auch diese

                entityMaterials.put(material, selected); // → Da es ja noch nicht vorhanden war, wird es nun reingesetzt
            }
        } else if (challengeData.isSuperRandomMobSpawnsEnabled()) {
            EntityType selected = Arrays.stream(EntityType.values()).toList().get(random.nextInt(EntityType.values().length)); // Wenn nicht, sucht er sich eine random entity aus Entity.values()
            Location location = e.getBlock().getLocation();
            Material material = e.getBlock().getType();

            location.getWorld().spawnEntity(location, selected); // -> Spawnt auch diese

            entityMaterials.put(material, selected); // → Da es ja noch nicht vorhanden war, wird es nun reingesetzt
        }
    }
}
