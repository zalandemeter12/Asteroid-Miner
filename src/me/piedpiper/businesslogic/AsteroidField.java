package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class AsteroidField implements ISteppable {
    private Sun sun;
    private Game game;
    private ArrayList<Ellipse2D> ellipses;
    private ArrayList<Robot> robots;
    private ArrayList<Settler> settlers;


    public AsteroidField(Sun sun, Game game, ArrayList<Ellipse2D> ellipses, ArrayList<Settler> settlers){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.sun = sun;
        this.game = game;
        this.ellipses = ellipses;
        this.robots = new ArrayList<>();
        this.settlers = settlers;

        Logger.tabcount--;
    }

    public void Step() {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".Step()");
        
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

        Logger.tabcount--;
    }

    public void AddRobot(Robot r) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".AddRobot()");
        
        robots.add(r);
        
        Logger.tabcount--;
    }

    public void RemoveRobot(Robot r) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".RemoveRobot()");
       
        robots.remove(r);
        
        Logger.tabcount--;
    }

    public void AddSettler(Settler s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".AddSettler()");
        
        settlers.add(s);
        
        Logger.tabcount--;
    }

    public void RemoveSettler(Settler s) {
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".RemoveSettler()");
        
        settlers.remove(s);
        if(settlers.isEmpty()) game.EndGame();
        
        Logger.tabcount--;
    }

    public ArrayList<Ellipse2D> GetEllipses(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetEllipses()");
        Logger.tabcount--;
        return ellipses;
    }

    public Sun GetSun(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetSun()");
        Logger.tabcount--;
        return sun;
    }

    public ArrayList<Robot> GetRobots(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetRobots()");
        Logger.tabcount--;
        return robots;
    }

    public ArrayList<Settler> GetSettlers(){
        Logger.logMessage("AsteroidField#" + Integer.toHexString(this.hashCode()) + ".GetSettlers()");
        Logger.tabcount--;
        return settlers;
    }
}
