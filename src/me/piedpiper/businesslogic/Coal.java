package me.piedpiper.businesslogic;

public class Coal extends Material {
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Coal#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        return false;
    }
}
