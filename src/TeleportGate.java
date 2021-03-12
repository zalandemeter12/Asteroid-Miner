public class TeleportGate extends OrbitingObject {
    private final TeleportGate gatePair;

    public TeleportGate(Point2D position, Ellipse2D ellipse, TeleportGate gatePair) {
        super(position, ellipse);
        this.gatePair = gatePair;
    }

    @Override
    public void AddWorker(Worker w) {
        gatePair.AddWorker(w);
        System.out.println("TeleportGate.AddWorker()");
    }
}
