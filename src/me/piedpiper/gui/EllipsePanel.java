package me.piedpiper.gui;

import me.piedpiper.businesslogic.Ellipse2D;

import javax.swing.*;
import java.awt.*;

public class EllipsePanel extends JPanel {
    private Ellipse2D ellipse;

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.white);

        g2d.drawOval(0,0, (int)ellipse.GetA(), (int)ellipse.GetB());
    }

}
