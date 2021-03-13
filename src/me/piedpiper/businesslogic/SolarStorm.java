package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class SolarStorm implements ISteppable{

    private final Sun sun;
    private final double angle;
    private int warnTimer;

    public SolarStorm(Sun sun, double angle, int warnTimer) {
        this.sun = sun;
        this.angle = angle;
        this.warnTimer = warnTimer;
        System.out.println("SolarStorm.Constructor()");
    }

    @Override
    public void Step() {
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
        System.out.println("SolarStorm.Step()");
    }
}
