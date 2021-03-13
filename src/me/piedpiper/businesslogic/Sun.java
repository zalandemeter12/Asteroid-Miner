package me.piedpiper.businesslogic;

import java.util.ArrayList;
import java.util.Random;

public class Sun implements ISteppable {
    private final Point2D position;
    private ArrayList<SolarStorm> solarStorms;
    private int roundsWithoutSS;
    private AsteroidField field;

    public Sun(Point2D position, AsteroidField field) {
        this.roundsWithoutSS = 0;
        this.position = position;
        this.field = field;
        this.solarStorms = new ArrayList<SolarStorm>();
        System.out.println("Sun.Constructor()");
    }

    @Override
    public void Step() {
        Random rand = new Random(); 
        if (rand.nextInt(25) % 25 < roundsWithoutSS) {
            solarStorms.add(new SolarStorm(this, 35, rand.nextInt(5)));
        } else {
            roundsWithoutSS++;
        }
        System.out.println("Sun.Step()");
    }

    public ArrayList<SolarStorm> GetSolarStorms() {
        return solarStorms;
    }

    public AsteroidField GetField() {
        return field;
    }

    public void SetField(AsteroidField field) {
        this.field = field;
    }
}
