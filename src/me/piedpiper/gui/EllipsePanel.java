package me.piedpiper.gui;

import me.piedpiper.businesslogic.Ellipse2D;
import me.piedpiper.businesslogic.OrbitingObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EllipsePanel extends JPanel implements IPosGettable{
    /**
     * Referencia az ellipszisre, amit abrazol
     */
    private Ellipse2D ellipse;

    /**
     * Konstruktor
     */
    public EllipsePanel(Ellipse2D e){
        ellipse=e;
        this.setSize(1000, 500);
        this.setBackground(new Color(61, 45, 182,0));
    }
/*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //this.setBackground(new Color(61, 45, 182,255));
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.white);
        System.out.println("voltam");
        g2d.drawOval(0,0, (int)ellipse.GetA(), (int)ellipse.GetB());
    }
*/

    /**
     * Kirajolas
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //this.setBackground(new Color(61, 45, 182,255));
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.white);

        g2d.drawOval(0,0, (int)ellipse.GetA(), (int)ellipse.GetB());
    }


    /**
     * Pozicio x koordinataja
     */
    @Override
    public int GetPosX() {
        return (1000-(int)ellipse.GetA())/2;
    }

    /**
     * Pozicio y koordinataja
     */
    @Override
    public int GetPosY() {
        return (int)((492-ellipse.GetB())*0.5);
    }

    /**
     * Szomszedok listajaval ter vissza
     */
    @Override
    public ArrayList<OrbitingObject> GetNeighbours() {
        return null;
    }

    /**
     * Megjelenites z "melysegi" koordinataja
     */
    public int GetZOrder(){
        return 11;
    }


}
