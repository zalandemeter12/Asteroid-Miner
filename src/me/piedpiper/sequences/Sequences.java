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
        OrbitingObject asteroid = orbitingObjects1.get(0);
        asteroid.AddMaterial(new Uran());
    }

    public void AddNeighbours(){
        asteroidField.Step();
    }

    public void AddWorkerToTeleportGate(){
        TeleportGate teleportGate1=new TeleportGate(new Point2D(1,1), ellipses.get(0), null);
        TeleportGate teleportGate2=new TeleportGate(new Point2D(2,1), ellipses.get(1), teleportGate1);
        teleportGate1.SetGatePair(teleportGate2);

        //sequence start
        teleportGate1.AddWorker(settlers.get(0));
    }

    public void AsteroidReceivesMaterial(){
        Settler s=settlers.get(0);
        Ice m=new Ice();
        s.GetBackpack().add(m);
        Asteroid a= new Asteroid(new Point2D(1,1), ellipses.get(0), 0, null);
        s.SetLocation(a);

        //sequence start
        s.PlaceMaterial(m);
    }

    public void AsteroidFieldSteps(){
        
    }
    
    public void BaseAsteroidRecievesMaterial(){
        Settler s=settlers.get(0);
        Ice m=new Ice();
        s.GetBackpack().add(m);
        s.SetLocation(base);

        //sequence start
        s.PlaceMaterial(m);
    }

    public void DrillHole(){
        Worker w = settlers.get(0);
        OrbitingObject o = w.GetLocation();
        w.DrillHole();
        o.DrilledOn();
        if(o.GetThickness() == 0 && o.IsCloseToSun())
            o.GetMaterial().BlowUp(o);
    }
    
    public void IceBlowUp(){
        /// ez az aszteroida tartalmaz jeget
        orbitingObjects1.get(2).GetMaterial().BlowUp((Asteroid) orbitingObjects1.get(2));
    }

    public void MineAsteroid(){
        Settler s = game.GetField().GetSettlers().get(0);
        s.Mine();
    }

    public void RadioactiveMaterialExplodes(){
        orbitingObjects1.get(0).SetMaterial(new Uran());
        orbitingObjects1.get(0).GetMaterial().BlowUp((Asteroid)orbitingObjects1.get(0));
    }
    
    public void RobotDies(){
        Robot r = game.GetField().GetRobots().get(0);
        r.Die();
    }

    public void RobotExplodes(){
        Robot r = game.GetField().GetRobots().get(0);
        r.Explode();
        OrbitingObject location = r.GetLocation();
        ArrayList<OrbitingObject> neighbours = location.GetNeighbors();
        neighbours.add(new Asteroid(new Point2D(1,1), ellipses.get(0), 0, null));
        r.MoveTo(neighbours.get(0));
    }

    public void SettlerBuildsRobot(){

    }
    
    public void SettlerCraftsGate(){
        
    }

    public void SettlerExplodes(){
        Settler s = game.GetField().GetSettlers().get(0);
        s.Explode();
    }

    public void SettlerMines(){
        Settler s = game.GetField().GetSettlers().get(0);
        OrbitingObject location = s.GetLocation();
        s.Mine();
        int thickness = location.GetThickness();
        if(thickness == 0 && s.GetBackpack().size()<10){
            Material material = location.GetMaterial();
            location.RemoveMaterial();
            s.AddMaterialToBackpack(material);
        }

    }

    public void SettlerPlacesGate(){
        Settler s = game.GetField().GetSettlers().get(0);
        OrbitingObject location = s.GetLocation();
        Point2D positionA = location.GetPosition();
        Ellipse2D e = location.GetEllipse();
        Point2D positionGate = e.GateLocation(positionA);
        TeleportGate t = new TeleportGate(null,e,null);
        t.SetPoint2D(positionGate);
    }

    public void SettlerDies(){
        Settler s = game.GetField().GetSettlers().get(0);
        s.Die();
    }

    public void SolarStormSteps(){
        SolarStorm ss = new SolarStorm(sun, 35, 2);
        ss.Step();
        ss.Step();
        ss.Step();
    }

    public void SunSteps(){
        sun.Step();
    }

    public void WorkerMoves(){
        orbitingObjects1.get(0).AddNeighbor(orbitingObjects1.get(1));
        Settler settler = new Settler(orbitingObjects1.get(0),asteroidField);
        settler.MoveTo(orbitingObjects1.get(1));
    }

    interface MoveAction {
        void move();
    }

    private MoveAction[] moveActions = new MoveAction[] {
        new MoveAction() { public void move() { AddMaterial();; } },
        new MoveAction() { public void move() { AddNeighbours(); } },
        new MoveAction() { public void move() { AddWorkerToTeleportGate(); } },
        new MoveAction() { public void move() { AsteroidReceivesMaterial(); } },
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
        System.out.println("21: WorkerMoves");
    }

    public static void main(String[] args) {
        Sequences sequences = new Sequences();
        
        Scanner input = new Scanner(System.in);
        int index = 0;
        
        while(true){
            printMenuItems();
            System.out.println("\n\nSelect sequence: ");
            index = input.nextInt();
            if(index == 0 || index > 21)
                break;


            sequences.moveActions[index-1].move();
        }

        input.close();
    }
}
