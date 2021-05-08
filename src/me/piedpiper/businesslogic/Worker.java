package me.piedpiper.businesslogic;

import java.util.ArrayList;

/**
 * A munkást reprezentáló absztrakt osztály a játékban, leszarmazik belőle a Settler és a Robot osztályok
 */
public abstract class Worker {
    /**
     * Az asztroida/teleport kapu, amin a Worker van
     */
    protected OrbitingObject location;
    /**
     * index
     */
    protected int id;
    /**
     * halott-e
     */
    protected boolean dead=false;

    /**
     * Worker konstruktora, beállítja a location-t
     */
    public Worker(OrbitingObject location) {
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.location = location;
        location.AddWorker(this);

        Logger.tabcount--;
    }

    /**
     *  a Workert a paraméterban átvett OrbitingObjectre
     */
    public void MoveTo(OrbitingObject o){
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".MoveTo()");
        /**
         * Az OrbitingObjectek, amikre a Worker tud mozogni.
         */
        ArrayList<OrbitingObject> neighbors = location.GetNeighbors();
        /**
         * Ha a paraméterként átvett OrbitingObject benne van a neighbours listában.
         */
        if (neighbors.contains(o)) {
            /**
             * Az aktuális OrbitingObjectről eltávolítja a Workert.
             */
            location.RemoveWorker(this);
            /**
             * Megváltoztatja a locationt az új helyzetére.
             */
            location=o;
            /**
             * Áthelyezi a cél OrbitingObjectre.
             */
            o.AddWorker(this);
        }
        
        Logger.tabcount--;
    }

    /**
     * A Worker meghal, megszűnik.
     */
    public abstract void Die();

    /**
     * Lejjebb fúr egy réteget az OrbitingObject kérgén, ha lehetséges.
     */
    public void DrillHole(){
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".DrillHole()");
        
        location.DrilledOn();

        Logger.tabcount--;
    }

    /**
     * A Worker felrobban, radioaktív anyagok hatására fordulhat elő.
     */
    public abstract void Explode();

    /**
     * A paraméterként átvett OrbitingObjectre állítja a Worker location-ét.
     */
    public void SetLocation(OrbitingObject l){
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".SetLocation()");
        
        this.location=l;
        
        Logger.tabcount--;
    }

    /**
     * Visszatér a location értékével.
     */
    public OrbitingObject GetLocation(){
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".GetLocation()");
        Logger.tabcount--;
        return location;
    }

    /**
     * A fuggveny banyaszas eseten hivodik meg
     */
    public void Mine(){
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".Mine()");
        Logger.tabcount--;
    }

    /**
     * A fuggveny visszaadja a munkas nevet
     */
    public abstract String GetName();


    /**A
     *  fuggveny visszaadja a munkas indexet
     */
    public int GetIndex(){
        return id;
    }

    /**
     * A fuggveny visszaadja, hogy a munkas halott-e
     */
    public boolean IsDead(){
        return dead;
    }
}
