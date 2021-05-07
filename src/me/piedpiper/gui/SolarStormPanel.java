package me.piedpiper.gui;

import me.piedpiper.businesslogic.OrbitingObject;
import me.piedpiper.businesslogic.SolarStorm;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.StrictMath.PI;

public class SolarStormPanel extends JPanel implements IPosGettable{
    /**
     * Referencia az aszteroidara, amit abrazol
     */
    private SolarStorm solarStorm;

    /**
     * Konstruktor
     */
    public SolarStormPanel(SolarStorm s){
        solarStorm=s;
        this.setSize(1000, 500);
        this.setBackground(new Color(0,0,0,0));
    }

    /**
     * Kirajolas
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(new Color(175, 36, 36, 100));

        g2d.fillArc(0, 0, 900, 400, (int)solarStorm.GetAngle(), 30);

        g2d.setPaint(Color.black);
        Font font = new Font("Arial", Font.PLAIN, 20);
        g2d.setFont(font);
        g2d.drawString(String.valueOf(solarStorm.GetWarnTimer()), (int)(450+100*Math.cos((solarStorm.GetAngle()+15)/180*PI)),
                (int)(204-50*Math.sin((solarStorm.GetAngle()+15)/180*PI)));
    }

    /**
     * Pozicio x koordinataja
     */
    @Override
    public int GetPosX() {
        return 50;
    }

    /**
     * Pozicio y koordinataja
     */
    @Override
    public int GetPosY() {
        return 46;
    }

    /**
     * Megjelenites z "melysegi" koordinataja
     */
    public int GetZOrder(){
        return 20;
    }

    /**
     * A hozza tartozo aszteroidanak a referenciajat adja vissza
     */
    @Override
    public ArrayList<OrbitingObject> GetNeighbours() {
        return null;
    }
}
