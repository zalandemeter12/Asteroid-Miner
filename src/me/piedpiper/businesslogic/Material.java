package me.piedpiper.businesslogic;

public abstract class Material {
    /**
     * id
     */
    protected int id;

    /**
     * Felrobbanaskor a robbanto hivja meg
     */
    public void BlowUp(OrbitingObject o, boolean mined) {
        Logger.logMessage("Material#" + Integer.toHexString(this.hashCode()) + ".BlowUp()");
        Logger.tabcount--;
    }

    /**
     *  Polimorf kompatibilitas ellenorzes
     */
    public abstract boolean IsCompatibleWith(Material m);

    /**
     * Nyersanyag nevet adja vissza az fg
     */
    public abstract String GetName();
}
