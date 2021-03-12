package me.piedpiper.businesslogic;

public abstract class Material {
    public void BlowUp(Asteroid a) {
        System.out.println("Material.BlowUp()");
    };
    public abstract boolean IsCompatibleWith(Material m);
}
