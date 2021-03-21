package me.piedpiper.businesslogic;

public abstract class RadioactiveMaterial extends Material {
    //A radioaktív anyag felrobban
    @Override
    public void BlowUp(OrbitingObject o) {
        Logger.logMessage("RadioactiveMaterial#" + Integer.toHexString(this.hashCode()) + ".BlowUp()");
        
        //Megöli a telepeseket azon az aszteroidán amin helyezkedik
        for (Worker w : o.GetWorkers()) {
            w.Explode();
        }
        o.GetEllipse().RemoveObject(o);
        o.RemoveMaterial();
        
        Logger.tabcount--;
    }
}
