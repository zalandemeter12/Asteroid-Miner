package me.piedpiper.businesslogic;

import java.util.ArrayList;

public abstract class Worker { //A munkást reprezentáló absztrakt osztály a játékban, leszarmazik belőle a Settler és a Robot osztályok
    protected OrbitingObject location; //Az asztroida/teleport kapu, amin a Worker van
    //index
    protected int id;
    //halott-e
    protected boolean dead=false;

    public Worker(OrbitingObject location) {    //Worker konstruktora, beállítja a location-t
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.location = location;
        location.AddWorker(this);

        Logger.tabcount--;
    }

    public void MoveTo(OrbitingObject o){ //Áthelyezi a Workert a paraméterban átvett OrbitingObjectre
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".MoveTo()");
        
        ArrayList<OrbitingObject> neighbors = location.GetNeighbors();  //Az OrbitingObjectek, amikre a Worker tud mozogni.
        if (neighbors.contains(o)) {    //Ha a paraméterként átvett OrbitingObject benne van a neighbours listában.
            location.RemoveWorker(this); //Az aktuális OrbitingObjectről eltávolítja a Workert.
            location=o; //Megváltoztatja a locationt az új helyzetére.
            o.AddWorker(this); //Áthelyezi a cél OrbitingObjectre.
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

    //A fuggveny banyaszas eseten hivodik meg
    public void Mine(){
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".Mine()");
        Logger.tabcount--;
    }

    //A fuggveny visszaadja a munkas nevet
    public abstract String GetName();

    //A fuggveny visszaadja a munkas indexet
    public int GetIndex(){
        return id;
    }

    //A fuggveny visszaadja, hogy a munkas halott-e
    public boolean IsDead(){
        return dead;
    }
}
