package me.piedpiper.gui;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private static final JPanel menuContainer = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
    /**
     * Menubar, ami tartalmazza a menut
     */
    private static final JMenuBar menuBar = new JMenuBar();

    /**
     * Jatek menu
     */
    private static final JMenu  gameMenu = new JMenu("Game");

    /**
     * Menupont, amivel uj jatekot lehet kezdeni
     */
    private static final JMenuItem  newMenuItem = new JMenuItem("New game");

    /**
     * Menupont, amivel meg lehet nyitni mentett jatekokat
     */
    private static final JMenuItem  openMenuItem = new JMenuItem("Open game");

    /**
     * Menupont, amivel el lehet menteni az aktualis jatekot
     */
    private static final JMenuItem  saveMenuItem = new JMenuItem("Save game");

    public View(){
        super("Asteroid miner");
        this.setMinimumSize(new Dimension(1000, 500));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameMenu.add(newMenuItem);
        gameMenu.add(openMenuItem);
        gameMenu.add(saveMenuItem);
        menuBar.add(gameMenu);
        menuContainer.add(menuBar);
        this.add(menuContainer);
    }
}
