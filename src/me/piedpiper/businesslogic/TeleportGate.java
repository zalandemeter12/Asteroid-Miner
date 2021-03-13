package me.piedpiper.businesslogic;

public class TeleportGate extends OrbitingObject {
    private TeleportGate gatePair;

    public TeleportGate(Point2D position, Ellipse2D ellipse) {
        super(position, ellipse);
        System.out.println("TeleportGate.Ctor()");
    }

    @Override
    public void AddWorker(Worker w) {
        System.out.println("TeleportGate.AddWorker()");
        if(gatePair.GetPosition() != null) {
            w.SetLocation(gatePair);
            gatePair.GetWorkers().add(w);
        }
    }

    public void SetGatePair(TeleportGate tg){
        gatePair = tg;
    }
}
