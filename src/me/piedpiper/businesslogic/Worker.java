package me.piedpiper.businesslogic;

import java.util.ArrayList;

public abstract class Worker {
    protected OrbitingObject location;

    public Worker(OrbitingObject location) {
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.location = location;

        Logger.tabcount--;
    }

    public void MoveTo(OrbitingObject o){
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".MoveTo()");
        
        ArrayList<OrbitingObject> neighbors = location.GetNeighbors();
        if (neighbors.contains(o)) {
            location.RemoveWorker(this);
            o.AddWorker(this);
            location=o;
        }
        
        Logger.tabcount--;
    }

    public abstract void Die();

    public void DrillHole(){
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".DrillHole()");
        
        location.DrilledOn();

        Logger.tabcount--;
    }
    
    public abstract void Explode();

    public void SetLocation(OrbitingObject l){
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".SetLocation()");
        
        this.location=l;
        
        Logger.tabcount--;
    }

    public OrbitingObject GetLocation(){
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".GetLocation()");
        Logger.tabcount--;
        return location;
    }
}
