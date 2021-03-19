package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class Ellipse2D {
    private final Point2D focalpoint0;
    private final Point2D focalpoint1;
    private double velocity;
    private ArrayList<OrbitingObject> objects;
    private final double distance;

    public Ellipse2D(Point2D focalpoint0, Point2D focalpoint1, double distance, double velocity, ArrayList<OrbitingObject> objects) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.objects = new ArrayList<>();
        this.focalpoint0 = focalpoint0;
        this.focalpoint1 = focalpoint1;
        this.distance = distance;
        this.velocity = velocity;
        this.objects = objects;
 
        Logger.tabcount--;
    }

    public void RemoveObject(OrbitingObject o) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".RemoveObject()");
        Logger.tabcount--;
    }

    public void MoveOrbits() {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".MoveOrbits()");
        
        for (OrbitingObject o : objects) {
            o.SetPosition(new Point2D(12,21));
        }

        Logger.tabcount--;
    }

    public Point2D GateLocation(Point2D p) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".GateLocation()");
        Logger.tabcount--;
        return null;
    }

    public ArrayList<OrbitingObject> GetObjects() {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".GetObjects()");
        Logger.tabcount--;
        return objects;
    }
}
