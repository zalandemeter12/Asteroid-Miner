import java.util.ArrayList;

public class Ellipse2D {
    private final Point2D[] focalpoints;
    private double velocity;
    private ArrayList<OrbitingObject> objects;
    private final double distance;

    public Ellipse2D(Point2D focalpoint0, Point2D focalpoint1, double distance, double velocity) {
        this.objects = new ArrayList<>();
        this.focalpoints = new Point2D[2];
        this.focalpoints[0] = focalpoint0;
        this.focalpoints[1] = focalpoint1;
        this.distance = distance;
        this.velocity = velocity;
        System.out.println("Ellipse2D.Ctor()");
    }

    public void RemoveObject(OrbitingObject o) {
        System.out.println("Ellipse2D.RemoveObject()");
    }

    public void MoveOrbits() {
        System.out.println("Ellipse2D.MoveOrbits()");
    }

    public Point2D GateLocation(Point2D p) {
        System.out.println("Ellipse2D.GateLocation()");
        return null;
    }

}
