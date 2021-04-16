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
        //this.game = new Game();
        this.asteroidField = game.GetField();
        this.ellipses = asteroidField.GetEllipses();
        this.sun = asteroidField.GetSun();
    }

    public boolean addasteroid(int ellipsenum, int thickness, String material,String closetosun){
        if((closetosun != "true" && closetosun != "false") || (material != "coal" && material != "ice" && material != "iron" && material != "uran")){return false;}
        boolean cts;
        if(closetosun == "true") {  cts = true;}
        else {cts = false;}
        Material material1=null;
        if(material == "uran"){
            material1 = new Uran();
        }
        else if(material == "coal"){
            material1 = new Coal();
        }
        else if(material == "ice"){
            material1 = new Ice();
        }
        else if(material == "iron"){
            material1=new Iron();
        }
        else if(material=="null"){
            material1=null;
        }
        Asteroid asteroid = new Asteroid(new Point2D(0,0),ellipses.get(ellipsenum),thickness,material1);
        asteroid.SetCloseToSun(cts);
        ellipses.get(ellipsenum).GetObjects().add(asteroid);
        return true;
    }

    public boolean addbaseasteroid(String closetosun,int ironnum,int icenum,int urannum,int coalnum){
        if(closetosun != "true" && closetosun != "false"){return false;}
        boolean cts;
        if(closetosun == "true") {  cts = true;}
        else {cts = false;}

        //game.SetBase(new Point2D(0,0),ellipses.get(1),5);
        base = game.GetBase();
        base.SetCloseToSun(cts);
        return true;
    }

    public boolean addteleportgate(String settler){
        TeleportGate t1 = new TeleportGate(new Point2D(0,0), ellipses.get(0));
        TeleportGate t2 = new TeleportGate(new Point2D(0,0), ellipses.get(0));
        t1.SetGatePair(t2);
        t2.SetGatePair(t1);
        boolean found = false;
        for(Settler s : asteroidField.GetSettlers())
            if(s.GetName() == settler){
                found = true;
                s.AddGate(t1);
                s.AddGate(t2);
            }
        if(!found) return false;
        return true;
    }

    public boolean addworker(String location, String type){
        OrbitingObject workerlocation=null;
        for(int i = 0;i<3;i++){
            for(int j = 0;j<ellipses.get(i).GetObjects().size();j++){
                if(ellipses.get(i).GetObjects().get(j).GetName()==location){workerlocation = ellipses.get(i).GetObjects().get(j);}
            }
        }
        if(workerlocation==null){return false;}
        if(type=="s"){
            asteroidField.AddSettler(new Settler(workerlocation,asteroidField,asteroidField.GetSettlers().size()+1));
        }
        else if(type=="r"){
            asteroidField.AddRobot(new Robot(workerlocation,asteroidField,asteroidField.GetRobots().size()+1));
        }
        else if(type=="u"){
            asteroidField.AddUfo(new Ufo(workerlocation,asteroidField,asteroidField.GetUfos().size()+1));
        }
        else {return false;}
        return true;
    }

    public boolean addmaterialtobackpack(String settler, String material){
        Material material1 = null;
        if(material=="coal") {material1 = new Coal();}
        else if(material=="uran") {material1 = new Uran();}
        else if(material=="iron") {material1 = new Iron();}
        else if(material=="ice") {material1 = new Ice();}
        else {return false;}
        boolean found = false;
        for(Settler s : asteroidField.GetSettlers()) {
            if (s.GetName() == settler) {
                s.AddMaterialToBackpack(material1);
                found = true;
            }
        }
        if(!found) return false;
        return true;
    }

    public boolean addsolarstorm(int angle,int warntimer,String target){

        return true;
    }

    public boolean setneighbours(String object1,String object2){
        for(int i = 0;i<3;i++){
            for(OrbitingObject o1 : ellipses.get(i).GetObjects())
                if(o1.GetName() == object1){
                    for(int j = 0;j<3;j++){
                        for(OrbitingObject o2 : ellipses.get(i).GetObjects())
                            if(o2.GetName() == object2){
                                o1.AddNeighbor(o2);
                                o2.AddNeighbor(o1);
                                return true;
                            }
                    }
                }
        }
        return false;
    }

    public boolean setclosetosun(String isclose,String object){
        boolean cts;
        if(isclose == "true") {  cts = true;}
        else if(isclose == "false") {cts = false;}
        else {return false;}
        for(int i = 0;i<3;i++){
            for(OrbitingObject o : ellipses.get(i).GetObjects())
                if(o.GetName() == object){
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
            if(s.GetName() == settler){
                s.SkipAction();
                return true;
            }
        return false;
    }

    public boolean move(String worker, String location){
        for(int i = 0;i<3;i++){
            for(OrbitingObject o : ellipses.get(i).GetObjects())
                if(o.GetName() == location){
                    for(Settler s : asteroidField.GetSettlers()) {
                        if (s.GetName() == worker) {
                            s.MoveTo(o);
                            return true;
                        }
                    }
                    for(Robot r : asteroidField.GetRobots()) {
                        if (r.GetName() == worker) {
                            r.MoveTo(o);
                            return true;
                        }
                    }
                    for(Ufo u : asteroidField.GetUfos()) {
                        if (u.GetName() == worker) {
                            u.MoveTo(o);
                            return true;
                        }
                    }
                }
        }
        return false;
    }

    public boolean drill(String worker){
        for(Settler s : asteroidField.GetSettlers()) {
            if (s.GetName() == worker) {
                s.DrillHole();
                return true;
            }
        }
        for(Robot r : asteroidField.GetRobots()) {
            if (r.GetName() == worker) {
                r.DrillHole();
                return true;
            }
        }
        return false;
    }

    public boolean mine(String worker){
        for(Settler s : asteroidField.GetSettlers()) {
            if (s.GetName() == worker) {
                s.Mine();
                return true;
            }
        }
        for(Ufo u : asteroidField.GetUfos()) {
            if (u.GetName() == worker) {
                u.Mine();
                return true;
            }
        }
        return false;
    }

    public boolean craftgate(String settler){
        for(Settler s : asteroidField.GetSettlers()) {
            if (s.GetName() == settler) {
                s.CraftGate();
                return true;
            }
        }
        return false;
    }

    public boolean buildrobot(String settler){
        for(Settler s : asteroidField.GetSettlers()) {
            if (s.GetName() == settler) {
                s.BuildRobot();
                return true;
            }
        }
        return false;
    }

    public boolean placematerial(String setller, String asteroid, String material){
        Material material1 = null;
        if(material=="coal") {material1 = new Coal();}
        else if(material=="uran") {material1 = new Uran();}
        else if(material=="iron") {material1 = new Iron();}
        else if(material=="ice") {material1 = new Ice();}
        else {return false;}
        for(int i = 0;i<3;i++){
            for(OrbitingObject o : ellipses.get(i).GetObjects())
                if(o.GetName() == asteroid){
                    for(Settler s : asteroidField.GetSettlers()) {
                        if (s.GetName() == setller) {
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
            if (s.GetName() == settler) {
                s.PlaceGate();
                return true;
            }
        }
        return false;
    }

    private void commandInterpreter(){
        String[] command = scanner.nextLine().split(" ");
        switch (command[0]){
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
                    if(!addasteroid(Integer.parseInt(command[1]), Integer.parseInt(command[2]), command[3], command[4]))
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
    }

    public static void main(String[] args) {

    }
}
