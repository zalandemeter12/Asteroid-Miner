package me.piedpiper.gui;

import me.piedpiper.businesslogic.OrbitingObject;
import me.piedpiper.businesslogic.Sun;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SunPanel extends JPanel implements IPosGettable{
    private Sun sun;

    public SunPanel(Sun s){
        sun=s;
        this.setSize(60, 60);
        this.setBackground(new Color(0,0,0,0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);


        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.orange);
        int r=60;
        g2d.fillOval(0, 0, r, r);
        g2d.setPaint(Color.darkGray);
        Font font = new Font("Arial", Font.PLAIN, 20);
        g2d.setFont(font);
        g2d.drawString("S", 24, 36);
    }

    @Override
    public int GetPosX() {
        return 470;
    }

    @Override
    public int GetPosY() {
        return 492/2-30;
    }

    @Override
    public ArrayList<OrbitingObject> GetNeighbours() {
        return new ArrayList<OrbitingObject>();
    }
}
