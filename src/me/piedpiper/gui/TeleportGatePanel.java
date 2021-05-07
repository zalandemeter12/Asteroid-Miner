package me.piedpiper.gui;

import me.piedpiper.businesslogic.OrbitingObject;
import me.piedpiper.businesslogic.TeleportGate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        g2d.fillRect(0, 0, 30, 30);
        g2d.setPaint(Color.darkGray);
        Font font = new Font("Arial", Font.PLAIN, 18);
        g2d.setFont(font);
        g2d.drawString("T" + teleportGate.GetIndex(), 5, 22);

    }

    @Override
    public int GetPosX() {
        return (int)teleportGate.GetPosition().GetX()+500-15;
    }

    @Override
    public int GetPosY() {
        return (int)teleportGate.GetPosition().GetY()+246-15;
    }

    @Override
    public ArrayList<OrbitingObject> GetNeighbours() {
        return teleportGate.GetNeighbors();
    }

    public TeleportGate GetGate() {
        return teleportGate;
    }
    public int GetZOrder(){
        return 0;
    }
}
