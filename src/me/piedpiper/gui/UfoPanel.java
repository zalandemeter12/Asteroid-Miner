package me.piedpiper.gui;

import me.piedpiper.businesslogic.Ufo;

import javax.swing.*;
import java.awt.*;

public class UfoPanel extends JPanel implements IPosGettable{
    private Ufo ufo;

    public UfoPanel(Ufo u){
        ufo=u;
        this.setSize(16, 16);
        this.setBackground(new Color(0,0,0,0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.green);
        int width=16;
        int height=16;

        g2d.fillOval(0, 0, width, height);
    }

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
        int width=16;
        int x;
        int gap=2;
        if(numberOfWorkers % 2 ==0){
            x=(int)ufo.GetLocation().GetPosition().GetX()+(numberOfWorkers/2-order-1)*(gap+width/2)+(gap/2);
        } else{
            int middle=(numberOfWorkers+1)/2;
            if(middle==order){
                x=(int)ufo.GetLocation().GetPosition().GetX()-width/2;
            }else
                x=(int)ufo.GetLocation().GetPosition().GetX()+(middle-order)*(gap+width) + width;
            if((middle-order)<0) x-=2*width;
        }
        return x;
    }

    @Override
    public int GetPosY() {
        return (int)ufo.GetLocation().GetPosition().GetY() - 35;
    }
}
