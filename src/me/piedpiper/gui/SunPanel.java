package me.piedpiper.gui;

import me.piedpiper.businesslogic.Sun;

import javax.swing.*;
import java.awt.*;

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

        //TODO width, height kitalálni
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.orange);
        int r=60;
        g2d.fillOval(0, 0, r, r);
    }

    @Override
    public int GetPosX() {
        return 350;
    }

    @Override
    public int GetPosY() {
        return 200;
    }
}
