package me.piedpiper.gui;

import me.piedpiper.businesslogic.BaseAsteroid;
import me.piedpiper.businesslogic.OrbitingObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BaseAsteroidPanel extends JPanel implements IPosGettable{
    /**
     * Referencia az aszteroidara, amit abrazol
     */
    private BaseAsteroid baseAsteroid;
    JLabel label = new JLabel("B1");

    /**
     * Konstruktor
     */
    public BaseAsteroidPanel(BaseAsteroid b){
        baseAsteroid=b;
        this.setSize(34, 34);
        this.setBackground(new Color(0,0,0,0));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Kirajolas
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.black);
        g2d.fillOval(0, 0, 34, 34);
        g2d.drawString("B",0,0);
        this.add(label);
        g2d.setPaint(Color.GRAY);
        Font font = new Font("Arial", Font.PLAIN, 18);
        g2d.setFont(font);
        g2d.drawString("B", 10, 24);
    }

    /**
     * Pozicio x koordinataja
     */
    @Override
    public int GetPosX() {
        return (int)baseAsteroid.GetPosition().GetX()-17+500;
    }

    /**
     * Pozicio y koordinataja
     */
    @Override
    public int GetPosY() {
        return -1*((int)baseAsteroid.GetPosition().GetY()+17)+246;
    }

    /**
     * Megjelenites z "melysegi" koordinataja
     */
    public int GetZOrder(){
        return 0;
    }

    /**
     * Szomszedok listajaval ter vissza
     */
    @Override
    public ArrayList<OrbitingObject> GetNeighbours() {
        return baseAsteroid.GetNeighbors();
    }

    /**
     * A hozza tartozo aszteroidanak a referenciajat adja vissza
     */
    public BaseAsteroid GetBaseAsteroid() {
        return baseAsteroid;
    }
}
