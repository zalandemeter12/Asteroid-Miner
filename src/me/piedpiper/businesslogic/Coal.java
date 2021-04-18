package me.piedpiper.businesslogic;

public class Coal extends Material {

    private static int currentIndex = 0;

    // Konstruktor
    public Coal() {
        Logger.logMessage("Coal#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.id=++currentIndex;

        Logger.tabcount--;
    }

    public Coal(boolean temp) {
        Logger.logMessage("Coal#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        if (temp)
            this.id=-1;
        else
            this.id=++currentIndex;

        Logger.tabcount--;
    }

    // Polimorf viselkedes megvalositasa
    // megmondja, hogy a kapott anyag is szen-e
    @Override
    public boolean IsCompatibleWith(Material m) {
        Logger.logMessage("Coal#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        if (m instanceof Coal) return true;
        else return false;
    }

    public String GetName(){
        return "Coal" + id;
    }

    public static void ResetIndex(){
        currentIndex=0;
    }
}
