package me.piedpiper.gui;

import me.piedpiper.businesslogic.TeleportGate;

import javax.swing.*;
import java.awt.*;

public class TeleportGatePanel extends JPanel implements IPosGettable{
    private TeleportGate teleportGate;

    public TeleportGatePanel(TeleportGate tp){
        teleportGate=tp;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //TODO width, height kitalálni
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.red);
        g2d.fillRect((int)teleportGate.GetPosition().GetX(), (int)teleportGate.GetPosition().GetY(), 10, 10);
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
