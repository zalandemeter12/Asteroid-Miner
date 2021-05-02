package me.piedpiper.businesslogic;

import me.piedpiper.gui.View;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;

//A játékok összefogó objektum, tertalmazza és létrehozza a fő objektumokat
public class Game {
    //Az aszteroida mező, ami az aszteroidákat tartalmazza
    private AsteroidField field;
    //A bázis aszteroidát külön ismeri
    private BaseAsteroid base;

    private static View view;

    //Konstruktor
    public Game() {
        Logger.logMessage("Game#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        view = new View(this);
        Sun sun = new Sun(new Point2D(0.0,0.0),null);
        ArrayList<OrbitingObject> objects1 = new ArrayList<>();
        ArrayList<OrbitingObject> objects2 = new ArrayList<>();
        ArrayList<OrbitingObject> objects3 = new ArrayList<>();
        ArrayList<Ellipse2D> ellipses = new ArrayList<>();
        ellipses.add(new Ellipse2D(new Point2D(10,10), new Point2D(20,20), 5, 3, objects1));
        ellipses.add(new Ellipse2D(new Point2D(15,15), new Point2D(25,25), 10, 3, objects2));
        ellipses.add(new Ellipse2D(new Point2D(20,20), new Point2D(30,30), 15, 3, objects3));
        ArrayList<Settler> settlers = new ArrayList<>();
        this.field = new AsteroidField(sun, this, ellipses, settlers);
        ellipses.get(0).SetField(this.field);
        ellipses.get(1).SetField(this.field);
        ellipses.get(2).SetField(this.field);
        sun.SetField(this.field);

        Logger.tabcount--;

    }

    public void testInitSequences(int settlerCount) {
        Sun sun = new Sun(new Point2D(0.0,0.0),null);

        ArrayList<OrbitingObject> objects1 = new ArrayList<>();
        ArrayList<OrbitingObject> objects2 = new ArrayList<>();
        ArrayList<OrbitingObject> objects3 = new ArrayList<>();
        ArrayList<Ellipse2D> ellipses = new ArrayList<>();
        ellipses.add(new Ellipse2D(new Point2D(10,10), new Point2D(20,20), 5, 3, objects1));
        ellipses.add(new Ellipse2D(new Point2D(15,15), new Point2D(25,25), 10, 3, objects2));
        ellipses.add(new Ellipse2D(new Point2D(20,20), new Point2D(30,30), 15, 3, objects3));
        for (int i = 0; i < 5; ++i) {
            objects1.add(new Asteroid(new Point2D(1,1),ellipses.get(0),5, new Iron()));
            objects1.add(new Asteroid(new Point2D(1,1),ellipses.get(0),5, new Coal()));
            objects1.add(new Asteroid(new Point2D(1,1),ellipses.get(0),5, new Ice()));
            objects1.add(new Asteroid(new Point2D(1,1),ellipses.get(0),5, new Uran()));

            objects2.add(new Asteroid(new Point2D(1,1),ellipses.get(1),5, new Iron()));
            objects2.add(new Asteroid(new Point2D(1,1),ellipses.get(1),5, new Coal()));
            objects2.add(new Asteroid(new Point2D(1,1),ellipses.get(1),5, new Ice()));
            objects2.add(new Asteroid(new Point2D(1,1),ellipses.get(1),5, new Uran()));

            objects3.add(new Asteroid(new Point2D(1,1),ellipses.get(2),5, new Iron()));
            objects3.add(new Asteroid(new Point2D(1,1),ellipses.get(2),5, new Coal()));
            objects3.add(new Asteroid(new Point2D(1,1),ellipses.get(2),5, new Ice()));
            objects3.add(new Asteroid(new Point2D(1,1),ellipses.get(2),5, new Uran()));
        }

        this.base = new BaseAsteroid(new Point2D(5,5), ellipses.get(1), 3, this);

        ArrayList<Settler> settlers = new ArrayList<>();
        this.field = new AsteroidField(sun, this, ellipses, settlers);
        for (int i = 0; i < settlerCount; ++i) {
            settlers.add(new Settler(base,field));
        }

        sun.SetField(this.field);
    }

    public void testInit(){
        Sun sun = new Sun(new Point2D(0.0,0.0),null);
        ArrayList<OrbitingObject> objects1 = new ArrayList<>();
        ArrayList<OrbitingObject> objects2 = new ArrayList<>();
        ArrayList<OrbitingObject> objects3 = new ArrayList<>();
        ArrayList<Ellipse2D> ellipses = new ArrayList<>();
        ellipses.add(new Ellipse2D(new Point2D(10,10), new Point2D(20,20), 5, 3, objects1));
        ellipses.add(new Ellipse2D(new Point2D(15,15), new Point2D(25,25), 10, 3, objects2));
        ellipses.add(new Ellipse2D(new Point2D(20,20), new Point2D(30,30), 15, 3, objects3));
        ArrayList<Settler> settlers = new ArrayList<>();
        this.field = new AsteroidField(sun, this, ellipses, settlers);

        Asteroid a1=new Asteroid(new Point2D(1,4), field.GetEllipses().get(0),5, new Iron());
        Asteroid a2=new Asteroid(new Point2D(4,4), field.GetEllipses().get(0),0, null);
        Asteroid a3=new Asteroid(new Point2D(-1,-6), field.GetEllipses().get(0),4, new Ice());
        Asteroid a4=new Asteroid(new Point2D(10,5), field.GetEllipses().get(1),7, new Coal());
        Asteroid a5=new Asteroid(new Point2D(3,7), field.GetEllipses().get(1),2, new Uran());
        Asteroid a6=new Asteroid(new Point2D(9,-9), field.GetEllipses().get(2),0, null);
        a2.GetNeighbors().add(a1);
        a1.GetNeighbors().add(a2);
        a1.GetNeighbors().add(a3);
        a3.GetNeighbors().add(a1);
        a3.GetNeighbors().add(a4);
        a4.GetNeighbors().add(a3);
        a1.GetNeighbors().add(a5);
        a5.GetNeighbors().add(a1);

        TeleportGate tg1=new TeleportGate(new Point2D(3,3), field.GetEllipses().get(0));
        TeleportGate tg2=new TeleportGate(new Point2D(5,2), field.GetEllipses().get(2));
        tg1.SetGatePair(tg2);
        tg2.SetGatePair(tg1);
        tg1.GetNeighbors().add(a5);
        a5.GetNeighbors().add(tg1);
        TeleportGate tg3=new TeleportGate(new Point2D(6,6), field.GetEllipses().get(2));
        TeleportGate tg4=new TeleportGate(null, null);
        tg4.SetGatePair(tg3);
        tg3.SetGatePair(tg4);

        Robot r1=new Robot(a4, field);
        Ufo u1= new Ufo(a5, field);
        a4.SetCloseToSun(true);
        a4.AddWorker(r1);
        a5.AddWorker(u1);

        field.GetEllipses().get(0).GetObjects().add(a1);
        field.GetEllipses().get(0).GetObjects().add(a2);
        field.GetEllipses().get(0).GetObjects().add(a3);
        field.GetEllipses().get(1).GetObjects().add(a4);
        field.GetEllipses().get(1).GetObjects().add(a5);
        field.GetEllipses().get(2).GetObjects().add(a6);
        field.GetEllipses().get(0).GetObjects().add(tg1);
        field.GetEllipses().get(2).GetObjects().add(tg2);
        field.GetEllipses().get(2).GetObjects().add(tg3);

        Settler s1=new Settler(a1, field);
        s1.GetBackpack().add(new Iron());
        Settler s2=new Settler(a3, field);
        s2.AddGate(tg4);
        this.base=new BaseAsteroid(new Point2D(3,3), field.GetEllipses().get(0), 0,this);
        field.GetEllipses().get(0).GetObjects().add(base);
        field.GetSettlers().add(s1);
        field.GetSettlers().add(s2);
        field.GetSun().GetSolarStorms().add(new SolarStorm(field.GetSun(), 10, 2));
    }

    public void SetBase(BaseAsteroid b){
        this.base = b;
    }

    //Elintdítja a játékot
    public void StartGame(){
        Logger.logMessage("Game#" + Integer.toHexString(this.hashCode()) + ".StartGame()");
        Logger.tabcount--;
    }
    //Kezeli a játék köreit
    public void GameLoop(){
        Logger.logMessage("Game#" + Integer.toHexString(this.hashCode()) + ".GameLoop()");
        Logger.tabcount--;
    }

    //Új kör indítását végzi el
    public void NextRound(){
        Logger.logMessage("Game#" + Integer.toHexString(this.hashCode()) + ".NextRound()");

        if(field.GetSettlers().size() == 0) {
            EndGame(false);
        } else {
            field.SetActiveSettler(field.GetSettlers().get(0));
        }

        field.Step();

        Logger.tabcount--;
    }

    //Játék befejezése
    public void EndGame(boolean win){
        Logger.logMessage("Game#" + Integer.toHexString(this.hashCode()) + ".EndGame()");
        Logger.tabcount--;
    }

    //Bázis getter
    public BaseAsteroid GetBase() {
        Logger.logMessage("Game#" + Integer.toHexString(this.hashCode()) + ".GetBase()");
        Logger.tabcount--;
        return base;
    }

    //Aszteroida mező getter
    public AsteroidField GetField(){
        Logger.logMessage("Game#" + Integer.toHexString(this.hashCode()) + ".GetField()");
        Logger.tabcount--;
        return field;
    }

    public void WriteJson(){
        JSONObject jsonObject = new JSONObject();
        if (field.GetActiveSettler() != null)
            jsonObject.put("ActiveSettler", field.GetActiveSettler().GetName());
        else
            jsonObject.put("ActiveSettler", "NULL");

        // innentol a keringo objektumok felepitese
        JSONObject orbitingObjectsJson = new JSONObject();

        // bazis aszteroida
        JSONObject baseAsteroidJson = new JSONObject();
        if (base != null) {
            baseAsteroidJson.put("xCoordinate", base.GetPosition().GetX());
            baseAsteroidJson.put("yCoordinate", base.GetPosition().GetY());
            baseAsteroidJson.put("thickness", base.GetThickness());

            // chest tartalma
            JSONArray chestJson = new JSONArray();
            ArrayList<String> chestItemNames = new ArrayList<String>();


            for (Material material : base.GetChest())
            {
                chestItemNames.add(material.GetName());
            }
            chestJson.putAll(chestItemNames);
            baseAsteroidJson.put("chest", chestJson);


            // bazis szomszedai
            JSONArray baseNeighboursJson = new JSONArray();
            ArrayList<String> baseNeighboursNames = new ArrayList<String>();


            for (OrbitingObject neighbour : base.GetNeighbors())
            {
                baseNeighboursNames.add(neighbour.GetName());
            }
            baseNeighboursJson.putAll(baseNeighboursNames);
            baseAsteroidJson.put("neighbours", baseNeighboursJson);

            orbitingObjectsJson.put("BaseAsteroid", baseAsteroidJson);
        } else {
            orbitingObjectsJson.put("BaseAsteroid", "NULL");
        }

        // lista az aszteroidakrol
        JSONArray asteroidListJson = new JSONArray();

        for (Ellipse2D ellipse : field.GetEllipses()) {
            for (OrbitingObject orbitingObject : ellipse.GetObjects()) {
                if (orbitingObject.getClass() == Asteroid.class){
                    JSONObject asteroidJson = new JSONObject();
                    asteroidJson.put("xCoordinate", orbitingObject.GetPosition().GetX());
                    asteroidJson.put("yCoordinate", orbitingObject.GetPosition().GetY());
                    asteroidJson.put("thickness", orbitingObject.GetThickness());
                    asteroidJson.put("ellipse", ellipse.GetId());

                    JSONArray neighboursJson = new JSONArray();
                    ArrayList<String> neighboursNames = new ArrayList<String>();

                    for (OrbitingObject neighbour : orbitingObject.GetNeighbors())
                    {
                        neighboursNames.add(neighbour.GetName());
                    }
                    neighboursJson.putAll(neighboursNames);

                    asteroidJson.put("neighbours", neighboursJson);
                    if(orbitingObject.GetMaterial() == null){
                        asteroidJson.put("material", "NULL");
                    }else {
                        asteroidJson.put("material", orbitingObject.GetMaterial().GetName());
                    }
                    asteroidJson.put("closeToSun", orbitingObject.IsCloseToSun());

                    JSONObject asteroidJsonWrapper = new JSONObject();
                    asteroidJsonWrapper.put(orbitingObject.GetName(), asteroidJson);
                    asteroidListJson.put(asteroidJsonWrapper);
                }
            }
        }

        orbitingObjectsJson.put("Asteroids:", asteroidListJson);

        // lista a teleportkapukrol
        JSONArray teleportGateListJson = new JSONArray();

        for (Ellipse2D ellipse : field.GetEllipses()) {
            for (OrbitingObject orbitingObject : ellipse.GetObjects()) {
                if (orbitingObject.getClass() == TeleportGate.class){
                    JSONObject teleportGateJson = new JSONObject();
                    teleportGateJson.put("xCoordinate", orbitingObject.GetPosition().GetX());
                    teleportGateJson.put("yCoordinate", orbitingObject.GetPosition().GetY());


                    JSONArray neighboursJson = new JSONArray();
                    ArrayList<String> neighboursNames = new ArrayList<String>();

                    for (OrbitingObject neighbour : orbitingObject.GetNeighbors())
                    {
                        neighboursNames.add(neighbour.GetName());
                    }
                    neighboursJson.putAll(neighboursNames);

                    teleportGateJson.put("neighbours", neighboursJson);
                    teleportGateJson.put("active", ((TeleportGate) orbitingObject).isActive());

                    teleportGateJson.put("closeToSun", orbitingObject.IsCloseToSun());
                    teleportGateJson.put("pair", ((TeleportGate)orbitingObject).getPair().GetName());

                    JSONObject teleportGateJsonWrapper = new JSONObject();
                    teleportGateJsonWrapper.put(orbitingObject.GetName(), teleportGateJson);
                    teleportGateListJson.put(teleportGateJsonWrapper);
                }
            }
        }

        orbitingObjectsJson.put("TeleportGates:", teleportGateListJson);

        jsonObject.put("OrbitingObjects", orbitingObjectsJson);

        // lista a telepesekrol
        JSONArray settlerListJson = new JSONArray();

        if(base != null) {
            for (Worker worker : base.GetWorkers()) {
                if (worker.getClass() == Settler.class) {
                    JSONObject settlerJson = new JSONObject();

                    // material inventory
                    JSONArray inventoryJson = new JSONArray();
                    ArrayList<String> inventoryNames = new ArrayList<String>();
                    for (Material material : ((Settler) worker).GetBackpack()) {
                        inventoryNames.add(material.GetName());
                    }
                    inventoryJson.putAll(inventoryNames);
                    settlerJson.put("inventory", inventoryJson);

                    // teleportGate inventory
                    JSONArray teleportGateJson = new JSONArray();
                    ArrayList<String> teleportGateNames = new ArrayList<String>();
                    for (TeleportGate teleportGate : ((Settler) worker).GetGateInventory()) {
                        teleportGateNames.add(teleportGate.GetName());
                    }
                    teleportGateJson.putAll(teleportGateNames);
                    settlerJson.put("teleportGateInventory", teleportGateNames);

                    settlerJson.put("location", worker.location.GetName());

                    JSONObject settlerJsonWrapper = new JSONObject();
                    settlerJsonWrapper.put(worker.GetName(), settlerJson);
                    settlerListJson.put(settlerJsonWrapper);
                }
            }
        }
        for (Ellipse2D ellipse : field.GetEllipses()) {
            for (OrbitingObject orbitingObject : ellipse.GetObjects()) {
                for (Worker worker : orbitingObject.GetWorkers()) {
                    if (worker.getClass() == Settler.class) {
                        JSONObject settlerJson = new JSONObject();

                        // material inventory
                        JSONArray inventoryJson = new JSONArray();
                        ArrayList<String> inventoryNames = new ArrayList<String>();
                        for (Material material : ((Settler) worker).GetBackpack()) {
                            inventoryNames.add(material.GetName());
                        }
                        inventoryJson.putAll(inventoryNames);
                        settlerJson.put("inventory", inventoryJson);

                        // teleportGate inventory
                        JSONArray teleportGateJson = new JSONArray();
                        ArrayList<String> teleportGateNames = new ArrayList<String>();
                        for (TeleportGate teleportGate : ((Settler) worker).GetGateInventory()) {
                            teleportGateNames.add(teleportGate.GetName());
                        }
                        teleportGateJson.putAll(teleportGateNames);
                        settlerJson.put("teleportGateInventory", teleportGateNames);

                        settlerJson.put("location", worker.location.GetName());

                        JSONObject settlerJsonWrapper = new JSONObject();
                        settlerJsonWrapper.put(worker.GetName(), settlerJson);
                        settlerListJson.put(settlerJsonWrapper);
                    }
                }
            }
        }

        jsonObject.put("Settlers", settlerListJson);

        // lista a robotokrol

        JSONArray robotListJson = new JSONArray();

        for (Ellipse2D ellipse : field.GetEllipses()) {
            for (OrbitingObject orbitingObject : ellipse.GetObjects()) {
                for (Worker worker : orbitingObject.GetWorkers()) {
                    if (worker.getClass() == Robot.class) {
                        JSONObject robotJson = new JSONObject();
                        robotJson.put("location", worker.location.GetName());

                        JSONObject robotJsonWrapper = new JSONObject();
                        robotJsonWrapper.put(worker.GetName(), robotJson);
                        robotListJson.put(robotJsonWrapper);
                    }
                }
            }
        }

        jsonObject.put("Robots", robotListJson);

        // lista az ufokrol
        JSONArray ufoListJson = new JSONArray();

        for (Ellipse2D ellipse : field.GetEllipses()) {
            for (OrbitingObject orbitingObject : ellipse.GetObjects()) {
                for (Worker worker : orbitingObject.GetWorkers()) {
                    if (worker.getClass() == Ufo.class) {
                        JSONObject ufoJson = new JSONObject();
                        ufoJson.put("location: ", worker.location.GetName());

                        JSONObject ufoJsonWrapper = new JSONObject();
                        ufoJsonWrapper.put(worker.GetName(), ufoJson);
                        ufoListJson.put(ufoJsonWrapper);
                    }
                }
            }
        }

        jsonObject.put("Ufos", ufoListJson);

        // lista a solarStromokrol
        JSONArray solarStormListJson = new JSONArray();

        for (SolarStorm solarStorm : field.GetSun().GetSolarStorms()) {
            JSONObject solarStormJson = new JSONObject();
            solarStormJson.put("angle", solarStorm.GetAngle());
            solarStormJson.put("warnTimer", solarStorm.GetWarnTimer());

            JSONObject solarStormJsonWrapper = new JSONObject();
            solarStormJsonWrapper.put(solarStorm.GetName(), solarStormJson);
            solarStormListJson.put(solarStormJsonWrapper);
        }

        jsonObject.put("Solarstorms", solarStormListJson);

        System.out.println(jsonObject.toString(4));

    }

    //Belépési pont
    public static void main(String[] args){
        //Logger.logOnConsole = false;
        Game game = new Game();
        //game.testInit();
        //game.WriteJson();
        Sun sun = new Sun(new Point2D(0,0),game.GetField(),view);
        TeleportGate t = new TeleportGate(new Point2D(400,400),game.GetField().GetEllipses().get(0),view);
        BaseAsteroid base = new BaseAsteroid(new Point2D(200,200),game.GetField().GetEllipses().get(0), 0, null, view);
        Asteroid a=new Asteroid(new Point2D(100, 100), game.GetField().GetEllipses().get(0), 0, null, view);
        Asteroid ab=new Asteroid(new Point2D(800, 100), game.GetField().GetEllipses().get(0), 0, null, view);
        a.AddWorker(new Robot(a, game.field, view));
        a.AddWorker(new Robot(a, game.field, view));
        a.AddWorker(new Robot(a, game.field, view));
        a.AddWorker(new Ufo(a, game.field, view));
        a.AddWorker(new Settler(a, game.field, view));
        Ellipse2D e= new Ellipse2D(new Point2D(-10,-10), new Point2D(10,10), 5, 3, null, view);

        view.setVisible(true);

    }
}
