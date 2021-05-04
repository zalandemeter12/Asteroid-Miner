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
        view.AddGraphicObject(panel);
        Logger.tabcount--;
    }

    //A napkitörést vezérlő kontroller által végrehajtott lépés
    @Override
    public void Step() {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Step()");
        
        //Megöli a sérthető telepeseket és tönkreteszi a robotokat, ha lejárt a figyelmeztető idő
        if (warnTimer>0) {
            warnTimer--;
        } else if(warnTimer==0){
            for (Ellipse2D e : sun.GetField().GetEllipses()) {
                //TODO nem tudni, hogy mukodik-e
                for(OrbitingObject o : e.GetObjects()){
                    double bottomLine=tan(angle); //y=bottomline*x
                    double topLine=tan(angle+PI/12); //y=topline*x
                    if(bottomLine*o.GetPosition().GetX()<o.GetPosition().GetY()
                       && topLine*o.GetPosition().GetX()>o.GetPosition().GetY()){
                        o.UnderSolarStorm();
                    }
                }
            }
            for (OrbitingObject o : targets)
                o.UnderSolarStorm();
            view.RemoveGraphicObject(panel);
        }
        
        Logger.tabcount--;
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
