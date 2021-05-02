package me.piedpiper.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private static ArrayList<JPanel>  graphicObjects = new ArrayList<JPanel>();
    public MainPanel(){
        this.setLayout(null);
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(JPanel panel: graphicObjects){
            this.add(panel);
            panel.setLocation(((IPosGettable)panel).GetPosX(),  ((IPosGettable)panel).GetPosY());
        }
    }

    public void AddGraphicObject(JPanel object){
        graphicObjects.add(object);
        repaint();
    }
    public void RemoveGraphicObject(JPanel object){
        graphicObjects.remove(object);
        repaint();
    }


}
