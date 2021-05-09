package me.piedpiper.businesslogic;

import me.piedpiper.gui.TeleportGatePanel;
import me.piedpiper.gui.View;

import java.util.Random;

public class TeleportGate extends OrbitingObject {
    /**
     * A teleport kapu párja
     */
    private TeleportGate gatePair;
    /**
     * meghibasodast jelzo attributum
     */
    private boolean isMalfunctioning;
    /**
     * index
     */
    private static int currentIndex = 0;
    /**
     * A kirajzolasert felelos peldany
     */
    private View view;
    /**
     * A kirajzolhato objektum, ami teleportkaput rajzol ki
     */
    private TeleportGatePanel panel;

    /**
     * A teleport kapu konstruktora
     */
    public TeleportGate(Point2D position, Ellipse2D ellipse) {
        super(position, ellipse);
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * tagvaltozok beallitasa
         */
        this.isMalfunctioning=false;
        this.id = ++currentIndex;
        this.ellipse = null;

        Logger.tabcount--;
    }

    /**
     * Masodik konstruktor, melyben az elozohoz kepest annzi a valtozas, hogy letrehoz egy TeleportGatePanel-t
     * amit atad a view-nak
     */
    public TeleportGate(Point2D position, Ellipse2D ellipse,View view) {
        super(position, ellipse);
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * tagvaltozok beallitasa
         */
        this.isMalfunctioning=false;
        this.id = ++currentIndex;
        this.ellipse = null;
        this.view = view;
        panel=new TeleportGatePanel(this);
        if(position!= null && ellipse!=null)
            view.AddGraphicObject(panel);
        Logger.tabcount--;
    }

    /**
     * Hozzáad egy dolgozót a teleport kapuhoz
     */
    @Override
    public void AddWorker(Worker w) {
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".AddWorker()");
        workers.add(w);
        /**
         * Amennyiben le van helyezve a párja, rögtön átdobja a másik kapuba
         */
        if(gatePair.GetPosition() != null) {
            workers.remove(w);
            w.SetLocation(gatePair);
            gatePair.GetWorkers().add(w);
        }

        Logger.tabcount--;
    }


    /**
     * Beállítja egy kapu párját
     */
    public void SetGatePair(TeleportGate tg){
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".SetGatePair()");
        
        this.gatePair = tg;
        
        Logger.tabcount--;
    }

    /**
     * Beállítja az ellipszist amin elhelyezkedik a teleport kapu
     */
    public void SetEllipse(Ellipse2D e) {
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".SetEllipse()");
        
        this.ellipse = e;
        
        Logger.tabcount--;
    }

    /**
     * napvihar eseten meghibasodik a teleportkapu
     */
    @Override
    public void UnderSolarStorm() {
        this.isMalfunctioning=true;
        super.UnderSolarStorm();
    }


    /**
     * A fuggveny beallitja a kapu uj poziciojat
     */
    @Override
    public void Moves(Point2D p){
        /**
         * meghibasodas eseten, nem a parameterkent kapott pozicioja rakja, hanem a kapu elkezd veletlenszeruen ugralni az aszteroidamezon
         */
        if (isMalfunctioning) {
            if (neighbors.size() > 0) {
                if (ellipse.GetField().IsRandom()) {
                    Random rand = new Random();
                    int idx = rand.nextInt(neighbors.size()-1);
                    this.position = neighbors.get(idx).GetPosition();
                } else {
                    this.position = neighbors.get(0).GetPosition();
                }
            }
        } else {
            this.position=p;
        }
    }

    /**
     * A fuggveny visszaadja a teleportkapu nevet
     */
    @Override
    public String GetName(){
        return "TeleportGate"+id;
    }

    /**
     * A fuggveny reseteli az indexet
     */
    public static void ResetIndex(){
        currentIndex=0;
    }

    /**
     * A fuggveny visszaadja a kapu parjat
     */
    public TeleportGate GetPair(){
        return this.gatePair;
    }

    /**
     * A fuggveny visszaadja, hogy aktiv e a teleportkapu
     */
    public boolean IsActive(){
        return gatePair.GetEllipse() != null && this.ellipse != null;
    }

    /**
     * elhelyezi a teleportkaput kirajzolo objektumot
     */
    public void PlacePanel(){
        if(panel==null){
            panel=new TeleportGatePanel(this);
        }
        view.AddGraphicObject(panel);
    }
}
