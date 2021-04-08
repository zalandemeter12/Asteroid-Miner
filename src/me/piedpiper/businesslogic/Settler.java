package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class Settler extends Worker {
    //Az aszteroida mező, amiben a telepes éppen benne van
    private final AsteroidField field;
    //Azt adja meg, hogy egy adott körben léphet e a telepes
    private boolean canStep;
    //A telepes nyersanyag táskája
    private ArrayList<Material> backpack;
    //A telepes teleport kapu tárolója
    private ArrayList<TeleportGate> gateInventory;

    //A telepes konstruktora
    public Settler(OrbitingObject location, AsteroidField field){
        super(location);
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Ctor()");  

        this.field = field;
        this.canStep = false;
        this.backpack = new ArrayList<>();
        this.gateInventory = new ArrayList<>();

        Logger.tabcount--;
    }

    //A telepes bányászik az aszteroidán amin van
    public void Mine() { 
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Mine()");
        
        //Csak akkor, ha át van fúrva a kéreg és van hely a táskájában
        if (location.GetThickness() == 0 && backpack.size() < 10) {
            backpack.add(location.RemoveMaterial());
        }

        Logger.tabcount--;
    }

    //A telepes lerakja a nála lévő nyersanyagot az aktuális helyzetére
    public void PlaceMaterial(Material m){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".PlaceMaterial()");
        
        //Csak akkor teszi meg, ha az aszteroida amin van el tudja azt fogadni
        if(backpack.contains(m) && location.AddMaterial(m)){
            backpack.remove(m);
        }

        Logger.tabcount--;
    }

    //A telepes lehelyez egy teleportkaput
    public void PlaceGate(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".PlaceGate()");
        
        //Csak akkor, ha van mit lehelyezni
        if (gateInventory.size() > 0) {
            Ellipse2D e=location.GetEllipse();
            gateInventory.get(0).SetPosition(e.GateLocation(location.GetPosition()));
            gateInventory.get(0).SetEllipse(location.GetEllipse()); 
            gateInventory.remove(0);
        }
        
        Logger.tabcount--;
    }

    //A telepes elkészít egy teleport kapu párt
    public void CraftGate(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".CraftGate()");
        
        //Csak akkor, ha van hely ahova elrakja a kapukat
        if (gateInventory.size() < 2) {
            //Egy nyersanyag receptet létrehoz, hogy ellenőrizze meg van-e minden anyag nála
            ArrayList<Material> materials = new ArrayList<>();
            materials.add(new Iron());
            materials.add(new Iron());
            materials.add(new Ice());
            materials.add(new Uran());
            BillOfMaterials bill = new BillOfMaterials(materials);
            int[] indices = new int[4];
            int idx = 0;
            //Végig megy és megnézi, hogy van-e elég nyersanyag nála
            for (int i = 0; i < backpack.size(); ++i)
                if (bill.IsNeeded(backpack.get(i)))
                    indices[idx++] = i;
            
            //Ha van nála elég anyag, kiveszi a táskából az anyagokat és elkészít velük két kaput
            if (bill.GetBill().size() == 0) {
                Material tmp0 = backpack.get(indices[0]);
                Material tmp1 = backpack.get(indices[1]);
                Material tmp2 = backpack.get(indices[2]);
                Material tmp3 = backpack.get(indices[3]);
                backpack.remove(tmp0);
                backpack.remove(tmp1);
                backpack.remove(tmp2);
                backpack.remove(tmp3);
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

    //A telepes megépít egy robotot
    public void  BuildRobot(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".BuildRobot()");
        
        //Létrehoz egy nyersanyag receptet
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(new Coal());
        materials.add(new Iron());
        materials.add(new Uran());
        BillOfMaterials bill = new BillOfMaterials(materials);
        int[] indices = new int[3];
        int idx = 0;
        //Megnézi, hogy van-e nála elég anyag
        for (int i = 0; i < backpack.size(); ++i)
            if (bill.IsNeeded(backpack.get(i)))
                indices[idx++] = i;

        //Ha van elég, akkor kiveszi a táskájából és épít vele egy robotot az aktuális helyzetére
        if (bill.GetBill().size() == 0) {
            Material tmp0 = backpack.get(indices[0]);
            Material tmp1 = backpack.get(indices[1]);
            Material tmp2 = backpack.get(indices[2]);
            backpack.remove(tmp0);
            backpack.remove(tmp1);
            backpack.remove(tmp2);
            Robot r = new Robot(location, field);
            field.AddRobot(r);
        }
        
        Logger.tabcount--;
    }

    //A telepes felrobban, ami egyenértékű azzal, hogy meghal
    @Override
    public void Explode(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Explode()");
        
        Die();
        
        Logger.tabcount--;
    }

    //A telepes kihagyja a lépést az adott körben
    public void SkipAction(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".SkipAction()");
        
        this.canStep = false;
        
        Logger.tabcount--;
    }

    //A telepes meghal
    @Override
    public void Die(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Die()");
        
        location.RemoveWorker(this);
        field.RemoveSettler(this);
        
        Logger.tabcount--;
    }

    //Visszaadja a telepes táskáját
    public ArrayList<Material> GetBackpack(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".GetBackpack()");
        Logger.tabcount--;
        return backpack;
    }

    //Hozzáad egy nyersanyagot a telepes táskájához
    public void AddMaterialToBackpack(Material m) {
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".AddMaterialToBackpack()");
        
        //Csak akkor, ha van benne hely
        if (backpack.size() < 10) {
            backpack.add(m);
        }
        
        Logger.tabcount--;
    }

    //Visszaadja a telepes teleport kapu tárolóját
    public ArrayList<TeleportGate> GetGateInventory() {
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".GetGateInventory()");
        Logger.tabcount--;
        return gateInventory;
    }

    //Hozzáad egy teleport kaput a telepes tárolójához, csak a tesztekhez használatos
    public void AddGate(TeleportGate g){
        gateInventory.add(g);
    }
}
