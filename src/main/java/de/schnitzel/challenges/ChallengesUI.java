package de.schnitzel.challenges;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
        this.inventory = plugin.getServer().createInventory(this, 27, Component.text("Challenge Auswahl"));

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

            meta.itemName(Component.text(" "));
            meta.lore(null);

            item.setItemMeta(meta);
            this.inventory.setItem(i, item);
        }
    }

    public void setMeta(String _meta, String lore, int i) {
        meta.itemName(Component.text(_meta, NamedTextColor.BLUE));
        meta.lore(List.of(Component.text(lore, NamedTextColor.GRAY)));

        /*if (plugin.data[i - 9]) {
            item = new ItemStack(Material.GREEN_DYE);
        } else {
            item = new ItemStack(Material.RED_DYE);
        }*/
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
                        //String name = PlainTextComponentSerializer.plainText().serialize(meta.itemName());
                        List<Component> lore = meta.lore();

                       /* plugin.data[i] = !plugin.data[i];

                        if (!plugin.data[i]) {
                            item = new ItemStack(Material.RED_DYE);
                        } else {
                            item = new ItemStack(Material.GREEN_DYE);
                        }
                        System.out.println(i + " " + plugin.data[i]);

                        //meta.itemName(Component.text(name));
                        meta.lore(lore);
                        item.setItemMeta(meta);

                        e.getInventory().setItem(i + 9, item);
                        */e.setCancelled(true);
                    }
                }
            }
        }
    }
}
