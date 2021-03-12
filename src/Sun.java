import java.util.ArrayList;

public class Sun implements ISteppable {

    private Point2D position;
    private ArrayList<SolarStorm> solarStorms;

    public Sun(Point2D position) {
        this.position = position;
        System.out.println("Sun.Constructor()");
    }

    @Override
    public void Step() {
        System.out.println("Sun.Step()");
    }


}
