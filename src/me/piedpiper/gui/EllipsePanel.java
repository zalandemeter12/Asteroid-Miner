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
    private final Ellipse2D ellipse;

    /**
     * Konstruktor
     */
    public EllipsePanel(Ellipse2D e){
        ellipse=e;
        this.setSize(1000, 500);
        this.setBackground(new Color(61, 45, 182,0));
    }

    /**
     * Kirajolas
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.setPaint(Color.white);
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{9}, 0);
        g2d.setStroke(dashed);

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
