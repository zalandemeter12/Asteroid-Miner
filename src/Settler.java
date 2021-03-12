import java.util.ArrayList;

public class Settler extends Worker implements ISteppable{

    private final AsteroidField field;

    private boolean canStep;

    private ArrayList<Material> backpack;

    private ArrayList<TeleportGate> gateInventory;

    public Settler(AsteroidField field){
        this.field = field;
        canStep = false;
        backpack = new ArrayList<>();
        gateInventory = new ArrayList<>();
    }

    public void  PlaceMaterial(Material m){
        System.out.println("Settler.PlaceMaterial()");
    }

    public void  PlaceGate(TeleportGate t){
        System.out.println("Settler.PlaceGate()");
    }

    public void  CraftGate(){
        System.out.println("Settler.CraftGate()");
    }

    public void  BuildRobot(){
        System.out.println("Settler.BuildRobot()");
    }

    @Override
    public void Explode(){
        System.out.println("Settler.Explode()");
    }

    public void SkipAction(){
        System.out.println("Settler.SkipAction()");
    }

    @Override
    public void Die(){
        System.out.println("Settler.Die()");
    }


}
