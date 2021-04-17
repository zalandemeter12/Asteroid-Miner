package me.piedpiper.consolegame;

import me.piedpiper.businesslogic.*;

import java.util.Scanner;

import java.util.ArrayList;

public class ConsoleGame {
    Scanner scanner = new Scanner(System.in);

    private Game game;
    private BaseAsteroid base;
    private AsteroidField asteroidField;
    private Sun sun;
    private ArrayList<Ellipse2D> ellipses;

    public void init(){
        Logger.logOnConsole = false;
        this.game = new Game();
        this.asteroidField = game.GetField();
        this.ellipses = asteroidField.GetEllipses();
        this.sun = asteroidField.GetSun();
    }

    public boolean addasteroid(int ellipsenum, int thickness, String material, String closetosun){
        boolean cts;
        if(closetosun.equals("true")) {  cts = true;}
        else if(closetosun.equals("false")) { cts = false; }
        else { return false; }
        Material material1;
        switch (material) {
            case "uran":
                material1 = new Uran();
                break;
            case "coal":
                material1 = new Coal();
                break;
            case "ice":
                material1 = new Ice();
                break;
            case "iron":
                material1 = new Iron();
                break;
            case "null":
                material1 = null;
                break;
            default:
                return false;
        }
        Asteroid asteroid = new Asteroid(new Point2D(0,0),ellipses.get(ellipsenum-1),thickness,material1);
        asteroid.SetCloseToSun(cts);
        ellipses.get(ellipsenum-1).GetObjects().add(asteroid);
        return true;
    }

    public boolean addbaseasteroid(String closetosun,int ironnum,int icenum,int urannum,int coalnum){
        boolean cts;
        if(closetosun.equals("true")) {  cts = true;}
        else if(closetosun.equals("false")) { cts = false; }
        else { return false; }

        BaseAsteroid base = new BaseAsteroid(new Point2D(0,0),ellipses.get(1),5, game);
        for (int i = 0; i < ironnum; ++i) {
            base.AddMaterial(new Iron());
        }
        for (int i = 0; i < icenum; ++i) {
            base.AddMaterial(new Ice());
        }
        for (int i = 0; i < urannum; ++i) {
            base.AddMaterial(new Uran());
        }
        for (int i = 0; i < coalnum; ++i) {
            base.AddMaterial(new Coal());
        }
        base.SetCloseToSun(cts);

        game.SetBase(base);
        return true;
    }

    public boolean addteleportgate(String settler){
        TeleportGate t1 = new TeleportGate(new Point2D(0,0), ellipses.get(0));
        TeleportGate t2 = new TeleportGate(new Point2D(0,0), ellipses.get(0));
        t1.SetGatePair(t2);
        t2.SetGatePair(t1);
        boolean found = false;
        for(Settler s : asteroidField.GetSettlers())
            if(s.GetName().equals(settler)){
                found = true;
                s.AddGate(t1);
                s.AddGate(t2);
                break;
            }
        return found;
    }

    public boolean addworker(String location, String type){
        OrbitingObject workerlocation = null;
        for(Ellipse2D e: ellipses){
            for(OrbitingObject o: e.GetObjects()){
                if(o.GetName().equals(location)) { workerlocation = o; }
            }
        }
        if(workerlocation == null){ return false; }
        switch (type) {
            case "s":
                asteroidField.AddSettler(new Settler(workerlocation, asteroidField));
                break;
            case "r":
                asteroidField.AddSteppable(new Robot(workerlocation, asteroidField));
                break;
            case "u":
                asteroidField.AddSteppable(new Ufo(workerlocation, asteroidField));
                break;
            default:
                return false;
        }
        return true;
    }

