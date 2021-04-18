package me.piedpiper.businesslogic;

import java.util.ArrayList;

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

    //A napkitörést vezérlő kontroller által végrehajtott lépés
    @Override
    public void Step() {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Step()");
        
        //Megöli a sérthető telepeseket és tönkreteszi a robotokat, ha lejárt a figyelmeztető idő
        if (warnTimer>0) {
            warnTimer--;
        } else {
            for (Ellipse2D e : sun.GetField().GetEllipses()) {
                //TODO implementálni pos+angle alapján
            }
            for (OrbitingObject o : targets)
                o.UnderSolarStorm();
            sun.GetSolarStorms().remove(this);
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
