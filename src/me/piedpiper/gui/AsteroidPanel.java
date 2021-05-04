package me.piedpiper.gui;

import me.piedpiper.businesslogic.Asteroid;
import me.piedpiper.businesslogic.OrbitingObject;

import javax.swing.*;
import java.awt.*;

public class AsteroidPanel extends JPanel implements IPosGettable{
    private Asteroid asteroid;

    public AsteroidPanel(Asteroid a){
        asteroid=a;
        this.setSize(30, 30);
        this.setBackground(new Color(0,0,0,0));

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.gray);
        g2d.fillOval(0, 0, 30, 30);
        g2d.setPaint(Color.darkGray);
        Font font = new Font("Arial", Font.PLAIN, 18);
        g2d.setFont(font);
        g2d.drawString("A" + asteroid.GetIndex(), 5, 22);
    }

    public int GetPosX(){
        return (int)asteroid.GetPosition().GetX()+500-15;
    }

    public int GetPosY(){
        return (int)asteroid.GetPosition().GetY()+246-15;
    }

    public Asteroid GetAsteroid() {
        return asteroid;
    }
}
