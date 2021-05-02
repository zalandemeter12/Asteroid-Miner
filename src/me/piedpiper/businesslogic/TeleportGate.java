package me.piedpiper.businesslogic;

import me.piedpiper.gui.AsteroidPanel;
import me.piedpiper.gui.TeleportGatePanel;
import me.piedpiper.gui.View;

import java.util.Random;

public class TeleportGate extends OrbitingObject {
    //A teleport kapu párja
    private TeleportGate gatePair;
    private boolean isMalfunctioning;
    private static int currentIndex = 0;
    private View view;
    private TeleportGatePanel panel;

    //A teleport kapu konstruktora
    public TeleportGate(Point2D position, Ellipse2D ellipse) {
        super(position, ellipse);
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.isMalfunctioning=false;
        this.id = ++currentIndex;
        this.ellipse = null;

        Logger.tabcount--;
    }

    public TeleportGate(Point2D position, Ellipse2D ellipse,View view) {
        super(position, ellipse);
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.isMalfunctioning=false;
        this.id = ++currentIndex;
        this.ellipse = null;
        this.view = view;
        panel=new TeleportGatePanel(this);
        view.AddGraphicObject(panel);
        Logger.tabcount--;
    }

    //Hozzáad egy dolgozót a teleport kapuhoz
    @Override
    public void AddWorker(Worker w) {
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".AddWorker()");
        workers.add(w);
        //Amennyiben le van helyezve a párja, rögtön átdobja a másik kapuba
        if(gatePair.GetPosition() != null) {
            workers.remove(w);
            w.SetLocation(gatePair);
            gatePair.GetWorkers().add(w);
        }

        Logger.tabcount--;
    }


    //Beállítja egy kapu párját
    public void SetGatePair(TeleportGate tg){
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".SetGatePair()");
        
        this.gatePair = tg;
        
        Logger.tabcount--;
    }

    //Beállítja az ellipszist amin elhelyezkedik a teleport kapu
    public void SetEllipse(Ellipse2D e) {
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".SetEllipse()");
        
        this.ellipse = e;
        
        Logger.tabcount--;
    }

    @Override
    public void UnderSolarStorm() {
        this.isMalfunctioning=true;
        super.UnderSolarStorm();
    }

    @Override
    public void Moves(Point2D p){
        if (isMalfunctioning) {
            //TODO a mozgatást megcsinálni rendesen
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

    @Override
    public String GetName(){
        return "TeleportGate"+id;
    }

    public static void ResetIndex(){
        currentIndex=0;
    }

    public TeleportGate getPair(){
        return this.gatePair;
    }

    public boolean isActive(){
        return gatePair.GetEllipse() != null && this.ellipse != null;
    }
}
