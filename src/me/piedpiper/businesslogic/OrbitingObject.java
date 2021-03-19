package me.piedpiper.businesslogic;

import java.util.ArrayList;

public abstract class OrbitingObject {
    protected ArrayList<OrbitingObject> neighbors;
    protected Point2D position;
    protected Ellipse2D ellipse;
    protected ArrayList<Worker> workers;

    public OrbitingObject(Point2D position, Ellipse2D ellipse){
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.neighbors  = new ArrayList<>();
        this.position = position;
        this.ellipse = ellipse;
        this.workers = new ArrayList<>();
       
        Logger.tabcount--;
    }

    public void AddWorker(Worker w) {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".AddWorker()");
        
        workers.add(w);
        
        Logger.tabcount--;
    }

    public void RemoveWorker(Worker w) {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".RemoveWorker()");
        
        workers.remove(w);
        
        Logger.tabcount--;
    }

    public void DrilledOn() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".DrilledOn()");
        Logger.tabcount--;
    }

    public boolean AddMaterial(Material m) {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".AddMaterial()");
        Logger.tabcount--;
        return false;
    }

    public Material RemoveMaterial() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".RemoveMaterial()");
        Logger.tabcount--;
        return null;
    }

    public ArrayList<Worker> GetExposedWorkers() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetExposedWorkers()");
        Logger.tabcount--;
        return new ArrayList<Worker>();
    }

    public void Explode() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".Explode()");
        Logger.tabcount--;
    }

    public int GetThickness() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetThickness()");
        Logger.tabcount--;
        return -1;
    }

    public void AddNeighbor(OrbitingObject o) {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".AddNeighbour()");
        
        neighbors.add(o);
        
        Logger.tabcount--;
    }

    public ArrayList<OrbitingObject> GetNeighbors() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetNeighbors()");
        Logger.tabcount--;
        return neighbors;
    }

    public void SetMaterial(Material m) {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".SetMaterial()");
        Logger.tabcount--;
    }

    public Material GetMaterial() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetMaterial()");
        Logger.tabcount--;
        return null;
    }

    public Point2D GetPosition() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetPosition()");
        Logger.tabcount--;
        return position;
    }

    public void SetPosition(Point2D p) {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".SetPosition()");
        
        position = p;
        
        Logger.tabcount--;
    }

    public boolean IsCloseToSun() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".IsCloseToSun()");
        Logger.tabcount--;
        return false;
    }

    public ArrayList<Worker> GetWorkers() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetWorkers()");
        Logger.tabcount--;
        return workers;
    }

    public Ellipse2D GetEllipse() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetEllipse()");
        Logger.tabcount--;
        return ellipse;
    }

    public ArrayList<Material> GetChest() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetChest()");
        Logger.tabcount--;
        return null;
    }
}
