package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class SolarStorm implements ISteppable{

    private final Sun sun;
    private final double angle;
    private int warnTimer;

    public SolarStorm(Sun sun, double angle, int warnTime) {
        this.sun = sun;
        this.angle = angle;
        this.warnTime = warnTime;
        System.out.println("SolarStorm.Constructor()");
    }

    @Override
    public void Step() {
        if (warnTimer>0) {
            warnTimer--;
        } else {
            ArrayList<Ellipse2D> ellipses = sun.GetField().GetEllipses();
            for (Ellipse2D e : ellipses) {
                ArrayList<OrbitingObject> objects = e.GetObjects();
                for (OrbitingObject o : objects) {
                    ArrayList<Worker> workers = o.GetExposedWorkers();
                    for (Worker w : workers) {
                        w.Die();
                    }
                }
            }
            sun.GetSolarStorms().remove(this);
        }
        System.out.println("SolarStorm.Step()");
    }
}
