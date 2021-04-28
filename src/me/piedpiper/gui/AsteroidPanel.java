package me.piedpiper.gui;

import me.piedpiper.businesslogic.Asteroid;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class AsteroidPanel extends JPanel {
    private Asteroid asteroid;

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.gray);
        g2d.fillOval((int)asteroid.GetPosition().GetX(), (int)asteroid.GetPosition().GetY(), 10, 10);

    }
}
