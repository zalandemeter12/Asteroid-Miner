package me.piedpiper.businesslogic;

public class Asteroid extends OrbitingObject {
    private int thickness;
    private boolean closeToSun;
    private Material material;

    public Asteroid(Point2D position, Ellipse2D ellipse, int thickness, Material material) {
        super(position, ellipse);
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.thickness = thickness;
        this.closeToSun = false;
        this.material = material;
        
        Logger.tabcount--;
    }

    @Override
    public void DrilledOn() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".DrilledOn()");
       

        if (thickness == 0 && closeToSun) {
            material.BlowUp(this);
        } else {
            thickness--;
        }
        
        Logger.tabcount--;
    }

    @Override
    public Material RemoveMaterial() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".RemoveMaterial()");
        

        Material tmp = this.material;
        this.material = null;
        
        Logger.tabcount--;
        return tmp;
    }

    @Override
    public boolean AddMaterial(Material m) {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".AddMaterial()");
        
        if (material == null) {
            this.material = m;
            
            Logger.tabcount--;
            return true;
        }
        else {
            Logger.tabcount--;
            return false;    
        }
    }

    @Override
    public void Explode() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".Explode()");
        Logger.tabcount--;
    }

    @Override
    public int GetThickness() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".GetThickness()");
        Logger.tabcount--;
        return this.thickness;
    }
    
    @Override
    public void SetMaterial(Material m) {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".SetMaterial()");
        Logger.tabcount--;
        material = m;
    }

    @Override
    public Material GetMaterial() {
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".GetMaterial()");
        Logger.tabcount--;
        return material;
    }

    public boolean IsCloseToSun(){
        Logger.logMessage("Asteroid#" + Integer.toHexString(this.hashCode()) + ".IsCloseToSun()");
        Logger.tabcount--;
        return closeToSun;
    }

	public void setCloseToSun(boolean c) {
		closeToSun = c;
    }
}
