package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class Ellipse2D {
    // A koordinatarendszerben az ellipszis poziciojat derterminalo fokuszpontok
    private final Point2D focalpoint0;
    private final Point2D focalpoint1;

    // Az ellipszisen levo objektumok keringesi sebessege
    private double velocity;

    // Az ellipszisen keringo objektumok listaja
    private ArrayList<OrbitingObject> objects;

    // A tavolsag parameter, ami a fokuszpontokkal egyutt determinaljak az ellipszist
    private final double distance;

    // Konstruktor
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

    // Keringo objektum eltavolitasa az ellipszisrol
    public void RemoveObject(OrbitingObject o) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".RemoveObject()");
        Logger.tabcount--;
    }

    // Az keringo objektumok (aszteroidak, teleportkapuk) mozgatasa
    public void MoveOrbits() {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".MoveOrbits()");
        
        for (OrbitingObject o : objects) {
            o.SetPosition(new Point2D(12,21));
        }

        Logger.tabcount--;
    }

    // Visszaad egy koordinatat, ahova lerakhatja a teleportkaput a hivo
    public Point2D GateLocation(Point2D p) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".GateLocation()");
        Logger.tabcount--;
        return null;
    }

    // visszaadja a rajta keringo objektumok listajat
    public ArrayList<OrbitingObject> GetObjects() {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".GetObjects()");
        Logger.tabcount--;
        return objects;
    }
}
