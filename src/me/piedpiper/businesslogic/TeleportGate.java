package me.piedpiper.businesslogic;

public class TeleportGate extends OrbitingObject {
    //A teleport kapu párja
    private TeleportGate gatePair;
    private boolean isMalfunctioning;

    //A teleport kapu konstruktora
    public TeleportGate(Point2D position, Ellipse2D ellipse) {
        super(position, ellipse);
        isMalfunctioning=false;
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        Logger.tabcount--;
    }

    public TeleportGate(Point2D position, Ellipse2D ellipse, int i) {
        super(position, ellipse);
        isMalfunctioning=false;
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        Logger.tabcount--;
        id=i;
    }

    //Hozzáad egy dolgozót a teleport kapuhoz
    @Override
    public void AddWorker(Worker w) {
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".AddWorker()");
       
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
    public void UnderSolarStorm(){
        isMalfunctioning=true;
    }

    @Override
    public void Moves(Point2D p){
        if(isMalfunctioning){
            position=p;
        }
    }

    @Override
    public String GetName(){
        return "TeleportGate"+id;
    }
}
