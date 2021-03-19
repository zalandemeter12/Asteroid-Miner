package me.piedpiper.businesslogic;

import java.util.ArrayList;
import java.util.Random;

public class Sun implements ISteppable {
    private final Point2D position;
    private ArrayList<SolarStorm> solarStorms;
    private int roundsWithoutSS;
    private AsteroidField field;

    public Sun(Point2D position, AsteroidField field) {
        Logger.logMessage("Sun#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.roundsWithoutSS = 0;
        this.position = position;
        this.field = field;
        this.solarStorms = new ArrayList<SolarStorm>();

        Logger.tabcount--;
    }

    @Override
    public void Step() {
        Logger.logMessage("Sun#" + Integer.toHexString(this.hashCode()) + ".Step()");
        
        Random rand = new Random(); 
        if (rand.nextInt(25) % 25 < roundsWithoutSS) {
            solarStorms.add(new SolarStorm(this, 35, rand.nextInt(5)));
        } else {
            roundsWithoutSS++;
        }
        for (SolarStorm s : solarStorms) 
            s.Step();
        
        Logger.tabcount--;
    }

    public ArrayList<SolarStorm> GetSolarStorms() {
        Logger.logMessage("Sun#" + Integer.toHexString(this.hashCode()) + ".GetSolarStorms()");
        Logger.tabcount--;
        return solarStorms;
    }

    public AsteroidField GetField() {
        Logger.logMessage("Sun#" + Integer.toHexString(this.hashCode()) + ".GetField()");
        Logger.tabcount--;
        return field;
    }

    public void SetField(AsteroidField field) {
        Logger.logMessage("Sun#" + Integer.toHexString(this.hashCode()) + ".SetField()");
        
        this.field = field;
        
        Logger.tabcount--;
    }
}
