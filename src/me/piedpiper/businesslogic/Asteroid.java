package me.piedpiper.businesslogic;

import me.piedpiper.gui.AsteroidPanel;
import me.piedpiper.gui.View;

//Az aszteroidát valósítja meg, tárolja a sziklarétegei számát,
//a tartalmazott nyersanyagát és hogy közel van-e a nap
public class Asteroid extends OrbitingObject {
    //Sziklarétegek száma
    protected int thickness;
    //Közel van-e a nap
    private boolean closeToSun;
    //A tartalmazott
    private Material material;
    private static int currentIndex = 0;
    private View view;
    private AsteroidPanel panel;

    //Kostruktor
    public Asteroid(Point2D position, Ellipse2D ellipse, int thickness, Material material) {
        super(position, ellipse);
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.thickness = thickness;
        this.closeToSun = false;
        this.material = material;
        this.id = ++currentIndex;
        Logger.tabcount--;
    }

    public Asteroid(Point2D position, Ellipse2D ellipse, int thickness, Material material,View view) {
        super(position, ellipse);
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.thickness = thickness;
        this.closeToSun = false;
        this.material = material;
        this.id = ++currentIndex;
        this.view = view;
        panel = new AsteroidPanel();
        view.AddGraphicObject(panel);
        Logger.tabcount--;
    }

    //Aszeroida megfúrása, csökkenti a sziklarétegek számát
    @Override
    public boolean DrilledOn() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".DrilledOn()");
        if (thickness <= 0) return false;
        thickness--;
        if (thickness == 0 && closeToSun && material != null) {
            material.BlowUp(this);
        }
        
        Logger.tabcount--;
        return true;
    }

    //Az aszteroid megfúrása, visszatér a tartalmazott nyersanyagggal
    @Override
    public Material RemoveMaterial() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".RemoveMaterial()");

        Material tmp = this.material;
        this.material = null;
        
        Logger.tabcount--;
        return tmp;
    }

    //Megpróbál lehelyezni egy nyersanyagot
    @Override
    public boolean AddMaterial(Material m) {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".AddMaterial()");
        
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

    //Az aszteroida felrobban
    @Override
    public void Explode() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".Explode()");
        if(material != null)
            material.BlowUp(this);
        Logger.tabcount--;
    }
    //Vastagság getter
    @Override
    public int GetThickness() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".GetThickness()");
        Logger.tabcount--;
        return this.thickness;
    }

    //nyersanyag setter
    @Override
    public void SetMaterial(Material m) {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".SetMaterial()");
        Logger.tabcount--;
        material = m;
    }

    //nyersanyag getter
    @Override
    public Material GetMaterial() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".GetMaterial()");
        Logger.tabcount--;
        return material;
    }

    //Megállapítja, hogy közel van-e a nap
    public boolean IsCloseToSun(){
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".IsCloseToSun()");
        Logger.tabcount--;
        return closeToSun;
    }
    //Nap közelség setter
    @Override
	public void SetCloseToSun(boolean c) {
		if(c) {
            material.BlowUp(this);
        }
        closeToSun = c;
    }

    public String GetName(){
        return "Asteroid" + id;
    }

    public static void ResetIndex(){
        currentIndex=0;
    }

    public AsteroidPanel GetPanel(){return panel;}
}
