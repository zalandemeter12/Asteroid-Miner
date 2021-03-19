package me.piedpiper.businesslogic;

public abstract class Material {
    public void BlowUp(OrbitingObject o) {
        Logger.logMessage("Material#" + Integer.toHexString(this.hashCode()) + ".BlowUp()");
        Logger.tabcount--;
    }
    public abstract boolean IsCompatibleWith(Material m);
}
