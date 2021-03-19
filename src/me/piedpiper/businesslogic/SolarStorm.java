package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class SolarStorm implements ISteppable{

    private final Sun sun;
    private final double angle;
    private int warnTimer;

    public SolarStorm(Sun sun, double angle, int warnTimer) {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.sun = sun;
        this.angle = angle;
        this.warnTimer = warnTimer;

        Logger.tabcount--;
    }

    @Override
    public void Step() {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Step()");
        
        if (warnTimer>0) {
            warnTimer--;
        } else {
            for (Ellipse2D e : sun.GetField().GetEllipses()) {
                for (OrbitingObject o : e.GetObjects()) {
                    for (Worker w : o.GetExposedWorkers()) {
                        w.Die();
                    }
                }
            }
            sun.GetSolarStorms().remove(this);
        }
        
        Logger.tabcount--;
    }
}
