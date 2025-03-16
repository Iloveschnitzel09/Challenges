package de.schnitzel.challenges;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChallengesUI implements InventoryHolder, Listener {
    private ItemStack item = new ItemStack(Material.GREEN_DYE);
    private ItemMeta meta = item.getItemMeta();
    private final Inventory inventory;
    private final Challenges plugin;

    public ChallengesUI(Challenges plugin) {
        this.plugin = plugin;
        // Macht ein Inventar mit 27 Slots
        this.inventory = plugin.getServer().createInventory(this, 27, "Challenge Auswahl");
        for (int i = 0; i <= 26; i++) {
            if (i > 8 && i < 18) {
                switch (i) {
                    case 9 -> setMeta("Random Block drops", "Jede Blockart hat einen anderen drop.", i);
                    case 10 -> setMeta("Super random Block drops", "Jeder Block hat einen anderen drop.", i);
                    case 11 -> setMeta("Random Mob drops", "Jede Mobart hat einen anderen drop.", i);
                    case 12 -> setMeta("Super random Mob drops", "Jedes Mob hat einen anderen drop.", i);
                    case 13 -> setMeta("Random Mob spawns", "Jede Blockart spawnt einen anderen Mob.", i);
                    case 14 -> setMeta("Super random Mob spawns", "jeder Block spawnt einen anderen Mob.", i);
                }
                item.setItemMeta(meta);

                this.inventory.setItem(i, item);
                continue;
            }
            item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

            meta.setItemName(" ");
            meta.setLore(null);

            item.setItemMeta(meta);
            this.inventory.setItem(i, item);
        }
    }

    public void setMeta(String mate, String lore, int i) {
        meta.setItemName(ChatColor.BLUE + mate);
        meta.setLore(List.of(ChatColor.GRAY + lore));

        if (plugin.data[i - 9]) {
            item = new ItemStack(Material.GREEN_DYE);
        } else {
            item = new ItemStack(Material.RED_DYE);
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof ChallengesUI) {
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE) {

                e.setCancelled(true); // Blockiert das Klicken & Herausnehmen

            } else if (e.getCurrentItem() != null) {
                for (int i = 0; i < 9; i++) {
                    if (e.getSlot() == i + 9) {
                        meta = e.getCurrentItem().getItemMeta();
                        String name = meta.getItemName();
                        List<String> lore = meta.getLore();

                        plugin.data[i] = !plugin.data[i];

                        if (!plugin.data[i]) {
                            item = new ItemStack(Material.RED_DYE);
                        } else {
                            item = new ItemStack(Material.GREEN_DYE);
                        }
                        System.out.println(i + " " + plugin.data[i]);

                        meta.setItemName(name);
                        meta.setLore(lore);
                        item.setItemMeta(meta);

                        e.getInventory().setItem(i + 9, item);
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
