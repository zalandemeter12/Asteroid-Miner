package me.piedpiper.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private static ArrayList<JPanel>  graphicObjects = new ArrayList<JPanel>();
    public MainPanel(){

    }

    @Override
    public void repaint(Rectangle r) {
        super.repaint(r);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
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
