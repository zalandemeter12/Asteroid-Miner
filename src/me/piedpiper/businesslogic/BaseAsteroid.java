package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class BaseAsteroid extends Asteroid {
    // Osszegyujtott anyagokat tartalmazo lista
    private ArrayList<Material> chest;
    // Jatekmenentet kezelo objektum referenciaja
    private Game game;

    // Konstruktor
    public BaseAsteroid(Point2D position, Ellipse2D ellipse, int thickness, Material material, Game game) {
        super(position, ellipse, thickness, material);
        Logger.logMessage("BaseAsteroid#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        chest=new ArrayList<>();
        this.game = game;

        Logger.tabcount--;
    }

    // Anyag belerakasa a chestbe
    @Override
    public boolean AddMaterial(Material m) {
        Logger.logMessage("BaseAsteroid#" + Integer.toHexString(this.hashCode()) + ".AddMaterial()");

        ArrayList<Material> materials = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            materials.add(new Uran());
            materials.add(new Coal());
            materials.add(new Ice());
            materials.add(new Iron());
        }
        BillOfMaterials bill = new BillOfMaterials(materials);
        for (int i = 0; i < chest.size(); ++i) {
            bill.IsNeeded(chest.get(i));
        }
        if (bill.IsNeeded(m)) {
            chest.add(m);
            if(bill.GetBill().size() == 0)
                game.EndGame();


            Logger.tabcount--;
            return true;
        } else {
            Logger.tabcount--;
            return false;
        }
    }

    // A mar osszegyujtott anyagok listajaval ter vissza
    @Override
    public ArrayList<Material> GetChest() {
        Logger.logMessage("BaseAsteroid#" + Integer.toHexString(this.hashCode()) + ".GetChest()");
        Logger.tabcount--;
        return chest;
    }



}
