package me.piedpiper.consolegame;

import me.piedpiper.businesslogic.*;

import java.util.Scanner;

public class ConsoleGame {
    Scanner scanner = new Scanner(System.in);

    public boolean addasteroid(int ellipsenum, int thickness, Material material,boolean closetosun){
        return true;
    }

    public boolean addbaseasteroid(boolean closetosun,int ironnum,int icenum,int urannum,int coalnum){
        return true;
    }

    public boolean addteleportgate(Settler settler){
        return true;
    }

    public boolean addworker(OrbitingObject location, char type){
        return true;
    }

    public boolean addmaterialtobackpack(Settler settler, Material material){
        return true;
    }

    public boolean addsolarstorm(int angle,int warntimer,OrbitingObject target){
        return true;
    }

    public boolean setneighbours(OrbitingObject object1,OrbitingObject object2){
        return true;
    }

    public boolean setclosetosun(boolean isclose,OrbitingObject object){
        return true;
    }

    public boolean setrandom(boolean trueorfalse){
        return true;
    }

    public boolean skip(Settler settler){
        return true;
    }

    public boolean move(Worker worker, OrbitingObject location){
        return true;
    }

    public boolean drill(Worker worker){
        return true;
    }

    public boolean mine(Worker worker){
        return true;
    }

    public boolean craftgate(Settler settler){
        return true;
    }

    public boolean buildrobot(Settler settler){
        return true;
    }

    public boolean placematerial(Settler setller, Asteroid asteroid, Material material){
        return true;
    }

    public boolean placegate(Settler settler){
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
