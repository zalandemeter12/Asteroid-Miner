package me.piedpiper.businesslogic;

import java.util.ArrayList;
import java.util.Random;

public class Robot extends Worker implements ISteppable{
    private final AsteroidField field;

    public Robot(OrbitingObject location, AsteroidField field){
        super(location);
        Logger.logMessage("Robot#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.field = field;

        Logger.tabcount--;
    }

    @Override
    public void Die(){
        Logger.logMessage("Robot#" + Integer.toHexString(this.hashCode()) + ".Die()");
        
        field.RemoveRobot(this);
        
        Logger.tabcount--;
    }
    @Override
    public void Explode(){
        Logger.logMessage("Robot#" + Integer.toHexString(this.hashCode()) + ".Explode()");
        
        ArrayList<OrbitingObject> neighbours = location.GetNeighbors();
        if (neighbours.size() > 0) {
            Random rand = new Random();
            int idx = rand.nextInt(neighbours.size()-1);
            MoveTo(neighbours.get(idx));
        }
        System.out.println("Robot.Explode()");
        
        Logger.tabcount--;
    }

    @Override
    public void Step(){
        Logger.logMessage("Robot#" + Integer.toHexString(this.hashCode()) + ".Step()");
        
        if (location.GetThickness() > 0) {
            location.DrilledOn();
        } else if (location.GetNeighbors().size() > 0) {
            Random rand = new Random();
            int idx = rand.nextInt(location.GetNeighbors().size()-1);
            MoveTo(location.GetNeighbors().get(idx));
            location.GetWorkers().remove(this);
        }
        
        Logger.tabcount--;
    }
}
