package me.piedpiper.businesslogic;

public class Ice extends Material {

    private static int currentIndex = 0;

    // Konstruktor
    public Ice(){
        Logger.logMessage("Ice#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.id = ++currentIndex;

        Logger.tabcount--;
    }
    public Ice(boolean temp){
        Logger.logMessage("Ice#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        if (temp)
            this.id=-1;
        else
            this.id=++currentIndex;

        Logger.tabcount--;
    }



    // Felrobbanast megvalosito metodus, a felrobbanto hivhatja
    @Override
    public void BlowUp(OrbitingObject o) {
        Logger.logMessage("Ice#" + Integer.toHexString(this.hashCode()) + ".BlowUp()");
        
        o.RemoveMaterial();
        
        Logger.tabcount--;
    }

    // Polimorf viselkedes megvalositasa
    // megmondja, hogy a kapott anyag is jeg-e
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Ice#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        if (m instanceof Ice) return true;
        else return false;
    }

    public String GetName(){
        return "Ice" + id;
    }
}
