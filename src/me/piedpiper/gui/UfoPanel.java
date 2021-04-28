package me.piedpiper.gui;

import me.piedpiper.businesslogic.Ufo;

import javax.swing.*;
import java.awt.*;

public class UfoPanel extends JPanel {
    private Ufo ufo;

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        //TODO width, height kitalálni, mennyivel legyen az aszteroida felett
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.green);
        int order=0;
        int numberOfWorkers=ufo.GetLocation().GetWorkers().size();
        for(int i=0; i<numberOfWorkers; i++){
          if(ufo.GetLocation().GetWorkers().get(i).equals(ufo)){
              order=i+1;
              break;
          }
        }
        int width=5;
        int height=5;
        int x;
        int gap=2;
        if(numberOfWorkers % 2 ==0){
            x=(int)ufo.GetLocation().GetPosition().GetX()+(numberOfWorkers/2-order-1)*(gap+width)+(gap/2+width/2);
        } else{
            int middle=(numberOfWorkers+1)/2;
            if(middle==order){
                x=(int)ufo.GetLocation().GetPosition().GetX();
            }else
                x=(int)ufo.GetLocation().GetPosition().GetX()+(middle-order)*(gap+width) + width;
                if((middle-order)<0) x-=2*width;
        }

        g2d.fillOval(x, (int)ufo.GetLocation().GetPosition().GetY() + 16, width, height);
    }
}
