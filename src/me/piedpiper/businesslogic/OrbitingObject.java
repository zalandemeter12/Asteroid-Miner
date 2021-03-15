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

    public Material RemoveMaterial() {
        System.out.println("OrbitingObject.RemoveMaterial()");
        return null;
    }

    public ArrayList<Worker> GetExposedWorkers() {
        System.out.println("OrbitingObject.GetExposedWorkers()");
        return new ArrayList<Worker>();
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

    public Point2D GetPosition() {
        return position;
    }

    public void SetPosition(Point2D p) {
        position = p;
    }

    public boolean IsCloseToSun() {
        return false;
    }

    public ArrayList<Worker> GetWorkers() {
        return workers;
    }

    public Ellipse2D GetEllipse() {
        return ellipse;
    }

    public ArrayList<Material> GetChest() {
        return null;
    }
}
