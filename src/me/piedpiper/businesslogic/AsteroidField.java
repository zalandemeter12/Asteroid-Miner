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
