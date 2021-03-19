package me.piedpiper.businesslogic;

public class Ice extends Material {
    @Override
    public void BlowUp(OrbitingObject o) {
        System.out.println("Ice.BlowUp()");
        o.RemoveMaterial();
    }

    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Ice#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        return false;
    }
}
