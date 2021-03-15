package me.piedpiper.businesslogic;

public class Uran extends Material {
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.tabcount++;
        Logger.logMessage("Uran.IsCompatibleWith()");
        Logger.tabcount--;
        return false;
    }
}
