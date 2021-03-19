package me.piedpiper.businesslogic;

public class Uran extends Material {
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Uran#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        return false;
    }
}
