package me.piedpiper.businesslogic;

import me.piedpiper.gui.AsteroidPanel;
import me.piedpiper.gui.View;

import java.util.ArrayList;

/**
 * Az aszteroidát valósítja meg, tárolja a sziklarétegei számát,
 *a tartalmazott nyersanyagát és hogy közel van-e a nap
 *valamint ismeri az altala letrehozott kirajzolhato objektumot
 */
public class Asteroid extends OrbitingObject {
    /**
     * Sziklarétegek száma
     */
    protected int thickness;
    /**
     * Közel van-e a nap
    */
    private boolean closeToSun;
    /**
     * A tartalmazott nyersanyag
     */
    private Material material;
    /**
     * index
     */
    private static int currentIndex = 0;
    /**
     * A kirajzolasert felelos peldany
     */
    private View view;
    /**
     * A kirajzolhato objektum, ami aszteroidat rajzol ki
     */
    private AsteroidPanel panel;

    /**
     * Kostruktor
     */
    public Asteroid(Point2D position, Ellipse2D ellipse, int thickness, Material material) {
        super(position, ellipse);
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * tagvaltozok beallitasa
         */
        this.thickness = thickness;
        this.closeToSun = false;
        this.material = material;
        this.id = ++currentIndex;
        Logger.tabcount--;
    }

    /**
     * Masodik konstruktor, melyben az elozohoz kepest annzi a valtozas, hogy letrehoz egy AsteroidPanel-t
     * amit atad a view-nak
     */

    public Asteroid(Point2D position, Ellipse2D ellipse, int thickness, Material material,View view) {
        super(position, ellipse);
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * tagvaltozok beallitasa
         */
        this.thickness = thickness;
        this.closeToSun = false;
        this.material = material;
        this.id = ++currentIndex;
        this.view = view;
        panel = new AsteroidPanel(this);
        view.AddGraphicObject(panel);
        Logger.tabcount--;
    }

    /**
     * Aszeroida megfúrása, csökkenti a sziklarétegek számát
     */
    @Override
    public boolean DrilledOn() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".DrilledOn()");
        /**
         * Ha az azteroida thickness-e nem nagyobb 0-nal, akkor visszaterunk hamissal, sikereltelen a furas
         */
        if (thickness <= 0) return false;
        thickness--;
        /**
         * a nyersanyag BlowUp fuggvenye meghivodik, ha az aszteroida kozel van a naphoz es van benne fedetlen nyersanyag
         */
        if (thickness == 0 && closeToSun && material != null) {
            material.BlowUp(this, false);
        }
        
        Logger.tabcount--;
        return true;
    }

    /**
     * Nyersanyag eltavolitasa, visszatér a tartalmazott nyersanyagggal
     */
    @Override
    public Material RemoveMaterial() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".RemoveMaterial()");

        Material tmp = this.material;
        this.material = null;
        
        Logger.tabcount--;
        return tmp;
    }

    /**
     * Megpróbál lehelyezni egy nyersanyagot
     */
    @Override
    public boolean AddMaterial(Material m) {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".AddMaterial()");

        /**
         * Csak akkor tud lehelyezni nyersanyagot, ha nincs az aszteroidanak retege es nyersanyaga
         */
        if (material == null && thickness==0) {
            this.material = m;
            
            Logger.tabcount--;
            return true;
        }
        else {
            Logger.tabcount--;
            return false;    
        }
    }

    /**
     * Az aszteroida felrobban
     */
    @Override
    public void Explode() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".Explode()");

        /**
         * Meghivja a rajta tartozkodo munkasok Die fuggvenyet
         */
        for(Worker w : workers)
            w.Die();

        /**
         * eltavolitja magat az ellipszisrol, amin van, illetve a view-bol eltavolitja az ot kirajzolo objektumot
         */
        ellipse.RemoveObject(this);
        view.RemoveGraphicObject(this.panel);
        Logger.tabcount--;
    }

    /**
     * Vastagság getter
     */
    @Override
    public int GetThickness() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".GetThickness()");
        Logger.tabcount--;
        return this.thickness;
    }

    /**
     * nyersanyag setter
     */
    @Override
    public void SetMaterial(Material m) {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".SetMaterial()");
        Logger.tabcount--;
        material = m;
    }

    /**
     * nyersanyag getter
     */
    @Override
    public Material GetMaterial() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".GetMaterial()");
        Logger.tabcount--;
        return material;
    }

    /**
     * Megállapítja, hogy közel van-e a nap
     */
    public boolean IsCloseToSun(){
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".IsCloseToSun()");
        Logger.tabcount--;
        return closeToSun;
    }

    /**
     * Nap közelség setter
     */
    @Override
	public void SetCloseToSun(boolean c) {
		if(c && material != null && thickness == 0) {
            material.BlowUp(this, false);
        }
        closeToSun = c;
    }

    /**
     * Visszaadja az aszteroida nevet
     */
    public String GetName(){
        return "Asteroid" + id;
    }

    /**
     * reseteli az aszteroida indexet
     */
    public static void ResetIndex(){
        currentIndex=0;
    }

    /**
     * Visszaadja az aszteroida Panel-jet, ami a kirajzolashoz szukseges
     */
    public AsteroidPanel GetPanel(){return panel;}

    /**
     * Visszaadja azoknak a telepeseknek a listajat, amelyek napvihar eseten nincsenek
     * vedett allapotban
     */
    public ArrayList<Worker> GetExposedWorkers() {
        if(thickness==0 && material==null){
            return new ArrayList<>();
        }
        return workers;
    }
}
