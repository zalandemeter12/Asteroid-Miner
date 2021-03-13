package me.piedpiper.businesslogic;

import java.util.ArrayList;

public abstract class Worker {
    protected OrbitingObject location;

    public Worker(OrbitingObject location) {
        this.location = location;
        System.out.println("Worker.Ctor()");
    }

    public void MoveTo(OrbitingObject o){
        ArrayList<OrbitingObject> neighbors = location.GetNeighbors();
        if (neighbors.contains(o)) {
            location.RemoveWorker(this);
            o.AddWorker(this);
        }
        System.out.println("Worker.MoveTo()");
        o.AddWorker(this);
        location=o;
    }

    public void Die(){
        System.out.println("Worker.Die()");
    }

    public void DrillHole(){
        System.out.println("Worker.DrillHole()");
    }

    public void Explode(){
        System.out.println("Worker.Explode()");
    }

    public OrbitingObject GetLocation(){
        return location;
    }


}
