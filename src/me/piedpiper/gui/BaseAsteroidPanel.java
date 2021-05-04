package me.piedpiper.gui;

import me.piedpiper.businesslogic.BaseAsteroid;
import me.piedpiper.businesslogic.OrbitingObject;

import javax.swing.*;
import java.awt.*;

public class BaseAsteroidPanel extends JPanel implements IPosGettable{
    private BaseAsteroid baseAsteroid;

    public BaseAsteroidPanel(BaseAsteroid b){
        baseAsteroid=b;
        this.setSize(30, 30);
        this.setBackground(new Color(0,0,0,0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.black);
        g2d.fillOval(0, 0, 30, 30);
        g2d.drawString("B",0,0);
        g2d.setPaint(Color.GRAY);
        Font font = new Font("Arial", Font.PLAIN, 18);
        g2d.setFont(font);
        g2d.drawString("B", 10, 22);
    }

    @Override
    public int GetPosX() {
        return (int)baseAsteroid.GetPosition().GetX()-15+500;
    }

    @Override
    public int GetPosY() {
        return (int)baseAsteroid.GetPosition().GetY()-15+246;
    }

    public BaseAsteroid GetBaseAsteroid() {
        return baseAsteroid;
    }
}
