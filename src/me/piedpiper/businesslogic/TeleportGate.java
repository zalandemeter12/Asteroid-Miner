package me.piedpiper.businesslogic;

public class TeleportGate extends OrbitingObject {
    private TeleportGate gatePair;

    public TeleportGate(Point2D position, Ellipse2D ellipse) {
        super(position, ellipse);
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        Logger.tabcount--;
    }

    @Override
    public void AddWorker(Worker w) {
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".AddWorker()");
       
        if(gatePair.GetPosition() != null) {
            workers.remove(w);
            w.SetLocation(gatePair);
            gatePair.GetWorkers().add(w);
        }
        
        Logger.tabcount--;
    }

    public void SetGatePair(TeleportGate tg){
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".SetGatePair()");
        
        this.gatePair = tg;
        
        Logger.tabcount--;
    }

    public void SetEllipse(Ellipse2D e) {
        Logger.logMessage("TeleportGate#" + Integer.toHexString(this.hashCode()) + ".SetEllipse()");
        
        this.ellipse = e;
        
        Logger.tabcount--;
    }
}
