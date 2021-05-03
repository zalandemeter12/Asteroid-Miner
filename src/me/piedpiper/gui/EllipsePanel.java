package me.piedpiper.gui;

import me.piedpiper.businesslogic.Ellipse2D;

import javax.swing.*;
import java.awt.*;

public class EllipsePanel extends JPanel implements IPosGettable{
    private Ellipse2D ellipse;

    public EllipsePanel(Ellipse2D e){
        ellipse=e;
        this.setSize(1000, 500);
        this.setBackground(new Color(0,0,0,0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.white);

        g2d.drawOval(50,50, 900, 400);
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
