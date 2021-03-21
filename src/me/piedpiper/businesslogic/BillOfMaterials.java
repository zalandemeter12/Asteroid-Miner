package me.piedpiper.businesslogic;

import java.util.ArrayList;

//Egy nyeranyagokból álló receptet lehet létrehozni vele,
// segítségével ellenőrízhető, hogy egy entitás rendelkezik-e
//bizonyos nyersanyagokkal
public class BillOfMaterials {
    //A recept nyersanyag listája
    private  ArrayList<Material> bill;

    //Konstruktor
    public BillOfMaterials(ArrayList<Material> bill){
        Logger.logMessage("BillOfMaterials#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.bill = bill;

        Logger.tabcount--;
    }

    //Ellenőrzi, hogy az adott nyersanyag benne van-e a listában,
    //ha igen, akkor kiveszi a listából és igazzal tér vissza
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

    //recept lista getter
    public ArrayList<Material> GetBill() {
        Logger.logMessage("BillOfMaterials#" + Integer.toHexString(this.hashCode()) + ".GetBill()");
        Logger.tabcount--;
        return bill;
    }

}
