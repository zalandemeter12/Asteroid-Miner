package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class BillOfMaterials {
    private  ArrayList<Material> bill;
    private  ArrayList<Material> cache;

    public BillOfMaterials(ArrayList<Material> bill){
        Logger.logMessage("BillOfMaterials#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.bill = bill;
        this.cache = (ArrayList<Material>)bill.clone();

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

    public void Init(){
        bill = (ArrayList<Material>)cache.clone();
    }

}
