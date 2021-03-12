package me.piedpiper.businesslogic;

public class SolarStorm implements ISteppable{

    private final Sun sun;
    private final double angle;
    private int warnTime;

    public SolarStorm(Sun sun, double angle, int warnTime) {
        this.sun = sun;
        this.angle = angle;
        this.warnTime = warnTime;
        System.out.println("SolarStorm.Constructor()");
    }

    @Override
    public void Step() {
        System.out.println("SolarStorm.Step()");
    }
}
