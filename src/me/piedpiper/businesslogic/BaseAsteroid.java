package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class BaseAsteroid extends Asteroid {
    private ArrayList<Material> chest;

    public BaseAsteroid(Point2D position, Ellipse2D ellipse, int thickness, Material material) {
        super(position, ellipse, thickness, material);
        System.out.println("BaseAsteroid.Ctor()");
        chest=new ArrayList<>();
    }

    @Override
    public boolean AddMaterial(Material m) {
        Logger.logMessage("BaseAsteroid.AddMaterial()");

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
        return chest;
    }
}
