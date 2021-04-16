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

                    }
                    catch (Exception e){
                        System.out.println("Invalid argument");
                    }
                }

                break;
            case "addbaseasteroid":
                break;
            case "addteleportgate":
                break;
            case "addworker":
                break;
            case "addmaterialtobackpack":
                break;
            case "addsolarstorm":
                break;
            case "setneighbours":
                break;
            case "setclosetosun":
                break;
            case "setrandom":
                break;
            case "skip":
                break;
            case "move":
                break;
            case "drill":
                break;
            case "mine":
                break;
            case "craftgate":
                break;
            case "buildrobot":
                break;
            case "placematerial":
                break;
            case "placegate":
                break;

        }

    }

    public static void main(String[] args) {

    }
}
