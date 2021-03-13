package me.piedpiper.sequences;

import me.piedpiper.businesslogic.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Sequences {

    private Game game;
    private AsteroidField asteroidField;
    private Sun sun;
    private ArrayList<Ellipse2D> ellipses;
    private ArrayList<OrbitingObject> orbitingObjects1;
    private ArrayList<OrbitingObject> orbitingObjects2;
    private ArrayList<OrbitingObject> orbitingObjects3;
    private ArrayList<Settler> settlers;
    private ArrayList<Robot> robots;
    private BaseAsteroid base;

    public Sequences() {
        this.game = new Game(2);
        this.base = game.GetBase();
        this.asteroidField = game.GetField();
        this.ellipses = asteroidField.GetEllipses();
        this.sun = asteroidField.GetSun();
        this.settlers = asteroidField.GetSettlers();
        this.robots = asteroidField.GetRobots();
        this.orbitingObjects1 = ellipses.get(0).GetObjects();
        this.orbitingObjects2 = ellipses.get(1).GetObjects();
        this.orbitingObjects3 = ellipses.get(2).GetObjects();
 
        this.robots.add(new Robot(this.base, this.asteroidField));
        this.robots.add(new Robot(this.base, this.asteroidField));
    }
    

    public void AddMaterial(){

    }

    public void AddNeighbours(){


    }

    public void AddWorkerToTeleportGate(){
        
    }

    public void AsteroidRecievesMaterial(){
        
    }

    public void AsteroidFieldSteps(){
        
    }
    
    public void BaseAsteroidRecievesMaterial(){
        
    }

    public void DrillHole(){
        
    }
    
    public void IceBlowUp(){
        
    }

    public void MineAsteroid(){
        
    }

    public void RadioactiveMaterialExplodes(){
        
    }
    
    public void RobotDies(){
        
    }

    public void RobotExplodes(){
        
    }

    public void SettlerBuildsRobot(){
        
    }
    
    public void SettlerCraftsGate(){
        
    }
    public void SettlerExplodes(){
        
    }
    public void SettlerMines(){
        
    }
    public void SettlerPlacesGate(){
        
    }
    public void SettlerDies(){
        
    }
    public void SolarStormSteps(){
        
    }
    public void SunSteps(){
        
    }
    public void UranBlowUp(){
        
    }
    public void WorkerMoves(){
        
    }

    interface MoveAction {
        void move();
    }

    private MoveAction[] moveActions = new MoveAction[] {
        new MoveAction() { public void move() { AddMaterial();; } },
        new MoveAction() { public void move() { AddNeighbours(); } },
        new MoveAction() { public void move() { AddWorkerToTeleportGate(); } },
        new MoveAction() { public void move() { AsteroidRecievesMaterial(); } },
        new MoveAction() { public void move() { AsteroidFieldSteps(); } },
        new MoveAction() { public void move() { BaseAsteroidRecievesMaterial(); } },
        new MoveAction() { public void move() { DrillHole(); } },
        new MoveAction() { public void move() { IceBlowUp(); } },
        new MoveAction() { public void move() { MineAsteroid(); } },
        new MoveAction() { public void move() { RadioactiveMaterialExplodes(); } },
        new MoveAction() { public void move() { RobotDies(); } },
        new MoveAction() { public void move() { RobotExplodes(); } },
        new MoveAction() { public void move() { SettlerBuildsRobot(); } },
        new MoveAction() { public void move() { SettlerCraftsGate(); } },
        new MoveAction() { public void move() { SettlerExplodes(); } },
        new MoveAction() { public void move() { SettlerMines(); } },
        new MoveAction() { public void move() { SettlerPlacesGate(); } },
        new MoveAction() { public void move() { SettlerDies(); } },
        new MoveAction() { public void move() { SolarStormSteps(); } },
        new MoveAction() { public void move() { SunSteps(); } },
        new MoveAction() { public void move() { UranBlowUp(); } },
        new MoveAction() { public void move() { WorkerMoves(); } },
    };

    static private void printMenuItems() {
        System.out.println("0: Exit");
        System.out.println("1: Add Material");
        System.out.println("2: AddNeighbours");
        System.out.println("3: AddWorkerToTeleportGate");
        System.out.println("4: AsteroidRecievesMaterial");
        System.out.println("5: AsteroidFieldSteps");
        System.out.println("6: BaseAsteroidRecievesMaterial");
        System.out.println("7: DrillHole");
        System.out.println("8: IceBlowUp");
        System.out.println("9: MineAsteroid");
        System.out.println("10: RadioactiveMaterialExplodes");
        System.out.println("11: RobotDies");
        System.out.println("12: RobotExplodes");
        System.out.println("13: SettlerBuildsRobot");
        System.out.println("14: SettlerCraftsGate");
        System.out.println("15: SettlerExplodes");
        System.out.println("16: SettlerMines");
        System.out.println("17: SettlerPlacesGate");
        System.out.println("18: SettlerDies");
        System.out.println("19: SolarStormSteps");
        System.out.println("20: SunSteps");
        System.out.println("21: UranBlowUp");
        System.out.println("22: WorkerMoves");
    }

    public static void main(String[] args) {
        Sequences sequences = new Sequences();
        
        Scanner input = new Scanner(System.in);
        int index = 0;
        
        while(input.nextInt() != 0){
            printMenuItems();
            sequences.moveActions[index].move();
        }
        
        input.close();
    }
}
