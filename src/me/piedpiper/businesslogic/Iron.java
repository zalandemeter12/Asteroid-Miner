package me.piedpiper.businesslogic;

public class Iron extends Material {
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Iron.IsCompatibleWith()");
        Logger.tabcount--;
        return false;
    }
}
