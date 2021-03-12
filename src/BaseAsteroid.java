import java.util.ArrayList;

public class BaseAsteroid extends Asteroid {
    private ArrayList<Material> chest;

    public BaseAsteroid(Point2D position, Ellipse2D ellipse, int thickness, Material material) {
        super(position, ellipse, thickness, material);
        System.out.println("BaseAsteroid.Ctor()");
    }

    @Override
    public boolean AddMaterial(Material m) {
        System.out.println("BaseAsteroid.AddMaterial()");
        ArrayList<Material> materials = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            materials.add(new Uranium());
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
            return true;
        } else {
            return false;
        }
    }
}
