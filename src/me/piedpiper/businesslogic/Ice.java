package me.piedpiper.businesslogic;

public class Ice extends Material {

    /**
     * index
     */
    private static int currentIndex = 0;

    /**
     * Konstruktor
     */
    public Ice(){
        Logger.logMessage("Ice#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.id = ++currentIndex;

        Logger.tabcount--;
    }

    /**
     * Masodik konstruktor, indexet megfelelore allitja
     */
    public Ice(boolean temp){
        Logger.logMessage("Ice#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        if (temp)
            this.id=-1;
        else
            this.id=++currentIndex;

        Logger.tabcount--;
    }


    /**
     *  Felrobbanast megvalosito metodus, a felrobbanto hivhatja
     */
    @Override
    public void BlowUp(OrbitingObject o, boolean mined) {
        Logger.logMessage("Ice#" + Integer.toHexString(this.hashCode()) + ".BlowUp()");
        
        o.RemoveMaterial();
        
        Logger.tabcount--;
    }

    /** Polimorf viselkedes megvalositasa
     * megmondja, hogy a kapott anyag is jeg-e
     */
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Ice#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        if (m instanceof Ice) return true;
        else return false;
    }

    /**
     * A fuggveny visszadja a nyersanyag nevet
     */
    public String GetName(){
        return "Ice" + id;
    }

    /**
     * A fuggveny reseteli a nyersanyag indexet
     */
    public static void ResetIndex(){
        currentIndex=0;
    }
}
