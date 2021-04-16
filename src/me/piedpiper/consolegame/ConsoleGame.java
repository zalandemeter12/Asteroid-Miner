package me.piedpiper.consolegame;

import me.piedpiper.businesslogic.*;

import java.util.Scanner;

import java.util.ArrayList;

public class ConsoleGame {
    Scanner scanner = new Scanner(System.in);

    private BaseAsteroid base;
    private Game game;
    private AsteroidField asteroidField;
    private Sun sun;
    private ArrayList<Ellipse2D> ellipses;

    public void init(){}

    public boolean addasteroid(int ellipsenum, int thickness, String material,String closetosun){
        if(material == "Uran"){
            Asteroid asteroid = new Asteroid(new Point2D(0,0),ellipses.get(ellipsenum),thickness,new Uran());
            asteroid.setCloseToSun(closetosun);
            ellipses.get(ellipsenum).GetObjects().add(asteroid);
            return true;
        }
        else if(material == "Coal"){
            Asteroid asteroid = new Asteroid(new Point2D(0,0),ellipses.get(ellipsenum),thickness,new Coal());
            asteroid.setCloseToSun(closetosun);
            ellipses.get(ellipsenum).GetObjects().add(asteroid);
            return true;
        }
        else if(material == "Ice"){
            Asteroid asteroid = new Asteroid(new Point2D(0,0),ellipses.get(ellipsenum),thickness,new Ice());
            asteroid.setCloseToSun(closetosun);
            ellipses.get(ellipsenum).GetObjects().add(asteroid);
            return true;
        }
        else if(material == "Iron"){
            Asteroid asteroid = new Asteroid(new Point2D(0,0),ellipses.get(ellipsenum),thickness,new Iron());
            asteroid.setCloseToSun(closetosun);
            ellipses.get(ellipsenum).GetObjects().add(asteroid);
            return true;
        }
        return false;



        return true;
    }

    public boolean addbaseasteroid(String closetosun,int ironnum,int icenum,int urannum,int coalnum){
        base = new BaseAsteroid(new Point2D(0,0), ellipses.get(1),5,null,game );
        return true;
    }

    public boolean addteleportgate(String settler){
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
