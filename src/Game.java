import java.util.ArrayList;

public class Game {

    private AsteroidField field;

    public Game(int settlerCount) {
        Sun sun = new Sun(new Point2D(0.0,0.0));
        ArrayList<Ellipse2D> ellipses = new ArrayList<>();
        /*
        ellipses.add(new Ellipse2D());
        ellipses.add(new Ellipse2D());
        ellipses.add(new Ellipse2D());
        */
        ArrayList<Settler> settlers = new ArrayList<>();
        for (int i = 0; i < settlerCount; ++i) {
            //settlers.add(new Settler());
        }
        this.field = new AsteroidField(sun, this, ellipses, settlers);
        System.out.println("Game.Ctor()");
    }

    public void StartGame(){
        System.out.println("Game.StartGame()");
    }

    public void GameLoop(){
        System.out.println("Game.StartGame()");
    }

    public void NextRound(){
        System.out.println("Game.NextRound()");
    }

    public void EndGame(){
        System.out.println("Game.EndGame()");
    }

    public static void main(String[] args){
        System.out.println("Waaanyád");
    }
}
