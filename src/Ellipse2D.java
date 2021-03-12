import java.util.ArrayList;

public class Ellipse2D {
    final private Point2D[] focalpoints= new Point2D[2];
    private double velocity;
    private ArrayList<OrbitingObject> objects= new ArrayList<>();

    public Ellipse2D() {
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
