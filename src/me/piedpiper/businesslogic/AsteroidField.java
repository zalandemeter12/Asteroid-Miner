package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class AsteroidField implements ISteppable {

    //Az osztaly attributumai
    //Az aszteroida mezon levo nap
    private Sun sun;
    //A jatek amiben az aszteroidamezo letre lett hozva
    private final Game game;
    //Az aszteroida mezon levo ellipszisek listaja, amiken az objektumok keringenek
    private final ArrayList<Ellipse2D> ellipses;
    //A mezoben levo kontroller által iranyitott steppablek
    private final ArrayList<ISteppable> steppables;
    private final ArrayList<Settler> settlers;
    private boolean random = true;
    private Settler activeSettler;


    //Konstruktor
    public AsteroidField(Sun sun, Game game, ArrayList<Ellipse2D> ellipses, ArrayList<Settler> settlers){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        //Az attributumok beallitasa a parametereknek megfeleloen
        this.sun = sun;
        this.game = game;
        this.ellipses = ellipses;
        this.steppables = new ArrayList<>();
        this.settlers = settlers;
        if (this.settlers.size() > 0)
            activeSettler = this.settlers.get(0);
        else
            activeSettler = null;

        Logger.tabcount--;
    }

    //Az ISteppable interface Step fuggvenyenek megvalositasa, ez a fuggveny felelos az objektumok keringteteseert, es ebben hivodnak meg a tovabbi step fuggvenyek
    public void Step() {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".Step()");

        for (Settler s: settlers) {
            s.SetCanStep(true);
        }

        //Nap step fuggvenyenek meghivasa
        sun.Step();

        SetSunClose();
        SetNeighbours();

        for (ISteppable s : steppables) {
            s.Step();
        }

        Logger.tabcount--;
    }

    public void SetSunClose(){
        ArrayList<OrbitingObject> orbitingObjects = new ArrayList<>();

        //Ellipszisen keringo objektumok uj poziciojanak beallitasa
        for(Ellipse2D e: ellipses) {
            e.MoveOrbits();
            orbitingObjects.addAll(e.GetObjects());
        }
        for(OrbitingObject o : orbitingObjects){
            if(o.GetPosition().DistanceFrom(sun.GetPosition()) < 200)
                o.SetCloseToSun(true);
            else
                o.SetCloseToSun(false);
        }
    }

    public void SetNeighbours(){
        ArrayList<OrbitingObject> orbitingObjects = new ArrayList<>();

        //Ellipszisen keringo objektumok uj poziciojanak beallitasa
        for(Ellipse2D e: ellipses) {
            e.MoveOrbits();
            orbitingObjects.addAll(e.GetObjects());
        }

        for (OrbitingObject orbitingObject : orbitingObjects) orbitingObject.GetNeighbors().clear();
        //Szomszedok beallitasa
        for(int i = 0; i < orbitingObjects.size(); i++){
            for(int j = 0; j < orbitingObjects.size(); j++){
                if(i != j){
                    OrbitingObject o1 = orbitingObjects.get(i);
                    OrbitingObject o2 = orbitingObjects.get(j);
                    if(o1.GetPosition().DistanceFrom(o2.GetPosition()) < 100){
                        o1.AddNeighbor(o2);
                    }
                }
            }
        }
    }

    public void AddSteppable(ISteppable s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".AddSteppable()");
        
        steppables.add(s);
        
        Logger.tabcount--;
    }

    public void RemoveSteppable(ISteppable s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".RemoveSteppable()");
       
        steppables.remove(s);
        
        Logger.tabcount--;
    }

    //Telepes hozzaadasa az aszteroida mezoben levo telepesek listajahoz
    public void AddSettler(Settler s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".AddSettler()");

        if (activeSettler == null)
            activeSettler = s;
        settlers.add(s);
        
        Logger.tabcount--;
    }

    //Telepes eltavolitasa a telepeseket tartalmazo listabol
    public void RemoveSettler(Settler s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".RemoveSettler()");
        
        settlers.remove(s);
        if(settlers.isEmpty()) game.EndGame(false);
        
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

    public ArrayList<ISteppable> GetSteppables(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetSteppables()");
        Logger.tabcount--;
        return steppables;
    }

    //Fuggveny, amely visszaadja az aszteroida mezoben levo telepeseket, a settlers listat
    public ArrayList<Settler> GetSettlers(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetSettlers()");
        Logger.tabcount--;
        return settlers;
    }

    public boolean IsRandom() {
        return random;
    }

    public void SetRandom(boolean random) {
        this.random = random;
    }

    public void SettlerStepped() {
        ArrayList<Settler> deadSettlers=new ArrayList<>();
        for (Settler s: settlers) {
            if(s.IsDead()){
                deadSettlers.add(s);
            } else if(s.CanStep()) {
                activeSettler = s;
                return;
            }
        }
        for(Settler s: deadSettlers){
            settlers.remove(s);
        }
        if(settlers.size() == 0){
            game.EndGame(false);
        }
        game.NextRound();
    }

    public Settler GetActiveSettler() {
        return activeSettler;
    }

    public void SetActiveSettler(Settler s) {
        activeSettler = s;
    }

    public void SetSun(Sun s){
        sun=s;
    }
}
