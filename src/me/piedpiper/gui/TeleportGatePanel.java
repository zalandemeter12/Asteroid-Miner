package me.piedpiper.gui;

import me.piedpiper.businesslogic.TeleportGate;

import javax.swing.*;
import java.awt.*;

public class TeleportGatePanel extends JPanel {
    private TeleportGate teleportGate;

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        //TODO width, height kitalálni
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.red);
        g2d.fillRect((int)teleportGate.GetPosition().GetX(), (int)teleportGate.GetPosition().GetY(), 10, 10);
    }
}
