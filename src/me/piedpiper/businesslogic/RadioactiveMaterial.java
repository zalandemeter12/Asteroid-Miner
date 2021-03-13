package me.piedpiper.businesslogic;

public abstract class RadioactiveMaterial extends Material {
    @Override
    public void BlowUp(OrbitingObject o) {
        o.RemoveMaterial();
        System.out.println("RadioactiveMaterial.BlowUp()");
    }
}
