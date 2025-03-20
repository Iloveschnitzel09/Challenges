package de.schnitzel.challenges;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;

public class NewChallengesUI {
    static ChestGui gui = new ChestGui(5, "My GUI");

    public static ChestGui getGUI(){
        return gui;
    }
}
