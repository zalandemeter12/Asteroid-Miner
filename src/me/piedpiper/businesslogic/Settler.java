package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class Settler extends Worker {
    private final AsteroidField field;
    private boolean canStep;
    private ArrayList<Material> backpack;
    private ArrayList<TeleportGate> gateInventory;

    public Settler(OrbitingObject location, AsteroidField field){
        super(location);
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        

        this.field = field;
        this.canStep = false;
        this.backpack = new ArrayList<>();
        this.gateInventory = new ArrayList<>();

        Logger.tabcount--;
    }

    public void Mine() { 
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Mine()");
        
        if (location.GetThickness() == 0 && backpack.size() < 10) {
            backpack.add(location.RemoveMaterial());
        }

        Logger.tabcount--;
    }

    public void PlaceMaterial(Material m){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".PlaceMaterial()");
        
        if(location.AddMaterial(m)){
            backpack.remove(m);
        }

        Logger.tabcount--;
    }

    public void PlaceGate(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".PlaceGate()");
        Logger.logGetter=true;
        Logger.logSetter=true;
        if (gateInventory.size() > 0) {
            Ellipse2D e=location.GetEllipse();
            gateInventory.get(0).SetPosition(e.GateLocation(location.GetPosition()));
            gateInventory.get(0).SetEllipse(location.GetEllipse());

        }
        
        Logger.tabcount--;
        Logger.logGetter=false;
        Logger.logSetter=false;
    }

    public void CraftGate(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".CraftGate()");
        
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
        
        Logger.tabcount--;
    }

    public void  BuildRobot(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".BuildRobot()");
        
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
        
        Logger.tabcount--;
    }

    @Override
    public void Explode(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Explode()");
        
        Die();
        
        Logger.tabcount--;
    }

    public void SkipAction(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".SkipAction()");
        Logger.tabcount--;
    }

    @Override
    public void Die(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Die()");
        
        location.RemoveWorker(this);
        field.RemoveSettler(this);
        
        Logger.tabcount--;
    }

    public ArrayList<Material> GetBackpack(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".GetBackpack()");
        Logger.tabcount--;
        return backpack;
    }

    public void AddMaterialToBackpack (Material m) {
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".AddMaterialToBackpack()");
        
        if (backpack.size() < 10) {
            backpack.add(m);
        }
        
        Logger.tabcount--;
    }

    public ArrayList<TeleportGate> GetGateInventory() {
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".GetGateInventory()");
        Logger.tabcount--;
        return gateInventory;
    }

    public void AddGate(TeleportGate g){ //for test cases
        gateInventory.add(g);
    }
}
