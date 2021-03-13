package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class Ellipse2D {
    private final Point2D focalpoint0;
    private final Point2D focalpoint1;
    private double velocity;
    private ArrayList<OrbitingObject> objects;
    private final double distance;

    public Ellipse2D(Point2D focalpoint0, Point2D focalpoint1, double distance, double velocity, ArrayList<OrbitingObject> objects) {
        this.objects = new ArrayList<>();
        this.focalpoint0 = focalpoint0;
        this.focalpoint1 = focalpoint1;
        this.distance = distance;
        this.velocity = velocity;
        this.objects = objects;
        System.out.println("Ellipse2D.Ctor()");
    }

    public void RemoveObject(OrbitingObject o) {
        System.out.println("Ellipse2D.RemoveObject()");
    }

    public void MoveOrbits() {
        for (OrbitingObject o : objects) {
            o.SetPosition(new Point2D(12,21));
        }
        System.out.println("Ellipse2D.MoveOrbits()");
    }

    public Point2D GateLocation(Point2D p) {
        System.out.println("Ellipse2D.GateLocation()");
        return null;
    }

    public ArrayList<OrbitingObject> GetObjects() {
        return objects;
    }
}
