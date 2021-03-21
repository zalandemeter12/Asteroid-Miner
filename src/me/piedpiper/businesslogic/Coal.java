package me.piedpiper.businesslogic;

public class Coal extends Material {

    // Konstruktor
    public Coal() {
        Logger.logMessage("Coal#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
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
}
