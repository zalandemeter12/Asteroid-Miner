package me.piedpiper.gui;

import com.sun.org.apache.xpath.internal.operations.Or;
import me.piedpiper.businesslogic.OrbitingObject;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class NeighboursPanel extends JPanel implements IPosGettable{
    private ArrayList<OrbitingObject> orbitingObjects;

    public NeighboursPanel(ArrayList<OrbitingObject> orbitingObjects){
        setSize(new Dimension(1000, 492));
        this.orbitingObjects = orbitingObjects;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(new Color(0,0,0,0));
        g.setColor(Color.red);
        for(OrbitingObject ob : orbitingObjects){
            for(OrbitingObject ne : ob.GetNeighbors()){
                g.drawLine((int)ob.GetPosition().GetX(), (int)ob.GetPosition().GetY(), (int)ne.GetPosition().GetX(), (int)ne.GetPosition().GetY());
            }
        }
    }

    @Override
    public int GetPosX() {
        return 0;
    }

    @Override
    public int GetPosY() {
        return 0;
    }

    @Override
    public ArrayList<OrbitingObject> GetNeighbours() {
        return new ArrayList<OrbitingObject>();
    }
}
