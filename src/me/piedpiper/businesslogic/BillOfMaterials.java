package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class BillOfMaterials {
    private final ArrayList<Material> bill;

    public BillOfMaterials(ArrayList<Material> bill){
        Logger.logMessage("BillOfMaterials#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.bill = bill;

        Logger.tabcount--;
    }

    public boolean IsNeeded(Material m){
        Logger.logMessage("BillOfMaterials#" + Integer.toHexString(this.hashCode()) + ".IsNeeded()");
        
        for (Material k: bill) {
            if (k.IsCompatibleWith(m)) {
                bill.remove(k);

                Logger.tabcount--;
                return true;
            }
        }

        Logger.tabcount--;
        return false;
    }

    public ArrayList<Material> GetBill() {
        Logger.logMessage("BillOfMaterials#" + Integer.toHexString(this.hashCode()) + ".GetBill()");
        Logger.tabcount--;
        return bill;
    } 
}
