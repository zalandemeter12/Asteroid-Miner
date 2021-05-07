package me.piedpiper.gui;

import me.piedpiper.businesslogic.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

    /**
     * Elrendezest segito panel
     */
    private static final JPanel containerPanel = new JPanel(new BorderLayout());

    /**
     * A jatekbeli vilagot megjelenito panel
     */
    private static MainPanel mainPanel;

    /**
     * Az aktualis informaciokat szovegesen megjelenito panel
     */
    private static final JPanel infoPanel = new JPanel();

    /**
     * Az informacios resz bal oldali resze
     */
    private static final JPanel infoPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));

    /**
     * Az informacios resz jobb oldali resze
     */
    private static final JPanel infoPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    private static final JPanel activeSettlerInfoPanel = new JPanel();
    private static final JLabel activeSettlerLabel = new JLabel("Active Settler: Settler1");
    private static final JLabel activeSettlerBackpackLabel = new JLabel("Backpack: Ice: 0, Uran: 0, Coal: 0, Iron: 0");
    private static final JLabel activeSettlerGateInvLabel = new JLabel("Gate inventory: 0");

    private static final JPanel clickedObjectInfoPanel = new JPanel();
    private static final JLabel clickedObjectLabel = new JLabel("Clicked object: Null");
    private static final JLabel clickedObjectThicknessLabel = new JLabel("Thickness: -");
    private static final JLabel clickedObjectMaterialLabel = new JLabel("Materials: -");

    private static final JButton drillButton = new JButton("Drill");
    private static final JButton mineButton = new JButton("Mine");
    private static final JButton moveButton = new JButton("Move");
    private static final JButton placeMaterialButton = new JButton("Place material");
    private static final JButton placeGateButton = new JButton("Place gate");
    private static final JButton skipButton = new JButton("Skip");
    private static final JButton buildGateButton = new JButton("Build gate");
    private static final JButton buildRobotButton = new JButton("Build robot");

    private static final Color grayColor = new Color(57, 57, 57);
    private static final Color almostWhite = new Color(230, 230, 230);
    private static final Color spaceBlue = new Color(61, 45, 182);

    private Game game;
    private OrbitingObject selectedObject = null;
    private Handlers handlers;

    public View(Game game) {
        super("Asteroid miner");

        this.handlers = new Handlers(this);
        this.game = game;
        this.setMinimumSize(new Dimension(1000, 650));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        mainPanel = new MainPanel(this);

        newMenuItem.addActionListener(e -> handlers.NewClicked());

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


        drillButton.addActionListener(e -> handlers.DrillClicked());
        mineButton.addActionListener(e -> handlers.MineClicked());
        moveButton.addActionListener(e -> handlers.MoveClicked());
        placeMaterialButton.addActionListener(e -> handlers.PlaceMaterialClicked());
        placeGateButton.addActionListener(e -> handlers.PlaceGateClicked());
        skipButton.addActionListener(e -> handlers.SkipClicked());
        buildRobotButton.addActionListener(e -> handlers.BuildRobotClicked());
        buildGateButton.addActionListener(e -> handlers.BuildGateClicked());

        JPanel buttonsFirstRow = new JPanel(new FlowLayout());
        JPanel buttonsSecondRow = new JPanel(new FlowLayout());
        buttonsFirstRow.setBackground(grayColor);
        buttonsSecondRow.setBackground(grayColor);

        infoPanelRight.setLayout(new BoxLayout(infoPanelRight, BoxLayout.PAGE_AXIS));
        infoPanelRight.add(buttonsFirstRow);
        infoPanelRight.add(buttonsSecondRow);


        buttonsFirstRow.add(drillButton);
        buttonsFirstRow.add(mineButton);
        buttonsFirstRow.add(moveButton);
        buttonsFirstRow.add(placeMaterialButton);
        buttonsSecondRow.add(placeGateButton);
        buttonsSecondRow.add(buildGateButton);
        buttonsSecondRow.add(buildRobotButton);
        buttonsSecondRow.add(skipButton);

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

    public void Refresh(){
        if (selectedObject != null) {
            clickedObjectLabel.setText("Clicked object: " + selectedObject.GetName());
            if(selectedObject.getClass() == Asteroid.class){
                clickedObjectThicknessLabel.setText("Thickness: " +selectedObject.GetThickness());
                if(selectedObject.GetThickness() > 0){
                    clickedObjectMaterialLabel.setText("Material: Unknown");
                }else if(selectedObject.GetMaterial() != null){
                    clickedObjectMaterialLabel.setText("Material: " + selectedObject.GetMaterial().GetName());
                }else{
                    clickedObjectMaterialLabel.setText("Material: -");
                }
            } else if (selectedObject.getClass() == BaseAsteroid.class) {
                ArrayList<Material> irons = new ArrayList<>();
                for (int i = 0; i < 3; ++i) irons.add(new Iron());
                BillOfMaterials ironBill = new BillOfMaterials(irons);
                for(Material m: selectedObject.GetChest()) ironBill.IsNeeded(m);

                ArrayList<Material> urans = new ArrayList<>();
                for (int i = 0; i < 3; ++i) urans.add(new Uran());
                BillOfMaterials uranBill = new BillOfMaterials(urans);
                for(Material m: selectedObject.GetChest()) uranBill.IsNeeded(m);

                ArrayList<Material> coals = new ArrayList<>();
                for (int i = 0; i < 3; ++i) coals.add(new Coal());
                BillOfMaterials coalBill = new BillOfMaterials(coals);
                for(Material m: selectedObject.GetChest()) coalBill.IsNeeded(m);

                ArrayList<Material> ices = new ArrayList<>();
                for (int i = 0; i < 3; ++i) ices.add(new Ice());
                BillOfMaterials iceBill = new BillOfMaterials(ices);
                for(Material m: selectedObject.GetChest()) iceBill.IsNeeded(m);

                clickedObjectThicknessLabel.setText("Materials: Ice: " + (3-iceBill.GetBill().size()) +", Uran: " + (3-uranBill.GetBill().size()) + ", Coal: " + (3-coalBill.GetBill().size()) + ", Iron: " + (3-ironBill.GetBill().size()));
                clickedObjectMaterialLabel.setText(" ");

            } else if (selectedObject.getClass() == TeleportGate.class) {
                if (((TeleportGate)selectedObject).GetPair().GetPosition() == null) {
                    clickedObjectThicknessLabel.setText("Pair: Not active");
                } else {
                    clickedObjectThicknessLabel.setText("Pair: " + ((TeleportGate)selectedObject).GetPair().GetName());
                }
                clickedObjectMaterialLabel.setText(" ");
            } else {
                clickedObjectThicknessLabel.setText("Thickness: -");
                clickedObjectMaterialLabel.setText("Material: -");
            }

        } else {
            clickedObjectLabel.setText("Clicked object: Null");
        }
        Settler activeSettler = game.GetField().GetActiveSettler();
        activeSettlerLabel.setText("Active Settler: " + activeSettler.GetName());

        ArrayList<Material> irons = new ArrayList<>();
        for (int i = 0; i < 10; ++i) irons.add(new Iron());
        BillOfMaterials ironBill = new BillOfMaterials(irons);
        for(Material m: activeSettler.GetBackpack()) ironBill.IsNeeded(m);


        ArrayList<Material> urans = new ArrayList<>();
        for (int i = 0; i < 10; ++i) urans.add(new Uran());
        BillOfMaterials uranBill = new BillOfMaterials(urans);
        for(Material m: activeSettler.GetBackpack()) uranBill.IsNeeded(m);

        ArrayList<Material> coals = new ArrayList<>();
        for (int i = 0; i < 10; ++i) coals.add(new Coal());
        BillOfMaterials coalBill = new BillOfMaterials(coals);
        for(Material m: activeSettler.GetBackpack()) coalBill.IsNeeded(m);

        ArrayList<Material> ices = new ArrayList<>();
        for (int i = 0; i < 10; ++i) ices.add(new Ice());
        BillOfMaterials iceBill = new BillOfMaterials(ices);
        for(Material m: activeSettler.GetBackpack()) iceBill.IsNeeded(m);

        activeSettlerBackpackLabel.setText("Backpack: Ice: " + (10-iceBill.GetBill().size()) +", Uran: " + (10-uranBill.GetBill().size()) + ", Coal: " + (10-coalBill.GetBill().size()) + ", Iron: " + (10-ironBill.GetBill().size()));
        activeSettlerGateInvLabel.setText("Gate inventory: " + activeSettler.GetGateInventory().size());
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

    public void ObjectClicked(OrbitingObject o){
        selectedObject = o;
        Refresh();
    }
}
