package me.piedpiper.businesslogic;

import java.util.ArrayList;

public abstract class OrbitingObject {
    /**
     * Az osztaly attributumai
     * A lista a szomszedos objektumokat tartalmazza
     */
    protected ArrayList<OrbitingObject> neighbors;
    /**
     * Az objektum pozicioja
     */
    protected Point2D position;
    /**
     * AZ ellipszis, amin az objektum talalhato
     */
    protected Ellipse2D ellipse;
    /**
     * Lista az objektumon levo munkasokrol(telepesek es robotok)
     */
    protected ArrayList<Worker> workers;

    private double t;

    protected int id;

    /**
     * Konstruktor
     */
    public OrbitingObject(Point2D position, Ellipse2D ellipse){
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.neighbors  = new ArrayList<>();
        this.position = position;
        this.ellipse = ellipse;
        this.workers = new ArrayList<>();
        if(ellipse!=null)
            ellipse.AddObject(this);
       
        Logger.tabcount--;
    }

    /**
     * Munkas hozzaadasa a munkasokat tartalmazo listahoz
     */
    public void AddWorker(Worker w) {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".AddWorker()");
        
        workers.add(w);
        
        Logger.tabcount--;
    }

    /**
     * A parameterben kapott munkas torlese az oket tartalmazo listabol
     */
    public void RemoveWorker(Worker w) {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".RemoveWorker()");
        
        workers.remove(w);
        
        Logger.tabcount--;
    }

    /**
     * Az objektumon valo furas eseten meghivodo fuggveny
     */
    public boolean DrilledOn() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".DrilledOn()");
        Logger.tabcount--;
        return false;
    }

    /**
     * Nyesranyag hozzaadasa az objektumhoz
     */
    public boolean AddMaterial(Material m) {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".AddMaterial()");
        Logger.tabcount--;
        return false;
    }

    /**
     * Nyersanyag eltavolitasa az objektumrol
     */
    public Material RemoveMaterial() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".RemoveMaterial()");
        Logger.tabcount--;
        return null;
    }

    /**
     * A fuggveny visszaadja az objektumon levo munkasokat (telepesek es robotok), akik nincsenek vedelem alatt a napvihar alatt
     */
    public ArrayList<Worker> GetExposedWorkers() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetExposedWorkers()");
        Logger.tabcount--;
        return workers;
    }

    /**
     * A objektum felrobbanasa eseten meghivodo fuggveny
     */
    public void Explode() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".Explode()");
        Logger.tabcount--;
    }

    /**
     * A fuggveny visszaadja az objektum kopenyenek a vastagsagat
     */
    public int GetThickness() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetThickness()");
        Logger.tabcount--;
        return -1;
    }

    /**
     * A fuggveny hozzaad egy objektumot az objektum szomszedjait nyilvantarto listahoz
     */
    public void AddNeighbor(OrbitingObject o) {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".AddNeighbour()");

        if (!neighbors.contains(o))
            neighbors.add(o);
        
        Logger.tabcount--;
    }

    /**
     * A fuggveny visszaadja az objektum szomszedjait tartalmazo listat
     */
    public ArrayList<OrbitingObject> GetNeighbors() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetNeighbors()");
        Logger.tabcount--;
        return neighbors;
    }

    /**
     * A fuggveny beallitja az objektumon levo nyersanyagot
     */
    public void SetMaterial(Material m) {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".SetMaterial()");
        Logger.tabcount--;
    }

    /**A fuggveny visszaadja az objektumon levo nyersanyagot
     */
    public Material GetMaterial() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetMaterial()");
        Logger.tabcount--;
        return null;
    }

    /**
     * A fuggveny visszaadja az objektum poziciojat
     */
    public Point2D GetPosition() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetPosition()");
        Logger.tabcount--;
        return position;
    }

    /**
     * A fuggveny beallitja az objektum poziciojat
     */
    public void SetPosition(Point2D p) {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".SetPosition()");
        
        position = p;
        
        Logger.tabcount--;
    }

    /**
     * A fuggveny visszaadja, hogy az objektum kozel van-e a naphoz
     */
    public boolean IsCloseToSun() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".IsCloseToSun()");
        Logger.tabcount--;
        return false;
    }

    /**
     * A fuggveny visszaadja a munkasokat tartalmazo listat
     */
    public ArrayList<Worker> GetWorkers() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetWorkers()");
        Logger.tabcount--;
        return workers;
    }

    /**
     * A fuggveny visszaadj az elipszist amin az objektum tartozkodik
     */
    public Ellipse2D GetEllipse() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetEllipse()");
        Logger.tabcount--;
        return ellipse;
    }

    /**
     * A fuggveny visszaadja a bazisaszteroida ladajaban levo nyersanyagok listajat
     */
    public ArrayList<Material> GetChest() {
        Logger.logMessage("OrbitingObject#" + Integer.toHexString(this.hashCode()) + ".GetChest()");
        Logger.tabcount--;
        return null;
    }

    /**
     * Setter, beallitja hogy az objektum kozel van e a naphoz
     */
    public void SetCloseToSun(boolean c){ }

    /**
     * napvihar eseten a fuggveny a rajta levo vedtelen munkasokat kezeli
     */
    public void UnderSolarStorm(){
        /**
         * ha nincs vedtelen munkas, akkor visszater
         */
        if(GetExposedWorkers().size()==0) return;
        ArrayList<Worker> deadWorkers=new ArrayList<>();
        /**
         * a vedtelen munkasoknak meghivja a Die fuggvenyet,
         * es hozzaadja oket a halott munkasokat tartalmazo listahoz
         */
        for (Worker w : GetExposedWorkers()) {
            w.Die();
            if(w.IsDead()) deadWorkers.add(w);
        }

        /**
         * eltavolitja magarol a halott mhnkasokat
         */
        for (Worker w : deadWorkers)
            workers.remove(w);
    }

    /**
     * objektum mozgasa eseten beallitja az uj poziciojat
     */
    public void Moves(Point2D p){
        this.position = p;
    }

    /**
     * A fuggveny visszadja a OrbotingObject nevet
     */
    public abstract String GetName();

    /**
     * A fuggveny visszaadja a indexet
     */
    public int GetIndex(){
        return id;
    }

    /**
     * A fuggveny visszaadja a 't' attributumot
     */
    public double GetT() {
        return t;
    }

    /**
     * A fuggveny beallitja a 't' attributumot
     */
    public void SetT(double t) {
        this.t = t;
    }

}
