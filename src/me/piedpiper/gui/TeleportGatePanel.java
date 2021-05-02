package me.piedpiper.gui;

import me.piedpiper.businesslogic.TeleportGate;

import javax.swing.*;
import java.awt.*;

public class TeleportGatePanel extends JPanel implements IPosGettable{
    private TeleportGate teleportGate;

    public TeleportGatePanel(TeleportGate tp){
        teleportGate=tp;
        this.setSize(30, 30);
        this.setBackground(new Color(0,0,0,0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //TODO width, height kitalálni
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.red);
        g2d.fillRect(0, 0, 20, 20);
    }

    @Override
    public int GetPosX() {
        return (int)teleportGate.GetPosition().GetX()-10;
    }

    @Override
    public int GetPosY() {
        return (int)teleportGate.GetPosition().GetY()-10;
    }
}
