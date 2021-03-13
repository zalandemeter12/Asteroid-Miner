package me.piedpiper.businesslogic;

public abstract class RadioactiveMaterial extends Material {
    @Override
    public void BlowUp(OrbitingObject o) {
        for (Worker w : o.GetWorkers()) {
            w.Explode();
        }
        o.GetEllipse().RemoveObject(o);
        o.RemoveMaterial();
        System.out.println("RadioactiveMaterial.BlowUp()");
    }
}
