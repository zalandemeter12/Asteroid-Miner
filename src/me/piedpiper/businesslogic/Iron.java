package me.piedpiper.businesslogic;

public class Iron extends Material {
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Iron#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        return false;
    }
}
