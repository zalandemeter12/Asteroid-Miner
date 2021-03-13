package me.piedpiper.businesslogic;

public class Ice extends Material {
    @Override
    public void BlowUp(Asteroid a) {
        System.out.println("Ice.BlowUp()");
        a.RemoveMaterial();
    }

    @Override
    public boolean IsCompatibleWith(Material m){
        System.out.println("Ice.IsCompatibleWith()");
        return false;
    }
}
