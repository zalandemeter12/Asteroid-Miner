package me.piedpiper.businesslogic;

import java.util.ArrayList;

public abstract class Worker { //A munkást reprezentáló absztrakt osztály a játékban, leszarmazik belőle a Settler és a Robot osztályok
    protected OrbitingObject location; //Az asztroida/teleport kapu, amin a Worker van

    public Worker(OrbitingObject location) {    //Worker konstruktora, beállítja a location-t
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.location = location;

        Logger.tabcount--;
    }

    public void MoveTo(OrbitingObject o){ //Athelyezi a Workert a paraméterban átvett OrbitingObjectre
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".MoveTo()");
        
        ArrayList<OrbitingObject> neighbors = location.GetNeighbors();  //Az OrbitingObjectek, amikre a Worker tud mozogni.
        if (neighbors.contains(o)) {    //Ha a paraméterként átvett OrbitingObject benne van a neighbours listában.
            location.RemoveWorker(this); //Az aktuális OrbitingObjectről eltávolítja a Workert.
            o.AddWorker(this); //Athelyezi a cél OrbitingObjectre.
            location=o; //Megváltoztatja a locationt az új helyzetére.
        }
        
        Logger.tabcount--;
    }

    public abstract void Die(); //A Worker meghal, megszűnik.

    public void DrillHole(){ //Lejjebb fúr egy réteget az OrbitingObject kérgén, ha lehetséges.
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".DrillHole()");
        
        location.DrilledOn();

        Logger.tabcount--;
    }
    
    public abstract void Explode(); //A Worker felrobban, radioaktív anyagok hatására fordulhat elő.

    public void SetLocation(OrbitingObject l){ //A paraméterként átvett OrbitingObjectre állítja a Worker location-ét.
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".SetLocation()");
        
        this.location=l;
        
        Logger.tabcount--;
    }

    public OrbitingObject GetLocation(){ //Visszatér a location értékével.
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".GetLocation()");
        Logger.tabcount--;
        return location;
    }
}
