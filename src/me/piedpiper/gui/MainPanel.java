package me.piedpiper.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Ezen a penelen jelennek meg a jatek elemeihez tartozo panelek
 */
public class MainPanel extends JPanel {
    /**
     * A jatek elemeihez tartozo panelek
     */
    private final static ArrayList<JPanel>  graphicObjects = new ArrayList<>();
    /**
     * A view amihez tartozik
     */
    private final View view;
    /**
     * A háttér panel
     */
    private final JPanel backGround = new JPanel();

    /**
     * konstruktor, inicializalja a tagvaltozokat
     */
    public MainPanel(View view){
        this.setLayout(null);
        this.view = view;
        backGround.setSize(new Dimension(1000, 492));
        backGround.setBackground(new Color(61, 45, 182,255));

    }

    /**
     * Kirajzolja a panel-t
     */
    @Override
    public void paint(Graphics g) {
        g.clearRect(0,0,1000,492);
        super.paint(g);

        for(int i = graphicObjects.size()-1; i >= 0; --i){
            this.add(graphicObjects.get(i));
            graphicObjects.get(i).setLocation(((IPosGettable)graphicObjects.get(i)).GetPosX(),  ((IPosGettable)graphicObjects.get(i)).GetPosY());

        }
        this.add(backGround);
    }

    /**
     * Hozzaad egy uj megjelenitendo elemet
     */
    public void AddGraphicObject(JPanel object){
        graphicObjects.add(object);
        object.addMouseListener(new OrbitingObjectMouseListener(view));
        repaint();
    }
    /**
     * Hozzaad egy uj megjelenitendo elemet, a megadott helyre a listaban
     */
    public void AddGraphicObject(JPanel object, int index){
        graphicObjects.add(index, object);
        object.addMouseListener(new OrbitingObjectMouseListener(view));
        repaint();
    }
    /**
     * Eltavolitja a megadott elemet megjelenitendo panelek kozul
     */
    public void RemoveGraphicObject(JPanel object){
        this.remove(object);
        graphicObjects.remove(object);
        repaint();
    }
    /**
     * Frissiti a szukseges elemeket
     */
    public void RemoveAllGraphicObject(){
        this.removeAll();
        graphicObjects.clear();
        repaint();
    }

    /**
     * Visszater a graphicObjects ertekevel
     */
    public ArrayList<JPanel> GetGraphicObjects() {
        return graphicObjects;
    }
}
