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
    private ArrayList<ISteppable> steppable;
    private BaseAsteroid base;
    private Scanner scanner;
    private int selectedScenario;

    public Sequences() {
       Init();
    }

    public void Init(){
        Logger.logOnConsole = false;
        this.game = new Game();
        game.testInitSequences(4);
        this.base = game.GetBase();
        this.asteroidField = game.GetField();
        this.ellipses = asteroidField.GetEllipses();
        this.sun = asteroidField.GetSun();
        this.settlers = asteroidField.GetSettlers();
        this.steppable = asteroidField.GetSteppables();
        this.orbitingObjects1 = ellipses.get(0).GetObjects();
        this.orbitingObjects2 = ellipses.get(1).GetObjects();
        this.orbitingObjects3 = ellipses.get(2).GetObjects();
        this.scanner = new Scanner(System.in);
 
        this.steppable.add(new Robot(this.base, this.asteroidField));
        this.steppable.add(new Robot(this.base, this.asteroidField));
        Logger.logOnConsole = true;
    }
    
    public void SettlerMovesToTeleportGate() {
        Logger.tabcount = -1;
        System.out.println("Scenarios of settler moves to teleport gate:\n");
        System.out.println("1: The gate's pair is active");
        System.out.println("2: The gate's pair is inactive");
        System.out.println("\nSelect scenario: ");
        selectedScenario = scanner.nextInt();
        if(selectedScenario > 0 && selectedScenario < 3) {
            if(selectedScenario == 1){
                Logger.logOnConsole = false;
                TeleportGate t1 = new TeleportGate(new Point2D(0.0,0.0), ellipses.get(0));
                TeleportGate t2 = new TeleportGate(new Point2D(0.0,0.0), ellipses.get(0));
                t1.SetGatePair(t2);
                Settler settler = new Settler(orbitingObjects1.get(0), asteroidField);
                orbitingObjects1.get(0).AddNeighbor(t1);
                Logger.logOnConsole = true;
                Logger.logGetter = true;
                settler.MoveTo(t1);   
                Logger.logGetter = false;
            }
            else{
                Logger.logOnConsole = false;
                TeleportGate t1 = new TeleportGate(new Point2D(0.0,0.0), ellipses.get(0));
                TeleportGate t2 = new TeleportGate(new Point2D(0.0,0.0), ellipses.get(0));
                t1.SetGatePair(t2);
                Settler settler = new Settler(orbitingObjects1.get(0), asteroidField);
                orbitingObjects1.get(0).AddNeighbor(t1);
                Logger.logOnConsole = true;
                settler.MoveTo(t1);   
            }
            Init();
        }
        else
            System.out.println("Invalid scenario number");
    }

    public void SettlerPlacesMaterialOnAsteroid() {
        Logger.tabcount = -1;
        System.out.println("Scenarios of settler places material on asteroid:\n");
        System.out.println("1: Asteroid doesn't have any layers of rock left and the asteroid is hollow.");
        System.out.println("2: Asteroid doesn't have any layers of rock left and the asteroid is not hollow.");
        System.out.println("3: Asteroid has at least 1 layer of rock.");
        System.out.println("\nSelect scenario: ");
        selectedScenario = scanner.nextInt();
        if(selectedScenario > 0 && selectedScenario < 4) {
            if(selectedScenario == 1){
                Logger.logOnConsole = false;
                Asteroid location = new Asteroid(new Point2D(0.0, 0.0),ellipses.get(0),0,null);
                Settler s = new Settler(location, asteroidField);
                Logger.logOnConsole = true;
                s.PlaceMaterial(new Uran());
            }
            else if(selectedScenario == 2){
                Logger.logOnConsole = false;
                Asteroid location = new Asteroid(new Point2D(0.0, 0.0),ellipses.get(0),0,new Uran());
                Settler s = new Settler(location, asteroidField);
                Logger.logOnConsole = true;
                s.PlaceMaterial(new Uran());
            }
            else{
                Logger.logOnConsole = false;
                Asteroid location = new Asteroid(new Point2D(0.0, 0.0),ellipses.get(0),2,null);
                Settler s = new Settler(location, asteroidField);
                Logger.logOnConsole = true;
                s.PlaceMaterial(new Uran());
            }
            Init();
        }
        else
            System.out.println("Invalid scenario number");

    }

    public void SettlerPlacesMaterialOnBaseAsteroid() {
        Logger.tabcount = -1;
        System.out.println("Scenarios of settler places material on base asteroid:\n");
        System.out.println("1: Baseasteroid has enough space for the material.");
        System.out.println("2: Baseasteroid doesn't have enough space for the material.");
        System.out.println("3: If the baseasteroid has 3 unit of every material, the settlers win the game.");
        System.out.println("\nSelect scenario: ");
        selectedScenario = scanner.nextInt();
        if(selectedScenario > 0 && selectedScenario < 4) {
            if(selectedScenario == 1){
                Logger.logOnConsole = false;
                Settler s = new Settler(base, asteroidField);
                Logger.logOnConsole = true;
                s.PlaceMaterial(new Uran());
            }
            else if(selectedScenario == 2){
                Logger.logOnConsole = false;
                Settler s = new Settler(base, asteroidField);
                base.AddMaterial(new Uran());
                base.AddMaterial(new Uran());
                Logger.logOnConsole = true;
                s.PlaceMaterial(new Uran());
            }
            else{
                Logger.logOnConsole = false;
                Settler s = new Settler(base, asteroidField);
                for (int i = 0; i < 3; ++i) {
                    base.AddMaterial(new Coal());
                    base.AddMaterial(new Ice());
                    base.AddMaterial(new Iron());
                }
                base.AddMaterial(new Uran());
                base.AddMaterial(new Uran());
                Uran a = new Uran();
                Logger.logOnConsole = true;
                s.PlaceMaterial(a);
            }
            Init();
        }
        else
            System.out.println("Invalid scenario number");

    }

    public void SettlerBuildsRobot() {
        Logger.tabcount = -1;
        System.out.println("Scenarios of settler builds robot:\n");
        System.out.println("1: Settler has all the required material to build a robot.");
        System.out.println("2: Settler doesn't have all the required material to build a robot.");
        System.out.println("\nSelect scenario: ");
        selectedScenario = scanner.nextInt();
        if(selectedScenario > 0 && selectedScenario < 3) {
            if(selectedScenario == 1){
                Logger.logOnConsole = false;
                Asteroid n = new Asteroid(new Point2D(0.0,0.0), ellipses.get(0), 3, null);
                Settler settler = new Settler(orbitingObjects1.get(0), asteroidField);
				settler.AddMaterialToBackpack(new Uran());
				settler.AddMaterialToBackpack(new Iron());
                settler.AddMaterialToBackpack(new Coal());
                Logger.logOnConsole = true;
                settler.BuildRobot();
            }
            else{
                Logger.logOnConsole = false;
                Asteroid n = new Asteroid(new Point2D(0.0,0.0), ellipses.get(0), 3, null);
				Settler settler = new Settler(orbitingObjects1.get(0), asteroidField);
                settler.AddMaterialToBackpack(new Iron());
                settler.AddMaterialToBackpack(new Coal());
                Logger.logOnConsole = true;
                settler.BuildRobot();
            }
            Init();
        }
        else
            System.out.println("Invalind scenario number");
    }

    public void SettlerCraftsGate() {
        System.out.println("Scenarios of settler crafts gate:\n");
        System.out.println("1: Settler has all the required material to build a teleport gate pair.");
        System.out.println("2: Settler doesn't have all the required material to teleport gate pair.");
        System.out.println("\nSelect scenario: ");
        selectedScenario = scanner.nextInt();
        if(selectedScenario > 0 && selectedScenario < 3) {
            if(selectedScenario == 1){
                Logger.logOnConsole = false;
                Asteroid n = new Asteroid(new Point2D(0.0,0.0), ellipses.get(0), 3, null);
                Settler settler = new Settler(orbitingObjects1.get(0), asteroidField);
				settler.AddMaterialToBackpack(new Uran());
				settler.AddMaterialToBackpack(new Iron());
                settler.AddMaterialToBackpack(new Iron());
                settler.AddMaterialToBackpack(new Ice());
                Logger.logOnConsole = true;
                settler.CraftGate();
            }
            else{
                Logger.logOnConsole = false;
                Asteroid n = new Asteroid(new Point2D(0.0,0.0), ellipses.get(0), 3, null);
                Settler settler = new Settler(orbitingObjects1.get(0), asteroidField);
                Logger.logOnConsole = true;
                settler.CraftGate();
            }
            Init();
        }
        else
            System.out.println("Invalind scenario number");

    }

    public void SettlerPlacesGate() {
        Logger.tabcount = -1;
        System.out.println("Scenarios of settler places gate:\n");
        System.out.println("1: Settler places gate");
        System.out.println("\nSelect scenario: ");
        selectedScenario = scanner.nextInt();
        if(selectedScenario == 1){
            Logger.logOnConsole = false;
            TeleportGate t1 = new TeleportGate(null, null);
            Settler s = new Settler(orbitingObjects1.get(0), asteroidField);
            s.AddGate(t1);
            Logger.logGetter=true;
            Logger.logSetter=true;
            Logger.logOnConsole = true;
            s.PlaceGate();
            Logger.logGetter=false;
            Logger.logSetter=false;
            Logger.logOnConsole = false;
            Init();    
        }
        else
            System.out.println("Invalind scenario number");
        Settler s = game.GetField().GetSettlers().get(0);

    }

    public void SettlerMovesToAsteroid() {
        Logger.tabcount = -1;
        System.out.println("Scenarios of settler moves to asteroid:\n");
        System.out.println("1: Settler tries to move to a neighbour asteroid");
        System.out.println("2: Settler tries to move to an asteroid that is not a neighbour");
        System.out.println("\nSelect scenario: ");
        selectedScenario = scanner.nextInt();
        if(selectedScenario > 0 && selectedScenario < 3) {
            if(selectedScenario == 1){
                Logger.logOnConsole = false;
                Asteroid n = new Asteroid(new Point2D(0.0,0.0), ellipses.get(0), 3, null);
                Settler settler = new Settler(orbitingObjects1.get(0), asteroidField);
                orbitingObjects1.get(0).AddNeighbor(n);
                Logger.logOnConsole = true;
                settler.MoveTo(n);
            }
            else{
                Logger.logOnConsole = false;
                Asteroid n = new Asteroid(new Point2D(0.0,0.0), ellipses.get(0), 3, null);
                Settler settler = new Settler(orbitingObjects1.get(0), asteroidField);
                Logger.logOnConsole = true;
                settler.MoveTo(n);
            }
            Init();
        }
        else
            System.out.println("Invalind scenario number");
    }

	public void SkipSettler() {
		Logger.tabcount = -1;
        System.out.println("Scenarios of player skips settler:\n");
        System.out.println("1:Player skips settler");
        System.out.println("\nSelect scenario: ");
        selectedScenario = scanner.nextInt();
        if(selectedScenario == 1) {
            Logger.logOnConsole = false;
            Asteroid n = new Asteroid(new Point2D(0.0, 0.0), ellipses.get(0), 0, new Iron());
            Settler settler = new Settler(n, asteroidField);
            Logger.logOnConsole = true;
            settler.SkipAction();
            Init();
        }
        else
            System.out.println("Invalind scenario number");
    }

    public void SettlerMinesAsteroid() {
        Logger.tabcount = -1;
        System.out.println("Scenarios of settler mines asteroid:\n");
        System.out.println("1: Rock layer thickness is 0 and there is material inside");
        System.out.println("2: Rock layer thickness is 0, but there is no material inside");
        System.out.println("3: Rock layer thickness is not 0, therefore settler cannot mine");
        System.out.println("\nSelect scenario: ");
        selectedScenario = scanner.nextInt();
        Asteroid n;
        Settler settler;
        switch(selectedScenario) {
            case 1:
                Logger.logOnConsole = false;
                n =  new Asteroid(new Point2D(0.0,0.0), ellipses.get(0), 0, new Iron());
                settler = new Settler(n, asteroidField);
                Logger.logOnConsole = true;
                settler.Mine();
              break;
            case 2:
                Logger.logOnConsole = false;
                n = new Asteroid(new Point2D(0.0,0.0), ellipses.get(0), 0, null);
                settler = new Settler(n, asteroidField);
                Logger.logOnConsole = true;
                settler.Mine();
              break;
            case 3:
                Logger.logOnConsole = false;
                n = new Asteroid(new Point2D(0.0,0.0), ellipses.get(0), 3, new Iron());
                settler = new Settler(n, asteroidField);
                Logger.logOnConsole = true;
                settler.Mine();
            break;
            default:
                System.out.println("Invalind scenario number");
          }
          Init();
    }

    public void SettlerDrillsAsteroid() {
        Logger.tabcount = -1;
        System.out.println("Scenarios of settler drills asteroid:\n");
        System.out.println("1: Asteroid has at least 1 layer of rock.");
        System.out.println("2: Asteroid doesn't have any layers of rock left.");
        System.out.println("3: If the settler drills the last layer of rock, the asteroid's core is radioactive and the asteroid is near the Sun, than the settler dies.");
        System.out.println("\nSelect scenario: ");
        selectedScenario = scanner.nextInt();
        if(selectedScenario > 0 && selectedScenario < 4) {
            Logger.logOnConsole = false;
			if (selectedScenario == 1) {
                Asteroid a = new Asteroid(new Point2D(0.0,0.0), ellipses.get(0), 2, null);
				Settler settler = new Settler(orbitingObjects1.get(0), asteroidField);
				Logger.logOnConsole = true;
                settler.DrillHole();
            } else if(selectedScenario==2){
                Asteroid a = new Asteroid(new Point2D(0.0,0.0), ellipses.get(0), 0, null);
				Settler settler = new Settler(orbitingObjects1.get(0), asteroidField);
                Logger.logOnConsole = true;
                settler.DrillHole();
			} else if(selectedScenario==3) {
			    Asteroid a = new Asteroid(new Point2D(0.0,0.0), ellipses.get(0), 1, new Uran());
				Settler settler = new Settler(orbitingObjects1.get(0), asteroidField);
                settler.SetLocation(a);
				a.SetCloseToSun(true);
                Logger.logOnConsole = true;
                settler.DrillHole();
            
            }
			Init();
        }
        else
            System.out.println("Invalind scenario number");
    }
    
    interface MoveAction {
        void move();
    }

    private MoveAction[] moveActions = new MoveAction[] {
        new MoveAction() { public void move() { SettlerMovesToTeleportGate(); } },
        new MoveAction() { public void move() { SettlerPlacesMaterialOnAsteroid(); } },
        new MoveAction() { public void move() { SettlerPlacesMaterialOnBaseAsteroid(); } },
        new MoveAction() { public void move() { SettlerBuildsRobot(); } },
        new MoveAction() { public void move() { SettlerCraftsGate(); } },
        new MoveAction() { public void move() { SettlerPlacesGate(); } },
        new MoveAction() { public void move() { SettlerMovesToAsteroid(); } },
        new MoveAction() { public void move() { SkipSettler(); } },
        new MoveAction() { public void move() { SettlerMinesAsteroid(); } },
        new MoveAction() { public void move() { SettlerDrillsAsteroid(); } },
    };

    static private void printMenuItems() {
        System.out.println("Use cases:\n");
        System.out.println("0: Exit");
        System.out.println("1: Settler moves to teleport gate");
        System.out.println("2: Settler places material on asteroid");
        System.out.println("3: Settler places material on base asteroid");
        System.out.println("4: Settler builds robot");
        System.out.println("5: Settler crafts gate");
        System.out.println("6: Settler places gate");
        System.out.println("7: Settler moves to asteroid");
        System.out.println("8: Skip settler");
        System.out.println("9: Settler mines asteroid");
        System.out.println("10: Settler drills asteroid");
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

            System.out.println("\ndo you want to continue? 1/0");
            if(input.nextInt() != 1)
                break;
        }

        input.close();
    }
}
