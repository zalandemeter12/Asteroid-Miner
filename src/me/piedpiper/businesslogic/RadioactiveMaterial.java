package me.piedpiper.businesslogic;

public abstract class RadioactiveMaterial extends Material {
    @Override
    public void BlowUp(Asteroid a) {
        System.out.println("RadioactiveMaterial.BlowUp()");
    }
}
