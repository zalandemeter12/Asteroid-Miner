package me.piedpiper.businesslogic;

public class Iron extends Material {

    /**
     * index
     */
    private static int currentIndex = 0;

    /**
     * Konstruktor
     */
    public Iron(){
        Logger.logMessage("Iron#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.id = ++currentIndex;

        Logger.tabcount--;
    }

    /**
     * Masodik konstruktor, indexet megfelelore allitja
     */
    public Iron(boolean temp){
        Logger.logMessage("Iron#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        if (temp)
            this.id=-1;
        else
            this.id=++currentIndex;

        Logger.tabcount--;
    }


    /**
     *  Polimorf viselkedes megvalositasa
     * megmondja, hogy a kapott anyag is vas-e
     */
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Iron#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        if (m instanceof Iron) return true;
        else return false;
    }

    /**
     * A fuggveny visszadja a nyersanyag nevet
     */
    public String GetName(){
        return "Iron" + id;
    }

    /**
     * A fuggveny reseteli a nyersanyag indexet
     */
    public static void ResetIndex(){
        currentIndex=0;
    }
}
