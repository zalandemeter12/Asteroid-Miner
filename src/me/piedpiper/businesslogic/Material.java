package me.piedpiper.businesslogic;

public abstract class Material {
    protected int id;

    // Felrobbanaskor a robbanto hivja meg
    public void BlowUp(OrbitingObject o) {
        Logger.logMessage("Material#" + Integer.toHexString(this.hashCode()) + ".BlowUp()");
        Logger.tabcount--;
    }

    // Polimorf kompatibilitas ellenorzes
    public abstract boolean IsCompatibleWith(Material m);
}