    public boolean addmaterialtobackpack(String settler, String material){
        Material material1;
        switch (material) {
            case "coal":
                material1 = new Coal();
                break;
            case "uran":
                material1 = new Uran();
                break;
            case "iron":
                material1 = new Iron();
                break;
            case "ice":
                material1 = new Ice();
                break;
            default:
                return false;
        }
        boolean found = false;
        for(Settler s : asteroidField.GetSettlers()) {
            if (s.GetName().equals(settler)) {
                s.AddMaterialToBackpack(material1);
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean addsolarstorm(int angle, int warntimer, String target){
        SolarStorm ss = new SolarStorm(sun,angle,warntimer);
        boolean found = false;
        for (Ellipse2D e: ellipses) {
            for (OrbitingObject o: e.GetObjects()) {
                if (o.GetName().equals(target)) {
                    ss.AddTarget(o);
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    public boolean setneighbours(String object1,String object2){
        OrbitingObject o1 = null;
        OrbitingObject o2 = null;
        boolean found1 = false;
        boolean found2= false;
        for(Ellipse2D e: ellipses){
            for(OrbitingObject o : e.GetObjects()) {
                if(o.GetName().equals(object1)){
                    found1 = true;
                    o1 = o;
                }
                if(o.GetName().equals(object2)){
                    found2 = true;
                    o2 = o;
                }
            }
        }
        if (found1 && found2) {
            o1.AddNeighbor(o2);
            o2.AddNeighbor(o1);
        }
        return found1 && found2;
    }

    public boolean setclosetosun(String closetosun,String object){
        boolean cts;
        if(closetosun.equals("true")) {  cts = true;}
        else if(closetosun.equals("false")) { cts = false; }
        else { return false; }

        for(Ellipse2D e: ellipses){
            for(OrbitingObject o : e.GetObjects())
                if(o.GetName().equals(object)){
                    o.SetCloseToSun(cts);
                }
        }
        return true;
    }

    public boolean setrandom(String trueorfalse){
        return true;
    }

    public boolean skip(String settler){
        for(Settler s : asteroidField.GetSettlers())
            if(s.GetName().equals(settler)){
                s.SkipAction();
                return true;
            }
        return false;
    }

    public boolean move(String worker, String location){
        for(Ellipse2D e: ellipses){
            for(OrbitingObject o : e.GetObjects())
                if(o.GetName().equals(location)){
                    for(Settler s : asteroidField.GetSettlers()) {
                        if (s.GetName().equals(worker)) {
                            s.MoveTo(o);
                            return true;
                        }
                    }
                    for(ISteppable s : asteroidField.GetSteppables()) {
                        if (((Worker) s).GetName().equals(worker)) {
                            ((Worker)s).MoveTo(o);
                            return true;
                        }
                    }
                }
        }
        return false;
    }

    public boolean drill(String worker){
        for(Settler s : asteroidField.GetSettlers()) {
            if (s.GetName().equals(worker)) {
                s.DrillHole();
                return true;
            }
        }
        for(ISteppable s: asteroidField.GetSteppables()) {
            if (worker.contains("Ufo")) return false;
            if (((Worker)s).GetName().equals(worker)) {
                ((Worker)s).DrillHole();
                return true;
            }
        }
        return false;
    }

    public boolean mine(String worker){
        for(Settler s : asteroidField.GetSettlers()) {
            if (s.GetName().equals(worker)) {
                s.Mine();
                return true;
            }
        }
        for(ISteppable s: asteroidField.GetSteppables()) {
            if (worker.contains("Robot")) return false;
            if (((Worker) s).GetName().equals(worker)) {
                ((Worker)s).Mine();
                return true;
            }
        }
        return false;
    }

    public boolean craftgate(String settler){
        for(Settler s : asteroidField.GetSettlers()) {
            if (s.GetName().equals(settler)) {
                s.CraftGate();
                return true;
            }
        }
        return false;
    }

    public boolean buildrobot(String settler){
        for(Settler s : asteroidField.GetSettlers()) {
            if (s.GetName().equals(settler)) {
                s.BuildRobot();
                return true;
            }
        }
        return false;
    }

    public boolean placematerial(String setller, String asteroid, String material){
        Material material1;
        switch (material) {
            case "coal":
                material1 = new Coal();
                break;
            case "uran":
                material1 = new Uran();
                break;
            case "iron":
                material1 = new Iron();
                break;
            case "ice":
                material1 = new Ice();
                break;
            default:
                return false;
        }
        for(Ellipse2D e: ellipses){
            for(OrbitingObject o : e.GetObjects())
                if(o.GetName().equals(asteroid)){
                    for(Settler s : asteroidField.GetSettlers()) {
                        if (s.GetName().equals(setller)) {
                            s.PlaceMaterial(material1);
                            return true;
                        }
                    }
                }
        }
        return false;
    }

    public boolean placegate(String settler){
        for(Settler s : asteroidField.GetSettlers()) {
            if (s.GetName().equals(settler)) {
                s.PlaceGate();
                return true;
            }
        }
        return false;
    }

    private boolean CommandInterpreter(){
        String[] command = scanner.nextLine().split(" ");
        switch (command[0]){
            case "quit":
                return false;
            case "addasteroid":
                if(command.length > 5)
                    System.out.println("Too many arguments");
                else if(command.length < 5)
                    System.out.println("Missing argument");
                else{
                    try{
                        if(!addasteroid(Integer.parseInt(command[1]), Integer.parseInt(command[2]), command[3], command[4]))
                            System.out.println("Invalid arguments");
                    }
                    catch (Exception e){
                        System.out.println("Invalid argument");
                    }
                }
                break;
            case "addbaseasteroid":
                if(command.length > 6)
                    System.out.println("Too many arguments");
                else if(command.length < 6)
                    System.out.println("Missing argument");
                else{
                    try{
                        if(!addbaseasteroid(command[1],Integer.parseInt(command[2]), Integer.parseInt(command[3]),Integer.parseInt(command[4]),Integer.parseInt(command[5])))
                            System.out.println("Invalid arguments");
                    }
                    catch (Exception e){
                        System.out.println("Invalid argument");
                    }
                }
                break;
            case "addteleportgate":
                if(command.length > 2)
                    System.out.println("Too many arguments");
                else if(command.length < 2)
                    System.out.println("Missing argument");
                else{
                    if(!addteleportgate(command[1]))
                        System.out.println("Invalid arguments");
                }
                break;
            case "addworker":
                if(command.length > 3)
                    System.out.println("Too many arguments");
                else if(command.length < 3)
                    System.out.println("Missing argument");
                else{
                    if(!addworker(command[1], command[2]))
                        System.out.println("Invalid arguments");
                }
                break;
            case "addmaterialtobackpack":
                if(command.length > 3)
                    System.out.println("Too many arguments");
                else if(command.length < 3)
                    System.out.println("Missing argument");
                else{
                    if(!addmaterialtobackpack(command[1], command[2]))
                        System.out.println("Invalid arguments");
                }
                break;
            case "addsolarstorm":
                if(command.length > 4)
                    System.out.println("Too many arguments");
                else if(command.length < 4)
                    System.out.println("Missing argument");
                else{
                    try{
                        if(!addsolarstorm(Integer.parseInt(command[1]), Integer.parseInt(command[2]), command[3]))
                            System.out.println("Invalid arguments");
                    }
                    catch (Exception e){
                        System.out.println("Invalid argument");
                    }
                }
                break;
            case "setneighbours":
                if(command.length > 3)
                    System.out.println("Too many arguments");
                else if(command.length < 3)
                    System.out.println("Missing argument");
                else{
                    if(!setneighbours(command[1], command[2]))
                        System.out.println("Invalid arguments");
                }
                break;
            case "setclosetosun":
                if(command.length > 3)
                    System.out.println("Too many arguments");
                else if(command.length < 3)
                    System.out.println("Missing argument");
                else{
                    if(!setclosetosun(command[1], command[2]))
                        System.out.println("Invalid arguments");
                }
                break;
            case "setrandom":
                if(command.length > 2)
                    System.out.println("Too many arguments");
                else if(command.length < 2)
                    System.out.println("Missing argument");
                else{
                    if(!setrandom(command[1]))
                        System.out.println("Invalid arguments");
                }
                break;
            case "skip":
                if(command.length > 2)
                    System.out.println("Too many arguments");
                else if(command.length < 2)
                    System.out.println("Missing argument");
                else {
                    if (!skip(command[1]))
                        System.out.println("Invalid arguments");
                }
                break;

            case "move":
                if(command.length > 3)
                    System.out.println("Too many arguments");
                else if(command.length < 3)
                    System.out.println("Missing argument");
                else {
                    if (!move(command[1], command[2]))
                        System.out.println("Invalid arguments");
                }
                break;

            case "drill":
                if(command.length > 2)
                    System.out.println("Too many arguments");
                else if(command.length < 2)
                    System.out.println("Missing argument");
                else {
                    if (!drill(command[1]))
                        System.out.println("Invalid arguments");
                }
                break;

            case "mine":
                if(command.length > 2)
                    System.out.println("Too many arguments");
                else if(command.length < 2)
                    System.out.println("Missing argument");
                else {
                    if (!mine(command[1]))
                        System.out.println("Invalid arguments");
                }
                break;

            case "craftgate":
                if(command.length > 2)
                    System.out.println("Too many arguments");
                else if(command.length < 2)
                    System.out.println("Missing argument");
                else {
                    if (!craftgate(command[1]))
                        System.out.println("Invalid arguments");
                }
                break;

            case "buildrobot":
                if(command.length > 2)
                    System.out.println("Too many arguments");
                else if(command.length < 2)
                    System.out.println("Missing argument");
                else {
                    if (!buildrobot(command[1]))
                        System.out.println("Invalid arguments");
                }
                break;

            case "placematerial":
                if(command.length > 4)
                    System.out.println("Too many arguments");
                else if(command.length < 4)
                    System.out.println("Missing argument");
                else {
                    if (!placematerial(command[1], command[2], command[3]))
                        System.out.println("Invalid arguments");
                }
                break;

            case "placegate":
                if(command.length > 2)
                    System.out.println("Too many arguments");
                else if(command.length < 2)
                    System.out.println("Missing argument");
                else {
                    if (!placegate(command[1]))
                        System.out.println("Invalid arguments");
                }
                break;
            default:
                System.out.println("Command not found");
                break;
        }
        return true;
    }

    public static void main(String[] args) {
        ConsoleGame consoleGame = new ConsoleGame();
        consoleGame.init();
        while (consoleGame.CommandInterpreter()) {
            consoleGame.game.WriteJson();
        }
    }
}
