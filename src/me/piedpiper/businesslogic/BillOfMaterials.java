package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class BillOfMaterials {
    private final ArrayList<Material> bill;

    public BillOfMaterials(ArrayList<Material> bill){
        this.bill = bill;
        System.out.println("BillOfMaterials.Ctor()");
    }

    public boolean IsNeeded(Material m){
        for (Material k: bill) {
            if (k.IsCompatibleWith(m)) {
                bill.remove(k);
                return true;
            }
        }
        System.out.println("BillOfMaterials.IsNeeded()");
        return false;
    }

    public ArrayList<Material> GetBill() {
        return bill;
    } 
}
