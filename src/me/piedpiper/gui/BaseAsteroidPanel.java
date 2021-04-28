package me.piedpiper.gui;

import me.piedpiper.businesslogic.BaseAsteroid;

import javax.swing.*;
import java.awt.*;

public class BaseAsteroidPanel extends JPanel {
    private BaseAsteroid baseAsteroid;

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        //TODO width, height kitalálni
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.gray);
        g2d.fillOval((int)baseAsteroid.GetPosition().GetX(), (int)baseAsteroid.GetPosition().GetY(), 10, 10);
    }

}
