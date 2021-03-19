package me.piedpiper.businesslogic;

public abstract class RadioactiveMaterial extends Material {
    @Override
    public void BlowUp(OrbitingObject o) {
        Logger.logMessage("RadioactiveMaterial#" + Integer.toHexString(this.hashCode()) + ".BlowUp()");
        
        for (Worker w : o.GetWorkers()) {
            w.Explode();
        }
        o.GetEllipse().RemoveObject(o);
        o.RemoveMaterial();
        
        Logger.tabcount--;
    }
}
