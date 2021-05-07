package me.piedpiper.businesslogic;

import me.piedpiper.gui.AsteroidPanel;
import me.piedpiper.gui.SolarStormPanel;
import me.piedpiper.gui.View;

import java.util.ArrayList;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.tan;

public class SolarStorm implements ISteppable{

    //A nap, ami a napkitörést okozta
    private final Sun sun;
    //A szög, amilyen irányban a napkitörésnek hatása lesz
    private final double angle;
    //Az emlékeztető körök száma
    private int warnTimer;
    private static int currentIndex = 0;
    private final int id;
    private ArrayList<OrbitingObject> targets;
    private View view;
    private SolarStormPanel panel;

    //A napkitörés konstruktora
    public SolarStorm(Sun sun, double angle, int warnTimer) {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.sun = sun;
        this.angle = angle;
        this.warnTimer = warnTimer;
        this.targets = new ArrayList<>();
        this.id = ++currentIndex;

        Logger.tabcount--;
    }

    public SolarStorm(Sun sun, double angle, int warnTimer,View view) {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.sun = sun;
        this.angle = angle;
        this.warnTimer = warnTimer;
        this.targets = new ArrayList<>();
        this.id = ++currentIndex;
        this.view = view;
        panel=new SolarStormPanel(this);
        view.AddGraphicObject(panel, 1);
        Logger.tabcount--;
    }

    //A napkitörést vezérlő kontroller által végrehajtott lépés
    @Override
    public void Step() {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Step()");
        //Megöli a sérthető telepeseket és tönkreteszi a robotokat, ha lejárt a figyelmeztető idő
        if (warnTimer > 0) {
            warnTimer--;
        } else if (warnTimer == 0) {
            for (Ellipse2D e : sun.GetField().GetEllipses()) {
                for (OrbitingObject o : e.GetObjects()) {
                    double bottomLine = Math.tan((angle + 0.000001) / 180 * PI); //y=bottomline*x
                    double topLine = Math.tan((angle + 30.000001) / 180 * PI); //y=topline*x
                    if ((angle >= 270 || angle <= 75 )
                            && (bottomLine * o.GetPosition().GetX()) < o.GetPosition().GetY()
                            && (topLine * o.GetPosition().GetX()) > o.GetPosition().GetY()) {
                        o.UnderSolarStorm();
                    } else if ((angle > 75 && angle < 90)
                            && (bottomLine * o.GetPosition().GetX()) < o.GetPosition().GetY()
                            && (topLine * o.GetPosition().GetX()) < o.GetPosition().GetY()) {
                        o.UnderSolarStorm();
                    } else if ((angle >= 90 && angle <= 255)
                            && (bottomLine * o.GetPosition().GetX()) > o.GetPosition().GetY()
                            && (topLine * o.GetPosition().GetX()) < o.GetPosition().GetY()) {
                        o.UnderSolarStorm();
                    } else if ((angle > 255 && angle < 270)
                            && (bottomLine * o.GetPosition().GetX()) > o.GetPosition().GetY()
                            && (topLine * o.GetPosition().GetX()) > o.GetPosition().GetY()) {
                        o.UnderSolarStorm();
                    }


                }
            /*for (OrbitingObject o : targets)
                o.UnderSolarStorm();*/

            }
            view.RemoveGraphicObject(panel);

            Logger.tabcount--;
        }
    }

    public String GetName(){
        return "SolarStorm"+id;
    }

    public int GetWarnTimer(){
        return warnTimer;
    }

    public double GetAngle(){
        return angle;
    }

    public void AddTarget(OrbitingObject o) {
        targets.add(o);
    }

    public static void ResetIndex(){
        currentIndex=0;
    }
}
