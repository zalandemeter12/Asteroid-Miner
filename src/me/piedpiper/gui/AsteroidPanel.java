package me.piedpiper.gui;

import me.piedpiper.businesslogic.Asteroid;
import me.piedpiper.businesslogic.OrbitingObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AsteroidPanel extends JPanel implements IPosGettable{
    /**
     * Referencia az aszteroidara, amit abrazol
     */
    private final Asteroid asteroid;

    /**
     * Konstruktor
     */
    public AsteroidPanel(Asteroid a){
        asteroid=a;
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
        g2d.setPaint(Color.gray);
        g2d.fillOval(0, 0, 34, 34);
        if(asteroid.IsCloseToSun()) g2d.setPaint(new Color(255,100,0));
        else g2d.setPaint(Color.darkGray);
        Font font = new Font("Arial", Font.PLAIN, 17);
        g2d.setFont(font);
        int shift = 3;
        if(asteroid.GetIndex() < 10)
            shift = 6;

        g2d.drawString("A" + asteroid.GetIndex(), shift, 24);

    }

    /**
     * Pozicio x koordinataja
     */
    public int GetPosX(){
        return (int)(asteroid.GetPosition().GetX())+500-17;
    }

    /**
     * Pozicio y koordinataja
     */
    public int GetPosY(){
        return -1*((int)asteroid.GetPosition().GetY()+17)+246;
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
        return asteroid.GetNeighbors();
    }

    /**
     * A hozza tartozo aszteroidanak a referenciajat adja vissza
     */
    public Asteroid GetAsteroid() {
        return asteroid;
    }

}
