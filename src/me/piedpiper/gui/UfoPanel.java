package me.piedpiper.gui;

import me.piedpiper.businesslogic.OrbitingObject;
import me.piedpiper.businesslogic.Ufo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UfoPanel extends JPanel implements IPosGettable{
    /**
     * Referencia az aszteroidara, amit abrazol
     */
    private Ufo ufo;

    int width=16;
    int height=16;

    /**
     * Konstruktor
     */
    public UfoPanel(Ufo u){
        ufo=u;
        this.setSize(width, height);
        this.setBackground(new Color(0,0,0,0));
    }

    /**
     * Kirajolas
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.green);
        g2d.fillOval(0, 0, width, height);

        g2d.setPaint(Color.darkGray);
        Font font = new Font("Arial", Font.PLAIN, 10);
        g2d.setFont(font);
        g2d.drawString("U", 4, 12);
    }

    /**
     * Pozicio x koordinataja
     */
    @Override
    public int GetPosX() {
        int order=0;
        int numberOfWorkers=ufo.GetLocation().GetWorkers().size();
        for(int i=0; i<numberOfWorkers; i++){
            if(ufo.GetLocation().GetWorkers().get(i).equals(ufo)){
                order=i+1;
                break;
            }
        }

        int x;
        int gap=2;
        if(numberOfWorkers % 2 ==0){
            x=(int)ufo.GetLocation().GetPosition().GetX()+(numberOfWorkers/2-order-1)*(gap+width)+(gap/2+width);
        } else{
            int middle=(numberOfWorkers+1)/2;
            if(middle==order){
                x=(int)ufo.GetLocation().GetPosition().GetX()-width/2;
            }else
                x=(int)ufo.GetLocation().GetPosition().GetX()+(middle-order)*(gap+width) + width;
            if((middle-order)<0) x-=2*width;
            else if((middle-order)>0) x-=width;

        }
        return x+500;
    }

    /**
     * Pozicio y koordinataja
     */
    @Override
    public int GetPosY() {
        return -1*((int)ufo.GetLocation().GetPosition().GetY()+ 35)+246 ;
    }

    /**
     * Megjelenites z "melysegi" koordinataja
     */
    public int GetZOrder(){
        return 0;
    }

    /**
     * A hozza tartozo aszteroidanak a referenciajat adja vissza
     */
    @Override
    public ArrayList<OrbitingObject> GetNeighbours() {
        return null;
    }
}
