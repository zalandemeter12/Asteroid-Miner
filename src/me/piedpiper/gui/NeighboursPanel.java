package me.piedpiper.gui;

import me.piedpiper.businesslogic.OrbitingObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NeighboursPanel extends JPanel implements IPosGettable{
    /**
     * Az egyik palyan keringo objektumok listaja
     */
    private final ArrayList<OrbitingObject> orbitingObjects1;
    /**
     * Az egyik palyan keringo objektumok listaja
     */
    private final ArrayList<OrbitingObject> orbitingObjects2;
    /**
     * Az egyik palyan keringo objektumok listaja
     */
    private final ArrayList<OrbitingObject> orbitingObjects3;

    /**
     * Konstruktor
     */
    public NeighboursPanel(ArrayList<OrbitingObject> orbitingObjects1, ArrayList<OrbitingObject> orbitingObjects2, ArrayList<OrbitingObject> orbitingObjects3){
        setSize(new Dimension(1000, 492));
        this.orbitingObjects1 = orbitingObjects1;
        this.orbitingObjects2 = orbitingObjects2;
        this.orbitingObjects3 = orbitingObjects3;
        this.setBackground(new Color(61, 45, 182, 0));
    }

    /**
     * Kirajolas
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(100, 200, 255));
        ((Graphics2D)g).setStroke(new BasicStroke(3));
        ArrayList<OrbitingObject> orbitingObjects = new ArrayList<>();
        orbitingObjects.addAll(orbitingObjects2);
        orbitingObjects.addAll(orbitingObjects3);
        orbitingObjects.addAll(orbitingObjects1);
        for(OrbitingObject ob : orbitingObjects){
            for(OrbitingObject ne : ob.GetNeighbors()){
                g.drawLine((int)ob.GetPosition().GetX()+500, -(int)ob.GetPosition().GetY()+246, (int)ne.GetPosition().GetX()+500, -(int)ne.GetPosition().GetY()+246);
            }
        }
    }

    /**
     * Pozicio x koordinataja
     */
    @Override
    public int GetPosX() {
        return 0;
    }

    /**
     * Pozicio y koordinataja
     */
    @Override
    public int GetPosY() {
        return 0;
    }

    /**
     * Megjelenites z "melysegi" koordinataja
     */
    public int GetZOrder(){
        return 1;
    }

    /**
     * Szomszedok listajaval ter vissza
     */
    @Override
    public ArrayList<OrbitingObject> GetNeighbours() {
        return new ArrayList<>();
    }
}
