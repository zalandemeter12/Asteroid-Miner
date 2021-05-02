package me.piedpiper.businesslogic;

import me.piedpiper.gui.EllipsePanel;
import me.piedpiper.gui.View;

import java.util.ArrayList;
import java.util.Random;

public class Ellipse2D {
    private AsteroidField field;
    // A koordinatarendszerben az ellipszis poziciojat derterminalo fokuszpontok
    private Point2D focalpoint0;
    private Point2D focalpoint1;
    // Az ellipszisen levo objektumok keringesi sebessege
    private double velocity;
    // Az ellipszisen keringo objektumok listaja
    private ArrayList<OrbitingObject> objects;
    // A tavolsag parameter, ami a fokuszpontokkal egyutt determinaljak az ellipszist
    private final double distance;
    private double a, b;
    private final int id;
    private static int currentIndex = 0;
    private View view;
    private EllipsePanel panel;

    // Konstruktor
    public Ellipse2D(Point2D focalpoint0, Point2D focalpoint1, double distance, double velocity, ArrayList<OrbitingObject> objects) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.objects = new ArrayList<>();
        this.focalpoint0 = focalpoint0;
        this.focalpoint1 = focalpoint1;
        this.distance = distance;
        this.velocity = velocity;
        this.objects = objects;
        this.id = ++currentIndex;

        Logger.tabcount--;
    }

    public Ellipse2D(Point2D focalpoint0, Point2D focalpoint1, double distance, double velocity, ArrayList<OrbitingObject> objects,View view) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.objects = new ArrayList<>();
        this.focalpoint0 = focalpoint0;
        this.focalpoint1 = focalpoint1;
        this.distance = distance;
        this.velocity = velocity;
        this.objects = objects;
        this.id = ++currentIndex;
        this.view = view;
        panel=new EllipsePanel(this);
        view.AddGraphicObject(panel);
        Logger.tabcount--;
    }

    public Ellipse2D(double a, double b, double distance, double velocity, ArrayList<OrbitingObject> objects) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.objects = new ArrayList<>();
        this.a = a;
        this.b = b;
        this.distance = distance;
        this.velocity = velocity;
        this.objects = objects;
        this.id = ++currentIndex;

        Logger.tabcount--;
    }


    public int GetId(){
        return id;
    }

    public double GetA(){return a;}
    public double GetB(){return b;}

    public void AddObject(OrbitingObject o){
        objects.add(o);
        Random rand = new Random();
        double tr = rand.nextDouble();
        boolean notClose = false;
        Point2D p = new Point2D(a * Math.cos(tr), b * Math.sin(tr));
        while(!notClose){
            tr += velocity;
            p = new Point2D(a * Math.cos(tr), b * Math.sin(tr));
            notClose = true;
            for(OrbitingObject or : objects){
                if(p.DistanceFrom(or.GetPosition()) < 10) //TODO find the real isClose value
                    notClose = false;
            }
        }
        o.SetPosition(p);
    }

    // Keringo objektum eltavolitasa az ellipszisrol
    public void RemoveObject(OrbitingObject o) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".RemoveObject()");
        objects.remove(o);
        Logger.tabcount--;
    }

    // Az keringo objektumok (aszteroidak, teleportkapuk) mozgatasa
    public void MoveOrbits() {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".MoveOrbits()");
        double t;
        for (OrbitingObject o : objects) {
            t = Math.acos(o.GetPosition().GetX() / a);
            o.SetPosition(new Point2D(a * Math.cos(t + velocity), b * Math.sin(t + velocity)));
        }

        Logger.tabcount--;
    }

    // Visszaad egy koordinatat, ahova lerakhatja a teleportkaput a hivo
    public Point2D GateLocation(Point2D p) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".GateLocation()");
        Logger.tabcount--;

        double t = Math.acos(p.GetX() / a);
        double shift = 0.1; //TODO find the real shift value
        return new Point2D(a * Math.cos(t + shift), b * Math.sin(t + shift));
    }

    // visszaadja a rajta keringo objektumok listajat
    public ArrayList<OrbitingObject> GetObjects() {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".GetObjects()");
        Logger.tabcount--;
        return objects;
    }

    public AsteroidField GetField() {
        return field;
    }

    public void SetField(AsteroidField field) {
        this.field = field;
    }

    public static void ResetIndex() {
        currentIndex=0;
    }
}
