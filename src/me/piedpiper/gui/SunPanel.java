package me.piedpiper.gui;

import me.piedpiper.businesslogic.Sun;

import javax.swing.*;
import java.awt.*;

public class SunPanel extends JPanel {
    private Sun sun;

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        //TODO width, height kitalálni
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.orange);
        g2d.fillOval(0, 0, 20, 20);
    }

}
