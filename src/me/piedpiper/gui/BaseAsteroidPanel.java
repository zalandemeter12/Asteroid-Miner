package me.piedpiper.gui;

import me.piedpiper.businesslogic.BaseAsteroid;

import javax.swing.*;
import java.awt.*;

public class BaseAsteroidPanel extends JPanel implements IPosGettable{
    private BaseAsteroid baseAsteroid;

    public BaseAsteroidPanel(BaseAsteroid b){
        baseAsteroid=b;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //TODO width, height kitalálni
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.gray);
        g2d.fillOval((int)baseAsteroid.GetPosition().GetX(), (int)baseAsteroid.GetPosition().GetY(), 10, 10);
    }

    @Override
    public int GetPosX() {
        return (int)baseAsteroid.GetPosition().GetX();
    }

    @Override
    public int GetPosY() {
        return (int)baseAsteroid.GetPosition().GetY();
    }
}
