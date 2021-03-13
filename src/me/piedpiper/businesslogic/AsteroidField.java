package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class AsteroidField implements ISteppable {
    private Sun sun;
    private Game game;
    private ArrayList<Ellipse2D> ellipses;
    private ArrayList<Robot> robots;
    private ArrayList<Settler> settlers;


    public AsteroidField(Sun sun, Game game, ArrayList<Ellipse2D> ellipses, ArrayList<Settler> settlers){
        this.sun = sun;
        this.game = game;
        this.ellipses = ellipses;
        this.robots = new ArrayList<>();
        this.settlers = settlers;
        System.out.println("AsteroidField.Ctor()");
    }

    public void Step() {

        System.out.println("AsteroidField.Step()");
        sun.Step();
        ArrayList<OrbitingObject> orbitingObjects = new ArrayList<OrbitingObject>();
        
        

        for(Ellipse2D e: ellipses) {
            e.MoveOrbits();
            orbitingObjects.addAll(e.GetObjects());
        }

        for(int i = 0; i < orbitingObjects.size(); i++){
            for(int j = 0; j < orbitingObjects.size(); j++){
                if(i != j){
                    OrbitingObject o1 = orbitingObjects.get(i);
                    OrbitingObject o2 = orbitingObjects.get(j);
                    if(o1.GetPosition().DistanceFrom(o2.GetPosition()) < 10){
                        o1.AddNeighbor(o2);
                    }
                }
            }
        }

        for (Robot r : robots) {
            r.Step();
        }

    }

    public void AddRobot(Robot r) {
        robots.add(r);
        System.out.println("AsteroidField.AddRobot()");
    }

    public void RemoveRobot(Robot r) {
        robots.remove(r);
        System.out.println("AsteroidField.RemoveRobot()");
    }

    public void AddSettler(Settler s) {
        settlers.add(s);
        System.out.println("AsteroidField.AddSettler()");
    }

    public void RemoveSettler(Settler s) {
        settlers.remove(s);
        if(settlers.isEmpty()) game.EndGame();
        System.out.println("AsteroidField.RemoveSettler()");
    }

    public ArrayList<Ellipse2D> GetEllipses(){
        return ellipses;
    }

    public Sun GetSun(){
        return sun;
    }

    public ArrayList<Robot> GetRobots(){
        return robots;
    }

    public ArrayList<Settler> GetSettlers(){
        return settlers;
    }
}
