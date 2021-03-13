package me.piedpiper.businesslogic;

public class Uran extends Material {
    @Override
    public void BlowUp(Asteroid a) {
        a.RemoveMaterial();
        System.out.println("Uranium.BlowUp()");
    }

    @Override
    public boolean IsCompatibleWith(Material m){
        System.out.println("Uranium.IsCompatibleWith()");
        return false;
    }
}
