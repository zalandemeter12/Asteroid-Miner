package me.piedpiper.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private static ArrayList<JPanel>  graphicObjects = new ArrayList<>();
    private View view;
    private JPanel backGround = new JPanel();


    public MainPanel(View view){
        this.setLayout(null);
        this.view = view;
        backGround.setSize(new Dimension(1000, 492));
        backGround.setBackground(new Color(61, 45, 182,255));

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

        for(int i = graphicObjects.size()-1; i >= 0; --i){
            this.add(graphicObjects.get(i));
            //this.setComponentZOrder(graphicObjects.get(i), ((IPosGettable)graphicObjects.get(i)).GetZOrder());
            graphicObjects.get(i).setLocation(((IPosGettable)graphicObjects.get(i)).GetPosX(),  ((IPosGettable)graphicObjects.get(i)).GetPosY());

        }
        this.add(backGround);
        //this.setComponentZOrder(backGround, graphicObjects.size());
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
        this.remove(object);
        graphicObjects.remove(object);
        repaint();
    }


    public ArrayList<JPanel> GetGraphicObjects() {
        return graphicObjects;
    }
}
