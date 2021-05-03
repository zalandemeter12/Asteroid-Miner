package me.piedpiper.gui;

import me.piedpiper.businesslogic.Settler;

import javax.swing.*;
import java.awt.*;

public class SettlerPanel extends JPanel implements IPosGettable{
    private Settler settler;

    public SettlerPanel(Settler s){
        settler=s;
        this.setSize(16, 16);
        this.setBackground(new Color(0,0,0,0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.orange);
        int width=16;
        int height=16;

        g2d.fillOval(0, 0, width, height);
    }

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
        int width=16;
        int x;
        int gap=2;
        if(numberOfWorkers % 2 ==0){
            x=(int)settler.GetLocation().GetPosition().GetX()+(numberOfWorkers/2-order-1)*(gap+width/2)+(gap/2);
        } else{
            int middle=(numberOfWorkers+1)/2;
            if(middle==order){
                x=(int)settler.GetLocation().GetPosition().GetX()-width/2;
            }else
                x=(int)settler.GetLocation().GetPosition().GetX()+(middle-order)*(gap+width) + width;
            if((middle-order)<0) x-=2*width;
        }
        return x+500;
    }

    @Override
    public int GetPosY() {
        return (int)settler.GetLocation().GetPosition().GetY() + 246- 35;
    }
}
