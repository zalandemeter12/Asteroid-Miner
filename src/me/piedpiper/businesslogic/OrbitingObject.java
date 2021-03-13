package me.piedpiper.businesslogic;

import java.util.ArrayList;

public abstract class OrbitingObject {
    protected ArrayList<OrbitingObject> neighbors;
    protected Point2D position;
    protected Ellipse2D ellipse;
    protected ArrayList<Worker> workers;

    public OrbitingObject(Point2D position, Ellipse2D ellipse){
        this.neighbors  = new ArrayList<>();
        this.position = position;
        this.ellipse = ellipse;
        this.workers = new ArrayList<>();
        System.out.println("OrbitingObject.Ctor()");
    }

    public void AddWorker(Worker w) {
        workers.add(w);
        System.out.println("OrbitingObject.AddWorker()");
    }

    public void RemoveWorker(Worker w) {
        workers.remove(w);
        System.out.println("OrbitingObject.RemoveWorker()");
    }

    public void DrilledOn() {
        System.out.println("OrbitingObject.DrilledOn()");
    }

    public boolean AddMaterial(Material m) {
        System.out.println("OrbitingObject.AddMaterial()");
        return false;
    }

    public void RemoveMaterial() {
        System.out.println("OrbitingObject.RemoveMaterial()");
    }

    public ArrayList<Worker> GetExposedWorkers() {
        System.out.println("OrbitingObject.GetExposedWorkers()");
        return null;
    }

    public void Explode() {
        System.out.println("OrbitingObject.Explode()");
    }

    public int GetThickness() {
        System.out.println("OrbitingObject.GetThickness()");
        return -1;
    }

    public void AddNeighbor(OrbitingObject o) {
        neighbors.add(o);
        System.out.println("OrbitingObject.AddNeighbor()");
    }

    public ArrayList<OrbitingObject> GetNeighbors() {
        return neighbors;
    }

    public void SetMaterial(Material m) {

    }

    public Material GetMaterial() {
        return null;
    }

    public Point2D GetLocation(){
        return position;
    }
}
