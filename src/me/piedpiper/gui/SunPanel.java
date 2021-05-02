package me.piedpiper.gui;

import me.piedpiper.businesslogic.Sun;

import javax.swing.*;
import java.awt.*;

public class SunPanel extends JPanel implements IPosGettable{
    private Sun sun;

    public SunPanel(Sun s){
        sun=s;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //TODO width, height kitalálni
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.orange);
        int r=20;
        g2d.fillOval(1000/2-r, 600/2+r, r, r);
    }

    @Override
    public int GetPosX() {
        return 0;
    }

    @Override
    public int GetPosY() {
        return 0;
    }
}
