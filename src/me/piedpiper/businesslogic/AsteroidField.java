package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class AsteroidField implements ISteppable {

    //Az osztaly attributumai
    //Az aszteroida mezon levo nap
    private Sun sun;
    //A jatek amiben az aszteroidamezo letre lett hozva
    private Game game;
    //Az aszteroida mezon levo ellipszisek listaja, amiken az objektumok keringenek
    private ArrayList<Ellipse2D> ellipses;
    //A mezoben levo kontroller által iranyitott workerek(robot, ufo)
    private ArrayList<ISteppable> steppable;
    //A mezoben levo elo telepesek
    private ArrayList<Settler> settlers;

    //Konstruktor
    public AsteroidField(Sun sun, Game game, ArrayList<Ellipse2D> ellipses, ArrayList<Settler> settlers){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        //Az attributumok beallitasa a parametereknek megfeleloen
        this.sun = sun;
        this.game = game;
        this.ellipses = ellipses;
        this.steppable = new ArrayList<>();
        this.settlers = settlers;

        Logger.tabcount--;
    }

    //Az ISteppable interface Step fuggvenyenek megvalositasa, ez a fuggveny felelos az objektumok keringteteseert, es ebben hivodnak meg a tovabbi step fuggvenyek
    public void Step() {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".Step()");

        //Nap step fuggvenyenek meghivasa
        sun.Step();
        ArrayList<OrbitingObject> orbitingObjects = new ArrayList<OrbitingObject>();    

        //Ellipszisen keringo objektumok uj poziciojanak beallitasa
        for(Ellipse2D e: ellipses) {
            e.MoveOrbits();
            orbitingObjects.addAll(e.GetObjects());
        }

        //Szomszedok beallitasa
        for(int i = 0; i < orbitingObjects.size(); i++){
            for(int j = 0; j < orbitingObjects.size(); j++){
                if(i != j){
                    OrbitingObject o1 = orbitingObjects.get(i);
                    OrbitingObject o2 = orbitingObjects.get(j);
                    if(o1.GetPosition().DistanceFrom(o2.GetPosition()) < 10){
                        o1.AddNeighbor(o2);
                    }
                }
            }
        }

        for (ISteppable s : steppable) {
            s.Step();
        }

        Logger.tabcount--;
    }

    public void AddSteppable(ISteppable s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".AddSteppable()");
        
        steppable.add(s);
        
        Logger.tabcount--;
    }

    public void RemoveSteppable(ISteppable s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".RemoveSteppable()");
       
        steppable.remove(s);
        
        Logger.tabcount--;
    }

    //Telepes hozzaadasa az aszteroida mezoben levo telepesek listajahoz
    public void AddSettler(Settler s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".AddSettler()");
        
        settlers.add(s);
        
        Logger.tabcount--;
    }

    //Telepes eltavolitasa a telepeseket tartalmazo listabol
    public void RemoveSettler(Settler s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".RemoveSettler()");
        
        settlers.remove(s);
        if(settlers.isEmpty()) game.EndGame();
        
        Logger.tabcount--;
    }

    //A fuggveny vissza adja az aszteroida mezoben levo ellipsziseknek a listajat
    public ArrayList<Ellipse2D> GetEllipses(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetEllipses()");
        Logger.tabcount--;
        return ellipses;
    }

    //A fuggveny visszaadja az aszteroida mezoben levo Nap objektumot
    public Sun GetSun(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetSun()");
        Logger.tabcount--;
        return sun;
    }

    public ArrayList<ISteppable> GetSteppable(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetSteppable()");
        Logger.tabcount--;
        return steppable;
    }

    //Fuggveny, amely visszaadja az aszteroida mezoben levo telepeseket, a settlers listat
    public ArrayList<Settler> GetSettlers(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetSettlers()");
        Logger.tabcount--;
        return settlers;
    }
}
