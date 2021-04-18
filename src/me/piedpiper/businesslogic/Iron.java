package me.piedpiper.businesslogic;

public class Iron extends Material {

    private static int currentIndex = 0;

    // Konstruktor
    public Iron(){
        Logger.logMessage("Iron#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.id = ++currentIndex;

        Logger.tabcount--;
    }
    public Iron(boolean temp){
        Logger.logMessage("Iron#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        if (temp)
            this.id=-1;
        else
            this.id=++currentIndex;

        Logger.tabcount--;
    }



    // Polimorf viselkedes megvalositasa
    // megmondja, hogy a kapott anyag is vas-e
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Iron#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        if (m instanceof Iron) return true;
        else return false;
    }

    public String GetName(){
        return "Iron" + id;
    }

    public static void ResetIndex(){
        currentIndex=0;
    }
}
