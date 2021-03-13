package me.piedpiper.businesslogic;

import java.util.ArrayList;
import java.util.Random;

public class Robot extends Worker implements ISteppable{
    private final AsteroidField field;

    public Robot(OrbitingObject location, AsteroidField field){
        super(location);
        this.field = field;
        System.out.println("Robot.Ctor()");
    }

    @Override
    public void Die(){
        System.out.println("Robot.Die()");
        field.RemoveRobot(this);
    }
    @Override
    public void Explode(){
        ArrayList<OrbitingObject> neighbours = location.GetNeighbors();
        if (neighbours.size() > 0) {
            Random rand = new Random();
            int idx = rand.nextInt(neighbours.size()-1);
            MoveTo(neighbours.get(idx));
        }
        System.out.println("Robot.Explode()");
    }

    @Override
    public void Step(){
        if (location.GetThickness() > 0) {
            location.DrilledOn();
        } else if (location.GetNeighbors().size() > 0) {
            Random rand = new Random();
            int idx = rand.nextInt(location.GetNeighbors().size()-1);
            MoveTo(location.GetNeighbors().get(idx));
            location.GetWorkers().remove(this);
        }
        System.out.println("Robot.Explode()");
    }
}
