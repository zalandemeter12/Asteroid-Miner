package me.piedpiper.businesslogic;

public class Coal extends Material {
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.tabcount++;
        Logger.logMessage("Coal.IsCompatibleWith()");
        Logger.tabcount--;
        return false;
    }
}
