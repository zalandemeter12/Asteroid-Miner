package me.piedpiper.businesslogic;

public class Uran extends Material {
    public Uran(){
        Logger.logMessage("Uran#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        Logger.tabcount--;
    }
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Uran#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        if (m instanceof Uran) return true;
        else return false;
    }
}
