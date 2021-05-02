package me.piedpiper.gui;

import me.piedpiper.businesslogic.Ellipse2D;

import javax.swing.*;
import java.awt.*;

public class EllipsePanel extends JPanel implements IPosGettable{
    private Ellipse2D ellipse;

    public EllipsePanel(Ellipse2D e){
        ellipse=e;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.white);

        g2d.drawOval((1000-(int)ellipse.GetA())/2,(600-(int)ellipse.GetB())/2, (int)ellipse.GetA(), (int)ellipse.GetB());
    }

    @Override
    public int GetPosX() {
        return (1000-(int)ellipse.GetA())/2;
    }

    @Override
    public int GetPosY() {
        return (600-(int)ellipse.GetB())/2;
    }
}
