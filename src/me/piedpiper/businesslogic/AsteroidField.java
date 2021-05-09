package me.piedpiper.businesslogic;

import java.util.ArrayList;

/**
 * Az aszteroida ovet valositja meg, tarolja a napot,
 * a jatekot amiben letre lett hozva, az ellipsziseket,
 * a telepeseket, a leptetheto objektumokat(robotok, ufok),
 * valamint az aktiv telepest.
 */
public class AsteroidField implements ISteppable {

    /**
     * Az osztaly attributumai
     * Az aszteroida mezon levo nap
     */
    private Sun sun;
    /**
     * A jatekmenetet kezelo objektum
     */
    private final Game game;
    /**
     * Az aszteroida mezon levo ellipszisek listaja, amiken az objektumok keringenek
     */
    private final ArrayList<Ellipse2D> ellipses;
    /**
     * A mezoben levo kontroller által iranyitott steppablek
     */
    private final ArrayList<ISteppable> steppables;
    /**
     * A mezoben levo telepesek listaja
     */
    private final ArrayList<Settler> settlers;
    /**
     * Ennek a valtozonak a segitsegevel lehet a randomitast ki/be kapcsolni
     */
    private boolean random = true;
    /**
     * Az aktiv telepes
     */
    private Settler activeSettler;


    /**
     * Konstruktor
     */
    public AsteroidField(Sun sun, Game game, ArrayList<Ellipse2D> ellipses, ArrayList<Settler> settlers){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * Az attributumok beallitasa a parametereknek megfeleloen
         */
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

    /**
     * Az ISteppable interface Step fuggvenyenek megvalositasa, ez a fuggveny felelos
     * az objektumok keringteteseert, es ebben hivodnak meg a tovabbi step fuggvenyek
     */
    public void Step() {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".Step()");

        for (Settler s: settlers) {
            s.SetCanStep(true);
        }

        /**
         * Nap step fuggvenyenek meghivasa
         */
        sun.Step();

        /**
         * egitestek keringetese
         */
        SetSunClose();
        /**
         * uj szomszedok beallitasa
         */
        SetNeighbours();

        /**
         * meghivja a leptetheto objektumok Step fuggvenyet
         */
        for (ISteppable s : steppables) {
            s.Step();
        }

        Logger.tabcount--;
    }

    /**
     * A mezon keringo objektumok uj poziciojanak beallitasat vegzi a fuggveny
     */
    public void SetSunClose(){
        ArrayList<OrbitingObject> orbitingObjects = new ArrayList<>();

        /**
         * Ellipszisen keringo objektumok uj poziciojanak beallitasa
         */
        for(Ellipse2D e: ellipses) {
            e.MoveOrbits();
            orbitingObjects.addAll(e.GetObjects());
        }
        /**
         * napkozelseg beallitasa a mezon levo objektumon az uj poziciojuk kiszamolasa utan
         */
        for(OrbitingObject o : orbitingObjects){
            o.SetCloseToSun(o.GetPosition().DistanceFrom(sun.GetPosition()) < 200);
        }
    }

    /**
     * Szomszedok beallitasat vegzi a fuggveny
     */
    public void SetNeighbours(){
        ArrayList<OrbitingObject> orbitingObjects = new ArrayList<>();

        /**
         * Ellipszisen keringo objektumok uj poziciojanak beallitasa
         */
        for(Ellipse2D e: ellipses) {
            e.MoveOrbits();
            orbitingObjects.addAll(e.GetObjects());
        }

        for (OrbitingObject orbitingObject : orbitingObjects) orbitingObject.GetNeighbors().clear();
        /**
         * Szomszedok beallitasa
         */
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

    /**
     * Uj steppable(robot vagy ufo) hozzaadasa az oket tartalmazo listahoz
     */
    public void AddSteppable(ISteppable s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".AddSteppable()");
        
        steppables.add(s);
        
        Logger.tabcount--;
    }

    /**
     * Steppable(robot vagy ufo) eltavolitasa az oket tartalmazo listabol
     */
    public void RemoveSteppable(ISteppable s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".RemoveSteppable()");
       
        steppables.remove(s);
        
        Logger.tabcount--;
    }

    /**
     * Telepes hozzaadasa az aszteroida mezoben levo telepesek listajahoz
     */
    public void AddSettler(Settler s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".AddSettler()");

        if (activeSettler == null)
            activeSettler = s;
        settlers.add(s);
        
        Logger.tabcount--;
    }

    /**
     * Telepes eltavolitasa a telepeseket tartalmazo listabol
     */
    public void RemoveSettler(Settler s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".RemoveSettler()");
        
        settlers.remove(s);
        if(settlers.isEmpty()) game.EndGame(false);
        
        Logger.tabcount--;
    }

    /**
     * A fuggveny vissza adja az aszteroida mezoben levo ellipsziseknek a listajat
     */
    public ArrayList<Ellipse2D> GetEllipses(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetEllipses()");
        Logger.tabcount--;
        return ellipses;
    }

    /**
     * A fuggveny visszaadja az aszteroida mezoben levo Nap objektumot
     */
    public Sun GetSun(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetSun()");
        Logger.tabcount--;
        return sun;
    }

    /**
     * A fuggveny visszaadja a Steppable-ket tartalmazo listat (robotok es ufok)
     */
    public ArrayList<ISteppable> GetSteppables(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetSteppables()");Logger.tabcount--;
        return steppables;
    }

    /**
     * Fuggveny, amely visszaadja az aszteroida mezoben levo telepeseket, a settlers listat
     */
    public ArrayList<Settler> GetSettlers(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetSettlers()");
        Logger.tabcount--;
        return settlers;
    }

    /**
     * Visszaadja a random attributum erteket
     */
    public boolean IsRandom() {
        return random;
    }

    /**
     * Beallitja a random attributum ertekert
     */
    public void SetRandom(boolean random) {
        this.random = random;
    }

    /**
     * Telepes lepese utan vegez muveleteket a fuggveny
     */
    public void SettlerStepped() {
        ArrayList<Settler> deadSettlers=new ArrayList<>();
        /**
         * ellenorzi, hogy eletben van e a telepes
         */
        for (Settler s: settlers) {
            if(s.IsDead()){
                deadSettlers.add(s);
            } else if(s.CanStep()) {
                activeSettler = s;
                return;
            }
        }
        /**
         * A halott telepeseket torli az ket tartalmazo listabol
         */
        for(Settler s: deadSettlers){
            settlers.remove(s);
        }
        /**
         * Ha nicsen mar elo telepes, akkor vege van a jateknak
         */
        if(settlers.size() == 0){
            game.EndGame(false);
        }
        /**
         * kovetkezo korre lepes
         */
        game.NextRound();
    }

    /**
     * Visszaadja az aktiv telepest
     */
    public Settler GetActiveSettler() {
        return activeSettler;
    }

    /**
     * Beallitja az aktiv telepest
     */
    public void SetActiveSettler(Settler s) {
        activeSettler = s;
    }

}
