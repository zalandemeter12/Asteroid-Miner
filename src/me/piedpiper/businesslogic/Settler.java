package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class Settler extends Worker {
    private final AsteroidField field;
    private boolean canStep;
    private ArrayList<Material> backpack;
    private ArrayList<TeleportGate> gateInventory;

    public Settler(OrbitingObject location, AsteroidField field){
        super(location);
        this.field = field;
        this.canStep = false;
        this.backpack = new ArrayList<>();
        this.gateInventory = new ArrayList<>();
        System.out.println("Settler.Ctor()");
    }

    public void Mine() { 
        if (location.GetThickness() == 0 && backpack.size() < 10) {
            backpack.add(location.RemoveMaterial());
        }
        System.out.println("Settler.Mine()");
    }

    public void  PlaceMaterial(Material m){
        Logger.tabcount++;
        Logger.logMessage("Settler.PlaceMaterial()");
        
        location.AddMaterial(m);

        Logger.tabcount--;
    }

    public void  PlaceGate(){
        if (gateInventory.size() > 0) {
            gateInventory.get(0).SetPosition(location.GetPosition());
            gateInventory.get(0).SetEllipse(location.GetEllipse());
        }
        System.out.println("Settler.PlaceGate()");
    }

    public void  CraftGate(){
        if (gateInventory.size() == 0) {
            ArrayList<Material> materials = new ArrayList<>();
            materials.add(new Iron());
            materials.add(new Iron());
            materials.add(new Ice());
            materials.add(new Uran());
            BillOfMaterials bill = new BillOfMaterials(materials);
            for (Material m: backpack) {
                bill.IsNeeded(m);
            }
            if (bill.GetBill().size() == 0) {
                bill = new BillOfMaterials(materials);
                for (Material m: backpack) {
                    if (bill.IsNeeded(m))
                        backpack.remove(m);
                }
                
                TeleportGate t1 = new TeleportGate(null, null);
                TeleportGate t2 = new TeleportGate(null, null);
                t1.SetGatePair(t2);
                t2.SetGatePair(t1);
                gateInventory.add(t1);
                gateInventory.add(t2);
            }
        }
        System.out.println("Settler.CraftGate()");
    }

    public void  BuildRobot(){
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(new Coal());
        materials.add(new Iron());
        materials.add(new Uran());
        BillOfMaterials bill = new BillOfMaterials(materials);
        for (Material m: backpack) {
            bill.IsNeeded(m);
        }
        if (bill.GetBill().size() == 0) {
            bill = new BillOfMaterials(materials);
            for (Material m: backpack) {
                if (bill.IsNeeded(m))
                    backpack.remove(m);
            }
            Robot r = new Robot(location, field);
            field.AddRobot(r);
        }
        System.out.println("Settler.BuildRobot()");
    }

    @Override
    public void Explode(){
        Die();
        System.out.println("Settler.Explode()");
    }

    public void SkipAction(){
        System.out.println("Settler.SkipAction()");
    }

    @Override
    public void Die(){
        location.RemoveWorker(this);
        field.RemoveSettler(this);
        System.out.println("Settler.Die()");
    }

    public ArrayList<Material> GetBackpack(){
        return backpack;
    }

    public void AddMaterialToBackpack (Material m) {
        if (backpack.size() < 10) {
            backpack.add(m);
        }
    }

    public ArrayList<TeleportGate> GetGateInventory() {
        return gateInventory;
    }
}
