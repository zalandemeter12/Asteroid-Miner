package me.piedpiper.businesslogic;

import me.piedpiper.gui.EllipsePanel;
import me.piedpiper.gui.View;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

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
    //szelleseg, magassag
    private double a, b;
    private final int id;
    //index
    private static int currentIndex = 0;
    //A kirajzolasert felelos peldany
    private View view;
    //A kirajzolhato objektum, ami ellipszist rajzol ki
    private EllipsePanel panel;

    // Konstruktor
    public Ellipse2D(Point2D focalpoint0, Point2D focalpoint1, double distance, double velocity, ArrayList<OrbitingObject> objects) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        //tagvaltozok beallitasa
        this.objects = new ArrayList<>();
        this.focalpoint0 = focalpoint0;
        this.focalpoint1 = focalpoint1;
        this.distance = distance;
        this.velocity = velocity;
        this.objects = objects;
        this.id = ++currentIndex;

        Logger.tabcount--;
    }

    //Masodik konstruktor, melyben az elozohoz kepest annzi a valtozas, hogy letrehoz egy EllipsePanel-t
    //amit atad a view-nak
    public Ellipse2D(Point2D focalpoint0, Point2D focalpoint1, double distance, double velocity, ArrayList<OrbitingObject> objects,View view) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        //tagvaltozok beallitasa
        this.objects = new ArrayList<>();
        this.focalpoint0 = focalpoint0;
        this.focalpoint1 = focalpoint1;
        this.distance = distance;
        this.velocity = velocity;
        this.objects = objects;
        this.id = ++currentIndex;
        this.view = view;
        a=sqrt(pow(focalpoint0.GetX(), 2)/0.75);
        b=a/2;
        panel=new EllipsePanel(this);
        view.AddGraphicObject(panel);
        Logger.tabcount--;
    }

    //Konstruktor, ami szelesseg es magassag alapjan adja meg az ellipszist
    public Ellipse2D(double a, double b, double distance, double velocity, ArrayList<OrbitingObject> objects, View view) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        //tagvaltozok beallitasa
        this.view = view;
        this.objects = new ArrayList<>();
        this.a = a;
        this.b = b;
        this.distance = distance;
        this.velocity = velocity;
        this.objects = objects;
        this.id = ++currentIndex;
        panel=new EllipsePanel(this);
        view.AddGraphicObject(panel);

        Logger.tabcount--;
    }

    //Visszaadja a ellipszis id-jat
    public int GetId(){
        return id;
    }

    //Getter az "a" attributumra
    public double GetA(){return a;}
    //Getter a "b" attributumra
    public double GetB(){return b;}

    //A fuggveny hozzaad egy OrbitingObjet-tet az oket tartalmazo listahoz
    public void AddObject(OrbitingObject o){
        objects.add(o);
        //a hozzaadas utan elhelyezi az objektumot, valmint beallitja a szukseges ertekeit
        Random rand = new Random();
        o.SetT(rand.nextDouble()*2*Math.PI);
        boolean notClose = false;
        Point2D p = new Point2D(a/2 * Math.cos(o.GetT()), b/2 * Math.sin(o.GetT()));
        while(!notClose){
            o.SetT(o.GetT() + velocity);
            p = new Point2D(a/2 * Math.cos(o.GetT()), b/2 * Math.sin(o.GetT()));
            notClose = true;
            for(OrbitingObject or : objects){
                if(p.DistanceFrom(or.GetPosition()) < 60) //TODO find the real isClose value
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
        for (OrbitingObject o : objects) {
            o.SetT(o.GetT() + velocity);
            o.SetPosition(new Point2D((a/2)*Math.cos(o.GetT()), (b/2) * Math.sin(o.GetT())));
        }
        Logger.tabcount--;
    }

    // Visszaad egy koordinatat, ahova lerakhatja a teleportkaput a hivo
    public Point2D GateLocation(OrbitingObject o) {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".GateLocation()");
        Logger.tabcount--;
        return new Point2D((a/2)*Math.cos(o.GetT() + 0.25), (b/2) * Math.sin(o.GetT()+0.25));
    }

    // visszaadja a rajta keringo objektumok listajat
    public ArrayList<OrbitingObject> GetObjects() {
        Logger.logMessage("Ellipse2D#" + Integer.toHexString(this.hashCode()) + ".GetObjects()");
        Logger.tabcount--;
        return objects;
    }

    //Getter, visszaadja az AsteroidFildet
    public AsteroidField GetField() {
        return field;
    }

    //Setter, bealitja az AsteroidFildet
    public void SetField(AsteroidField field) {
        this.field = field;
    }

    //Reseteli az ellipszis indexet a fuggveny
    public static void ResetIndex() {
        currentIndex=0;
    }

    //Setter, ami a sebesseget allitja be
    public void SetVelocity(double v){
        velocity=v;
    }
}
