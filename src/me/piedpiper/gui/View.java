package me.piedpiper.gui;

import me.piedpiper.businesslogic.Game;
import me.piedpiper.businesslogic.OrbitingObject;

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

    private static final JPanel containerPanel = new JPanel(new BorderLayout());

    private static MainPanel mainPanel;

    private static final JPanel infoPanel = new JPanel();
    private static final JPanel infoPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private static final JPanel infoPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    private static final JPanel activeSettlerInfoPanel = new JPanel();
    private static final JLabel activeSettlerLabel = new JLabel("Active Settler: Settler1");
    private static final JLabel activeSettlerBackpackLabel = new JLabel("Backpack: Ice: 1, Uran:2, Coal: 0, Iron: 0");
    private static final JLabel activeSettlerGateInvLabel = new JLabel("Gate inventory: 2");

    private static final JPanel clickedObjectInfoPanel = new JPanel();
    private static final JLabel clickedObjectLabel = new JLabel("Clicked object: Asteroid2");
    private static final JLabel clickedObjectThicknessLabel = new JLabel("Thickness: 2");
    private static final JLabel clickedObjectMaterialLabel = new JLabel("Material: Unknown");

    private static final JButton drillButton = new JButton("Drill");
    private static final JButton mineButton = new JButton("Mine");
    private static final JButton moveButton = new JButton("Move");
    private static final JButton placeMaterialButton = new JButton("Place material");
    private static final JButton placeGateButton = new JButton("Place gate");
    private static final JButton skipButton = new JButton("Skip");

    private static final Color grayColor = new Color(57, 57, 57);
    private static final Color almostWhite = new Color(230, 230, 230);
    private static final Color spaceBlue = new Color(61, 45, 182);

    private Game game;
    private OrbitingObject selectedObject = null;

    public View(Game game){
        super("Asteroid miner");

        this.game = game;
        this.setMinimumSize(new Dimension(1000, 600));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel = new MainPanel();

        gameMenu.add(newMenuItem);
        gameMenu.add(openMenuItem);
        gameMenu.add(saveMenuItem);
        menuBar.add(gameMenu);
        menuContainer.add(menuBar);


        infoPanel.setBackground(grayColor);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.LINE_AXIS));

        infoPanelLeft.setBackground(grayColor);
        infoPanelRight.setBackground(grayColor);
        infoPanel.add(infoPanelLeft);
        infoPanel.add(infoPanelRight);

        activeSettlerInfoPanel.setLayout(new BoxLayout(activeSettlerInfoPanel, BoxLayout.PAGE_AXIS));
        activeSettlerLabel.setForeground(almostWhite);
        activeSettlerBackpackLabel.setForeground(almostWhite);
        activeSettlerGateInvLabel.setForeground(almostWhite);
        activeSettlerInfoPanel.add(activeSettlerLabel);
        activeSettlerInfoPanel.add(activeSettlerBackpackLabel);
        activeSettlerInfoPanel.add(activeSettlerGateInvLabel);
        activeSettlerInfoPanel.setBackground(grayColor);


        clickedObjectInfoPanel.setLayout(new BoxLayout(clickedObjectInfoPanel, BoxLayout.PAGE_AXIS));
        clickedObjectLabel.setForeground(almostWhite);
        clickedObjectThicknessLabel.setForeground(almostWhite);
        clickedObjectMaterialLabel.setForeground(almostWhite);
        clickedObjectInfoPanel.add(clickedObjectLabel);
        clickedObjectInfoPanel.add(clickedObjectThicknessLabel);
        clickedObjectInfoPanel.add(clickedObjectMaterialLabel);
        clickedObjectInfoPanel.setBackground(grayColor);

        infoPanelLeft.add(activeSettlerInfoPanel);
        infoPanelLeft.add(clickedObjectInfoPanel);


        infoPanelRight.add(drillButton);
        infoPanelRight.add(mineButton);
        infoPanelRight.add(moveButton);
        infoPanelRight.add(placeMaterialButton);
        infoPanelRight.add(placeGateButton);
        infoPanelRight.add(skipButton);

        mainPanel.setBackground(spaceBlue);
        menuContainer.setBackground(spaceBlue);

        containerPanel.add(menuContainer, BorderLayout.NORTH);
        containerPanel.add(infoPanel, BorderLayout.SOUTH);
        containerPanel.add(mainPanel, BorderLayout.CENTER);
        this.add(containerPanel);
    }


    public void AddGraphicObject(JPanel p){
        mainPanel.AddGraphicObject(p);
    }

    public void RemoveGraphicObject(JPanel p){
        mainPanel.RemoveGraphicObject(p);
    }
    public void Repaint(){

    }

    public Game GetGame() {
        return game;
    }

    public void SetGame(Game game) {
        this.game = game;
    }

    public OrbitingObject GetSelectedObject() {
        return selectedObject;
    }

    public void SetSelectedObject(OrbitingObject selectedObject) {
        this.selectedObject = selectedObject;
    }
}
