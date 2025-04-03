package de.schnitzel.challenges

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder
import com.github.stefvanschie.inventoryframework.gui.GuiItem
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui
import com.github.stefvanschie.inventoryframework.pane.OutlinePane
import com.github.stefvanschie.inventoryframework.pane.Pane
import com.github.stefvanschie.inventoryframework.pane.StaticPane
import com.github.stefvanschie.inventoryframework.pane.util.Slot
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent

class NewChallengesMenu() : ChestGui(3, ComponentHolder.of(Component.text("Challenge Auswahl"))) {
    init {
        val data = ChallengeData.instance
        val pane = StaticPane(1, 1, 9, 1, Pane.Priority.HIGH)
        val outlinePane = OutlinePane(0, 0, 9, 3, Pane.Priority.LOW)

        pane.addItem(
            GuiItem(
                ItemBuilder(if (data.isRandomBlockDropsEnabled) Material.GREEN_DYE else Material.RED_DYE)
                    .setName(Component.text("Random Block Drops"))
                    .addLoreLine(Component.text("Jede Blockart hat einen anderen drop."))
                    .build()
            ), Slot.fromIndex(0)
        )
        pane.addItem(
            GuiItem(
                ItemBuilder(if (data.isSuperRandomBlockDropsEnabled) Material.GREEN_DYE else Material.RED_DYE)
                    .setName(Component.text("Super random Block drops"))
                    .addLoreLine(Component.text("Jeder Block hat einen anderen drop."))
                    .build()
            ), Slot.fromIndex(1)
        )
        pane.addItem(
            GuiItem(
                ItemBuilder(if (data.isRandomMobDropsEnabled) Material.GREEN_DYE else Material.RED_DYE)
                    .setName(Component.text("Random Mob drops"))
                    .addLoreLine(Component.text("Jede Mobart hat einen anderen drop."))
                    .build()
            ), Slot.fromIndex(2)
        )
        pane.addItem(
            GuiItem(
                ItemBuilder(if (data.isSuperRandomMobDropsEnabled) Material.GREEN_DYE else Material.RED_DYE)
                    .setName(Component.text("Super random Mob drops"))
                    .addLoreLine(Component.text("Jedes Mob hat einen anderen drop."))
                    .build()
            ), Slot.fromIndex(3)
        )
        pane.addItem(
            GuiItem(
                ItemBuilder(if (data.isRandomMobSpawnsEnabled) Material.GREEN_DYE else Material.RED_DYE)
                    .setName(Component.text("Random Mob spawns"))
                    .addLoreLine(Component.text("Jede Blockart spawnt einen anderen Mob."))
                    .build()
            ), Slot.fromIndex(4)
        )
        pane.addItem(
            GuiItem(
                ItemBuilder(if (data.isSuperRandomMobSpawnsEnabled) Material.GREEN_DYE else Material.RED_DYE)
                    .setName(Component.text("Super random Mob spawns"))
                    .addLoreLine(Component.text("jeder Block spawnt einen anderen Mob."))
                    .build()
            ), Slot.fromIndex(5)
        )

        outlinePane.addItem(
            GuiItem(
                ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                    .setName(Component.text(""))
                    .build()
            )
        )
        outlinePane.setRepeat(true)

        pane.setOnClick { event: InventoryClickEvent ->
            when (event.slot) {
                10 -> {
                    data.isRandomBlockDropsEnabled = !data.isRandomBlockDropsEnabled
                }

                10 + 1 -> {
                    data.isSuperRandomBlockDropsEnabled = !data.isSuperRandomBlockDropsEnabled
                }

                10 + 2 -> {
                    data.isRandomMobDropsEnabled = !data.isRandomMobDropsEnabled
                }

                10 + 3 -> {
                    data.isSuperRandomMobDropsEnabled = !data.isSuperRandomMobDropsEnabled
                }

                10 + 4 -> {
                    data.isRandomMobSpawnsEnabled = !data.isRandomMobSpawnsEnabled
                }

                10 + 5 -> {
                    data.isSuperRandomMobSpawnsEnabled = !data.isSuperRandomMobSpawnsEnabled
                }
            }
            NewChallengesMenu().show(event.whoClicked)
        }

        this.setOnGlobalClick { event: InventoryClickEvent ->
            event.isCancelled =
                true
        }
        this.setOnGlobalClick { event: InventoryClickEvent ->
            event.isCancelled =
                true
        }
        this.addPane(pane)
        this.addPane(outlinePane)
    }
}


