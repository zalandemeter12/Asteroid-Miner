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
        this.game = new Game();
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
        asteroid.setCloseToSun(cts);
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
        game.GetBase().setCloseToSun(cts);

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
        return true;
    }

    public boolean addmaterialtobackpack(String settler, String material){
        return true;
    }

    public boolean addsolarstorm(int angle,int warntimer,String target){
        return true;
    }

    public boolean setneighbours(String object1,String object2){
        return true;
    }

    public boolean setclosetosun(String isclose,String object){
        return true;
    }

    public boolean setrandom(String trueorfalse){
        return true;
    }

    public boolean skip(String settler){
        return true;
    }

    public boolean move(String worker, String location){
        return true;
    }

    public boolean drill(String worker){
        return true;
    }

    public boolean mine(String worker){
        return true;
    }

    public boolean craftgate(String settler){
        return true;
    }

    public boolean buildrobot(String settler){
        return true;
    }

    public boolean placematerial(String setller, String asteroid, String material){
        return true;
    }

    public boolean placegate(String settler){
        return true;
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
