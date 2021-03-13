package me.piedpiper.businesslogic;

public class TeleportGate extends OrbitingObject {
    private TeleportGate gatePair;

    public TeleportGate(Point2D position, Ellipse2D ellipse, TeleportGate gatePair) {
        super(position, ellipse);
        this.gatePair = gatePair;
        System.out.println("TeleportGate.Ctor()");
    }

    @Override
    public void AddWorker(Worker w) {
        System.out.println("TeleportGate.AddWorker()");
        if(gatePair.GetLocation() != null) {
            w.SetLocation(gatePair);
            gatePair.GetWorkers().add(w);
        }
    }

    public void SetGatePair(TeleportGate tg){
        gatePair = tg;
    }

    public void SetPoint2D(Point2D p){
        position = p;
    }





}
