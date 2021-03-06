package me.piedpiper.businesslogic;

import me.piedpiper.gui.SettlerPanel;
import me.piedpiper.gui.View;

import java.util.ArrayList;

public class Settler extends Worker {
    /**
     * Az aszteroida mező, amiben a telepes éppen benne van
     */
    private final AsteroidField field;
    /**
     * Azt adja meg, hogy egy adott körben léphet e a telepes
     */
    private boolean canStep;
    /**
     * A telepes nyersanyag táskája
     */
    private final ArrayList<Material> backpack;
    /**
     * A telepes teleport kapu tárolója
     */
    private final ArrayList<TeleportGate> gateInventory;
    /**
     * index
     */
    private static int currentIndex = 0;
    /**
     * A kirajzolasert felelos peldany
     */
    private View view;
    /**
     * A kirajzolhato objektum, ami telepest rajzol ki
     */
    private SettlerPanel panel;


    /**
     * A telepes konstruktora
     */
    public Settler(OrbitingObject location, AsteroidField field){
        super(location);
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * tagvaltozok beallitasa
         */
        this.field = field;
        this.canStep = true;
        this.backpack = new ArrayList<>();
        this.gateInventory = new ArrayList<>();
        this.id = ++currentIndex;

        Logger.tabcount--;
    }

    /**
     * Masodik konstruktor, melyben az elozohoz kepest annzi a valtozas, hogy letrehoz egy SettlerPanel-t
     * amit atad a view-nak
     */
    public Settler(OrbitingObject location, AsteroidField field,View view){
        super(location);
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * tagvaltozok beallitasa
         */
        this.field = field;
        this.canStep = true;
        this.backpack = new ArrayList<>();
        this.gateInventory = new ArrayList<>();
        this.id = ++currentIndex;
        this.view = view;
        panel=new SettlerPanel(this);
        view.AddGraphicObject(panel);
        Logger.tabcount--;
    }


    /**
     * A telepes bányászik az aszteroidán amin van
     *
     */
    public void Mine() { 
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Mine()");
        if(location.IsCloseToSun() && location.GetThickness()==0 && location.GetMaterial()!=null)
            location.GetMaterial().BlowUp(location, true);
        /**
         * Csak akkor, ha át van fúrva a kéreg és van hely a táskájában
         */
        if (location.GetThickness() == 0 && backpack.size() < 10) {
            Material mined = location.RemoveMaterial();
            if(mined != null) {
                backpack.add(mined);
                canStep=false;
                field.SettlerStepped();
            }
        }

        Logger.tabcount--;
    }

    /**
     * Áthelyezi a Workert a paraméterban átvett OrbitingObjectre
     */
    @Override
    public void MoveTo(OrbitingObject o){
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".MoveTo()");

        /**
         * Az OrbitingObjectek, amikre a Worker tud mozogni.
         */
        ArrayList<OrbitingObject> neighbors = location.GetNeighbors();
        /**
         * Ha a paraméterként átvett OrbitingObject benne van a neighbours listában.
         */
        if (neighbors.contains(o)) {
            /**
             * Az aktuális OrbitingObjectről eltávolítja a Workert
             */
            location.RemoveWorker(this);
            /**
             * Megváltoztatja a locationt az új helyzetére.
             */
            location=o;
            /**
             * Áthelyezi a cél OrbitingObjectre.
             */
            o.AddWorker(this);
            canStep=false;
            field.SettlerStepped();
        }

