package me.piedpiper.consolegame;

import me.piedpiper.businesslogic.Game;

import java.util.Scanner;

public class ConsoleGame {
    private Scanner scanner = new Scanner(System.in);


    private void CommandInterpreter(){
        String command = scanner.nextLine();
        String[] commandLine = command.split(" ");
        switch (commandLine[0]){
            case "addasteroid":
                if(commandLine.length < 5)
                    System.out.println("Missing argument");
                if(commandLine.length > 5)
                    System.out.println("too many arguments");
                else{
                    //TODO
                }


                break;
            case "addbaseasteroid":
                //TODO
                break;
            case "addteleportgate":
                //TODO
                break;
            case "addworker":
                //TODO
                break;
            case "addmaterialtobackpack":
                //TODO
                break;
            case "addsolarstorm":
                //TODO
                break;
            case "setneighbours":
                //TODO
                break;
            case "setclosetosun":
                //TODO
                break;
            case "setrandom":
                //TODO
                break;
            case "skip":
                //TODO
                break;
            case "move":
                //TODO
                break;
            case "drill":
                //TODO
                break;
            case "mine":
                //TODO
                break;
            case "craftgate":
                //TODO
                break;
            case "buildrobot":
                //TODO
                break;
            case "placematerial":
                //TODO
                break;
            case "placegate":
                //TODO
                break;
            default:
                System.out.println(command.split(" ")[0]+ " is not recognized as a valid command");
        }

    }

    public static void main(String[] args) {

    }
}
