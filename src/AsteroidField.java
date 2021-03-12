import java.util.ArrayList;

public class AsteroidField implements ISteppable {
    private Sun sun;
    private Game game;
    private Ellipse2D[] ellipse= new Ellipse2D[3];
    private ArrayList<Robot> robots= new ArrayList<>();
    private ArrayList<Settler> settlers= new ArrayList<>();


    public AsteroidField(){
        System.out.println("AsteroidField.Ctor()");
    }

    public void Step() {
        System.out.println("AsteroidField.Step()");
    }

    public void AddRobot(Robot r) {
        System.out.println("AsteroidField.AddRobot()");
    }

    public void RemoveRobot(Robot r) {
        System.out.println("AsteroidField.RemoveRobot()");
    }

    public void AddSettler(Settler s) {
        System.out.println("AsteroidField.AddSettler()");
    }

    public void RemoveSettler(Settler s) {
        System.out.println("AsteroidField.RemoveSettler");
    }
}
