package me.piedpiper.businesslogic;

public class Coal extends Material {
    public Coal() {
        Logger.logMessage("Coal#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        Logger.tabcount--;
    }
    @Override
    public boolean IsCompatibleWith(Material m) {
        Logger.logMessage("Coal#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        if (m instanceof Coal) return true;
        else return false;
    }
}
