package me.piedpiper.gui;

import me.piedpiper.businesslogic.SolarStorm;

import javax.swing.*;
import java.awt.*;

public class SolarStormPanel extends JPanel {
    private SolarStorm solarStorm;

    public SolarStormPanel(SolarStorm s){
        solarStorm=s;
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.red);
        //nem vágom hogy a startAngle-t mibol kene tudni, ha valaki tudja megcsinalhatja
        g2d.fillArc(0, 0, 1000, 600, 0, (int)solarStorm.GetAngle());
    }

}
