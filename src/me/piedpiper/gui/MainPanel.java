package me.piedpiper.gui;

import me.piedpiper.businesslogic.OrbitingObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private static ArrayList<JPanel>  graphicObjects = new ArrayList<>();
    private View view;

    public MainPanel(View view){
        this.setLayout(null);
        this.view = view;

    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0,0,1000,492);
        super.paint(g);
        /*
        for(JPanel panel: graphicObjects){
            this.add(panel);
            panel.setLocation(((IPosGettable)panel).GetPosX(),  ((IPosGettable)panel).GetPosY());
        }
        */
        g.setColor(Color.RED);
        for(int i = graphicObjects.size()-1; i >= 0; --i){

            IPosGettable graphObj = (IPosGettable)graphicObjects.get(i);
            for(OrbitingObject o : graphObj.GetNeighbours()){
                g.drawLine((int)o.GetPosition().GetX()+500, (int)o.GetPosition().GetY()+246, graphObj.GetPosX()+15, graphObj.GetPosY()+15);
                System.out.println("x: "+(int)o.GetPosition().GetY()+", y: "+ (int)o.GetPosition().GetY());
            }
            this.add(graphicObjects.get(i));
            this.setComponentZOrder(graphicObjects.get(i), 0);
            graphicObjects.get(i).setLocation(((IPosGettable)graphicObjects.get(i)).GetPosX(),  ((IPosGettable)graphicObjects.get(i)).GetPosY());
        }
    }

    public void AddGraphicObject(JPanel object){
        graphicObjects.add(object);
        object.addMouseListener(new OrbitingObjectMouseListener(view));
        repaint();
    }

    public void AddGraphicObject(JPanel object, int index){
        graphicObjects.add(index, object);
        object.addMouseListener(new OrbitingObjectMouseListener(view));
        repaint();
    }

    public void RemoveGraphicObject(JPanel object){
        graphicObjects.remove(object);
        repaint();
    }
    public void RemoveAllG(){
        graphicObjects.clear();
        this.removeAll();
        this.revalidate();
        this.repaint();
        //repaint();
    }

    public ArrayList<JPanel> GetGraphicObjects() {
        return graphicObjects;
    }
}