        Logger.tabcount--;
    }

    /**
     * Lejjebb fúr egy réteget az OrbitingObject kérgén, ha lehetséges.
     */
    @Override
    public void DrillHole(){
        Logger.logMessage("Worker#" + Integer.toHexString(this.hashCode()) + ".DrillHole()");

        if (location.DrilledOn()) {
            canStep=false;
            field.SettlerStepped();
        }

        Logger.tabcount--;
    }

    /**
     * A telepes lerakja a nála lévő nyersanyagot az aktuális helyzetére
     */
    public void PlaceMaterial(Material m){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".PlaceMaterial()");

        /**
         * Csak akkor teszi meg, ha az aszteroida amin van el tudja azt fogadni
         */
        if(backpack.contains(m) && location.AddMaterial(m)){
            backpack.remove(m);
            canStep=false;
            field.SettlerStepped();
        }

        Logger.tabcount--;
    }

    /**
     * A telepes lehelyez egy teleportkaput
     */
    public void PlaceGate(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".PlaceGate()");

        /**
         * Csak akkor, ha van mit lehelyezni
         */
        if (gateInventory.size() > 0) {

            TeleportGate tg = gateInventory.get(0);

            gateInventory.remove(0);


            tg.SetEllipse(location.GetEllipse());

            tg.PlacePanel();
            tg.AddNeighbor(location);
            location.AddNeighbor(tg);



            canStep=false;
            field.SettlerStepped();
            Ellipse2D e = location.GetEllipse();
            tg.SetPosition(new Point2D(0,0));
            e.AddObject(tg);
            tg.SetT(location.GetT()+0.25);
            tg.SetPosition(e.GateLocation(location));
        }

        
        Logger.tabcount--;
    }

    /**
     * A telepes elkészít egy teleport kapu párt
     */
    public void CraftGate(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".CraftGate()");

        /**
         * Csak akkor, ha van hely ahova elrakja a kapukat
         */
        if (gateInventory.size() < 2) {
            /**
             * Egy nyersanyag receptet létrehoz, hogy ellenőrizze meg van-e minden anyag nála
             */
            ArrayList<Material> materials = new ArrayList<>();
            materials.add(new Iron(true));
            materials.add(new Iron(true));
            materials.add(new Ice(true));
            materials.add(new Uran(true));
            BillOfMaterials bill = new BillOfMaterials(materials);
            int[] indices = new int[4];
            int idx = 0;
            /**
             * Végig megy és megnézi, hogy van-e elég nyersanyag nála
             */
            for (int i = 0; i < backpack.size(); ++i)
                if (bill.IsNeeded(backpack.get(i)))
                    indices[idx++] = i;

            /**
             * Ha van nála elég anyag, kiveszi a táskából az anyagokat és elkészít velük két kaput
             */
            if (bill.GetBill().size() == 0) {
                Material tmp0 = backpack.get(indices[0]);
                Material tmp1 = backpack.get(indices[1]);
                Material tmp2 = backpack.get(indices[2]);
                Material tmp3 = backpack.get(indices[3]);
                backpack.remove(tmp0);
                backpack.remove(tmp1);
                backpack.remove(tmp2);
                backpack.remove(tmp3);
                TeleportGate t1 = new TeleportGate(null, null, view);
                TeleportGate t2 = new TeleportGate(null, null, view);
                t1.SetGatePair(t2);
                t2.SetGatePair(t1);
                gateInventory.add(t1);
                gateInventory.add(t2);
                canStep=false;
                field.SettlerStepped();
            }
        }
        
        Logger.tabcount--;
    }

    /**
     * A telepes megépít egy robotot
     */
    public void  BuildRobot(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".BuildRobot()");

        /**
         * Létrehoz egy nyersanyag receptet
         */
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(new Coal(true));
        materials.add(new Iron(true));
        materials.add(new Uran(true));
        BillOfMaterials bill = new BillOfMaterials(materials);
        int[] indices = new int[3];
        int idx = 0;
        /**
         * Megnézi, hogy van-e nála elég anyag
         */
        for (int i = 0; i < backpack.size(); ++i)
            if (bill.IsNeeded(backpack.get(i)))
                indices[idx++] = i;

        /**
         * Ha van elég, akkor kiveszi a táskájából és épít vele egy robotot az aktuális helyzetére
         */
        if (bill.GetBill().size() == 0) {
            Material tmp0 = backpack.get(indices[0]);
            Material tmp1 = backpack.get(indices[1]);
            Material tmp2 = backpack.get(indices[2]);
            backpack.remove(tmp0);
            backpack.remove(tmp1);
            backpack.remove(tmp2);
            Robot r = new Robot(location, field, view);
            field.AddSteppable(r);
            canStep=false;
            field.SettlerStepped();
        }
        
        Logger.tabcount--;
    }

    /**
     * A telepes felrobban, ami egyenértékű azzal, hogy meghal
     */
    @Override
    public void Explode(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Explode()");
        
        Die();
        
        Logger.tabcount--;
    }

    /**
     * A telepes kihagyja a lépést az adott körben
     */
    public void SkipAction(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".SkipAction()");

        this.canStep = false;
        field.SettlerStepped();

        Logger.tabcount--;
    }

    /**
     * A telepes meghal
     */
    @Override
    public void Die(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".Die()");

        dead=true;
        view.RemoveGraphicObject(panel);
        Logger.tabcount--;
    }

    /**
     * Visszaadja a telepes táskáját
     */
    public ArrayList<Material> GetBackpack(){
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".GetBackpack()");
        Logger.tabcount--;
        return backpack;
    }

    /**
     * Hozzáad egy nyersanyagot a telepes táskájához
     */
    public void AddMaterialToBackpack(Material m) {
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".AddMaterialToBackpack()");

        /**
         * Csak akkor, ha van benne hely
         */
        if (backpack.size() < 10) {
            backpack.add(m);
        }
        
        Logger.tabcount--;
    }

    /**
     * Visszaadja a telepes teleport kapu tárolóját
     */
    public ArrayList<TeleportGate> GetGateInventory() {
        Logger.logMessage("Settler#" + Integer.toHexString(this.hashCode()) + ".GetGateInventory()");
        Logger.tabcount--;
        return gateInventory;
    }

    /**
     * Hozzáad egy teleport kaput a telepes tárolójához, csak a tesztekhez használatos
     */
    public void AddGate(TeleportGate g){
            gateInventory.add(g);
    }

    /**
     * A fuggveny visszadja a settler nevet
     */
    public String GetName(){
        return "Settler"+id;
    }

    /**
     * A fuggveny visszadja a id-t
     */
    public int getId(){
        return id;
    }

    /**
     * A fuggveny visszaadja, hogy lephet e a telepes
     */
    public boolean CanStep() {
        return canStep;
    }

    /**
     * A fuggveny beallitja, hogy lephet e a telepes
     */
    public void SetCanStep(boolean value) {
        canStep = value;
    }

    /**
     * A fuggveny reseteli az indexet
     */
    public static void ResetIndex(){
        currentIndex=0;
    }
}