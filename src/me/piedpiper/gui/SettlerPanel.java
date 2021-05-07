package me.piedpiper.gui;

import me.piedpiper.businesslogic.OrbitingObject;
import me.piedpiper.businesslogic.Settler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SettlerPanel extends JPanel implements IPosGettable{
    /**
     * Referencia a telepesre, amit abrazol
     */
    private final Settler settler;

    /**
     * Panel szelessege
     */
    int width=16;

    /**
     * Panel magassaga
     */
    int height=16;

    /**
     * Konstruktor
     */
    public SettlerPanel(Settler s){
        settler=s;
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
        g2d.setPaint(Color.orange);
        g2d.fillOval(0, 0, width, height);

        g2d.setPaint(Color.darkGray);
        Font font = new Font("Arial", Font.PLAIN, 10);
        g2d.setFont(font);
        g2d.drawString("S" + settler.GetIndex(), 2, 12);

    }

    /**
     * Pozicio x koordinataja
     */
    @Override
    public int GetPosX() {
        int order=0;
        int numberOfWorkers=settler.GetLocation().GetWorkers().size();
        for(int i=0; i<numberOfWorkers; i++){
            if(settler.GetLocation().GetWorkers().get(i).equals(settler)){
                order=i+1;
                break;
            }
        }

        int x;
        int gap=2;
        if(numberOfWorkers % 2 ==0){
            x=(int)settler.GetLocation().GetPosition().GetX()+(numberOfWorkers/2-order-1)*(gap+width)+(gap/2+width);
        } else{
            int middle=(numberOfWorkers+1)/2;
            if(middle==order){
                x=(int)settler.GetLocation().GetPosition().GetX()-width/2;
            }else
                x=(int)settler.GetLocation().GetPosition().GetX()+(middle-order)*(gap+width) + width;
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
        return -1*((int)settler.GetLocation().GetPosition().GetY()+ 35)+246;
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
        return null;
    }
}
