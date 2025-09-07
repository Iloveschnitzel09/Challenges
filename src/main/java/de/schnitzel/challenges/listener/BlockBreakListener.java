package de.schnitzel.challenges.listener;

import de.schnitzel.challenges.util.ChallengeData;
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

        Material mat = e.getBlock().getType();
        Location loc = e.getBlock().getLocation();

        if (challengeData.isRandomBlockDropsEnabled()) {
            randomBlockDrop(mat, loc, e);
        } else if (challengeData.isSuperRandomBlockDropsEnabled()) {
            superRandomBlockDrop(mat, loc, e);
        }

        if (challengeData.isRandomMobSpawnsEnabled()) {
            randomMobSpawn(mat, loc);
        } else if (challengeData.isSuperRandomMobSpawnsEnabled()) {
            superRandomMobSpawn(loc);
        }
    }

    public void randomBlockDrop(Material mat, Location loc, BlockBreakEvent e) {
        e.setDropItems(false); // Verhindert normale Drops

        if (item2drop.containsKey(mat)) {
            // Prüft, ob das Material bereits ein Item Typ hat
            loc.getWorld().dropItem(loc, new ItemStack(item2drop.get(mat))); //wenn ja, spawnt die Item aus der Map
            return;
        }

        // Wähle ein zufälliges Material und speichere es
        Material[] materials = Arrays.stream(Material.values()).filter(Material::isItem).toArray(Material[]::new);
        Material randomMaterial = materials[random.nextInt(materials.length)];

        item2drop.put(mat, randomMaterial); // Speichert die Zuordnung
        loc.getWorld().dropItem(loc, new ItemStack(randomMaterial));

    }

    public void superRandomBlockDrop(Material mat, Location loc, BlockBreakEvent e) {
        e.setDropItems(false); // Verhindert normale Drops

        Material randomMaterial;

        Material[] materials = Arrays.stream(Material.values()).filter(Material::isItem).toArray(Material[]::new);
        do {
            randomMaterial = materials[random.nextInt(materials.length)];
        } while (randomMaterial.isLegacy());
        loc.getWorld().dropItem(loc, new ItemStack(randomMaterial));
    }

    public void randomMobSpawn(Material mat, Location loc) {
        EntityType selected;

        do {
            selected = Arrays.stream(EntityType.values()).toList().get(random.nextInt(EntityType.values().length)); // Wenn nicht, sucht er sich eine random entity aus Entity.values()
        } while (!selected.isSpawnable());

        loc.add(0.5, 0, 0.5);

        if (entityMaterials.containsKey(mat)) { // Prüft, ob das Material bereits ein Entity Typ hat
            loc.getWorld().spawnEntity(loc, entityMaterials.get(mat));
            return;
        }

        loc.getWorld().spawnEntity(loc, selected); // -> Spawnt auch diese

        entityMaterials.put(mat, selected); // → Da es ja noch nicht vorhanden war, wird es nun reingesetzt

    }

    public void superRandomMobSpawn(Location loc) {
        EntityType selected;

        do {
            selected = Arrays.stream(EntityType.values()).toList().get(random.nextInt(EntityType.values().length)); // Wenn nicht, sucht er sich eine random entity aus Entity.values()
        } while (!selected.isSpawnable());

        loc.getWorld().spawnEntity(loc, selected); // -> Spawnt auch diese
    }
}
