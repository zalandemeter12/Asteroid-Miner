package me.piedpiper.businesslogic;

public abstract class Material {
    public void BlowUp(OrbitingObject o) {
        System.out.println("Material.BlowUp()");
    };
    public abstract boolean IsCompatibleWith(Material m);
}
