package me.piedpiper.businesslogic;

public class Ice extends Material {
    @Override
    public void BlowUp(OrbitingObject o) {
        System.out.println("Ice.BlowUp()");
        o.RemoveMaterial();
    }

    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.tabcount++;
        Logger.logMessage("Ice.IsCompatibleWith()");
        Logger.tabcount--;
        return false;
    }
}
