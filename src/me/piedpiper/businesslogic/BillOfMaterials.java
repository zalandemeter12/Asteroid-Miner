package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class BillOfMaterials {
    private final ArrayList<Material> bill;

    public BillOfMaterials(ArrayList<Material> bill){
        this.bill = bill;
        System.out.println("BillOfMaterials.Ctor()");
    }

    public boolean IsNeeded(Material m){
        System.out.println("BillOfMaterials.IsNeeded()");
        return false;
    }
}
