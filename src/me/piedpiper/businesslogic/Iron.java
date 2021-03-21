package me.piedpiper.businesslogic;

public class Iron extends Material {
    //Az osztaly konstruktora
    public Iron(){
        Logger.logMessage("Iron#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
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
}
