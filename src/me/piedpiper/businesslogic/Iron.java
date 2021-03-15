package me.piedpiper.businesslogic;

public class Iron extends Material {
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.tabcount++;
        Logger.logMessage("Iron.IsCompatibleWith()");
        Logger.tabcount--;
        return false;
    }
}
