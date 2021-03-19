package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class BaseAsteroid extends Asteroid {
    private ArrayList<Material> chest;

    public BaseAsteroid(Point2D position, Ellipse2D ellipse, int thickness, Material material) {
        super(position, ellipse, thickness, material);
        Logger.logMessage("BaseAsteroid#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        chest=new ArrayList<>();

        Logger.tabcount--;
    }

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
            if (bill.IsNeeded(chest.get(i))) {
                //chest.remove();
            }
        }
        if (bill.IsNeeded(m)) {
            chest.add(m);

            Logger.tabcount--;
            return true;
        } else {
            Logger.tabcount--;
            return false;
        }
    }

    @Override
    public ArrayList<Material> GetChest() {
        Logger.logMessage("BaseAsteroid#" + Integer.toHexString(this.hashCode()) + ".GetChest()");
        Logger.tabcount--;
        return chest;
    }

}
