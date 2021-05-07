package me.piedpiper.gui;

import me.piedpiper.businesslogic.OrbitingObject;
import me.piedpiper.businesslogic.SolarStorm;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.StrictMath.PI;

public class SolarStormPanel extends JPanel implements IPosGettable{
    private SolarStorm solarStorm;

    public SolarStormPanel(SolarStorm s){
        solarStorm=s;
        this.setSize(1000, 500);
        this.setBackground(new Color(0,0,0,0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.red);

        g2d.fillArc(0, 0, 900, 400, (int)solarStorm.GetAngle(), 30);
    }

    @Override
    public int GetPosX() {
        return 50;
    }

    @Override
    public int GetPosY() {
        return 46;
    }

    public int GetZOrder(){
        return 20;
    }

    @Override
    public ArrayList<OrbitingObject> GetNeighbours() {
        return null;
    }
}
