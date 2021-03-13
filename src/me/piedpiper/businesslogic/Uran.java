package me.piedpiper.businesslogic;

public class Uran extends Material {
    @Override
    public boolean IsCompatibleWith(Material m){
        System.out.println("Uranium.IsCompatibleWith()");
        return false;
    }
}
