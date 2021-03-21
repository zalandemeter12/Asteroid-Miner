package me.piedpiper.businesslogic;

public class Ice extends Material {
    public Ice(){
        Logger.logMessage("Ice#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        Logger.tabcount--;
    }
    @Override
    public void BlowUp(OrbitingObject o) {
        Logger.logMessage("Ice#" + Integer.toHexString(this.hashCode()) + ".BlowUp()");
        
        o.RemoveMaterial();
        
        Logger.tabcount--;
    }

    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Ice#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        if (m instanceof Ice) return true;
        else return false;
    }
}
