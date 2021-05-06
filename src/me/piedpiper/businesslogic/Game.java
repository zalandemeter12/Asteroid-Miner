package me.piedpiper.businesslogic;

import me.piedpiper.gui.View;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.StrictMath.PI;

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
        Init();

        Logger.tabcount--;

    }

    public void Init(){
        Sun sun = new Sun(new Point2D(0.0,0.0),null, view);

        ArrayList<OrbitingObject> objects1 = new ArrayList<>();
        ArrayList<OrbitingObject> objects2 = new ArrayList<>();
        ArrayList<OrbitingObject> objects3 = new ArrayList<>();

        ArrayList<Ellipse2D> ellipses = new ArrayList<>();
        ArrayList<Settler> settlers = new ArrayList<>();
        this.field = new AsteroidField(sun, this, ellipses, settlers);
        sun.SetField(this.field);

        ellipses.add(new Ellipse2D(900, 400, 5, 0.04, objects1, view));
        ellipses.add(new Ellipse2D(800, 300, 10, -0.04, objects2, view));
        ellipses.add(new Ellipse2D(700, 200, 15, 0.02, objects3, view));

        this.base = new BaseAsteroid(new Point2D(1,1), ellipses.get(1), 0, this, view);

        Random rand=new Random();
        int bound=6;
        for(int i=0; i<2; i++) {
            objects3.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(2), rand.nextInt(bound), new Iron(), view));
            objects3.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(2), rand.nextInt(bound), new Ice(), view));
            objects3.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(2), rand.nextInt(bound), new Coal(), view));
            objects3.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(2), rand.nextInt(bound), null, view));
            objects3.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(2), rand.nextInt(bound), new Iron(), view));
            objects3.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(2), rand.nextInt(bound), new Uran(), view));

            objects2.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(1), rand.nextInt(bound), new Iron(), view));
            objects2.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(1), rand.nextInt(bound), new Ice(), view));
            objects2.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(1), rand.nextInt(bound), new Coal(), view));
            objects2.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(1), rand.nextInt(bound), new Uran(), view));
            objects2.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(1), rand.nextInt(bound), new Uran(), view));
            objects2.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(1), rand.nextInt(bound), null, view));

            objects1.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(0), rand.nextInt(bound), new Iron(), view));
            objects1.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(0), rand.nextInt(bound), null, view));
            objects1.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(0), rand.nextInt(bound), new Ice(), view));
            objects1.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(0), rand.nextInt(bound), new Coal(), view));
            objects1.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(0), rand.nextInt(bound), new Uran(), view));
            objects1.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(0), rand.nextInt(bound), new Iron(), view));
            objects1.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(0), rand.nextInt(bound), new Ice(), view));
            objects1.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(0), rand.nextInt(bound), new Uran(), view));
            objects1.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(0), rand.nextInt(bound), new Uran(), view));
            objects1.add(new Asteroid(new Point2D(1, 4), field.GetEllipses().get(0), rand.nextInt(bound), new Coal(), view));
        }

        /*TeleportGate teleportGate1= new TeleportGate(null, null, view);
        TeleportGate teleportGate2= new TeleportGate(null, null, view);
        teleportGate1.SetGatePair(teleportGate2);
        teleportGate2.SetGatePair(teleportGate1);*/

        for(int i=0; i<3;i++) {
            settlers.add(new Settler(base, field, view));
        }
        /*settlers.get(0).AddGate(teleportGate1);
        settlers.get(0).AddGate(teleportGate2);*/
        field.SetActiveSettler(field.GetSettlers().get(0));

        OrbitingObject randO1=objects1.get(rand.nextInt(objects1.size()));
        OrbitingObject randO2=objects2.get(rand.nextInt(objects2.size()));
        OrbitingObject randO3=objects3.get(rand.nextInt(objects3.size()));

        new Ufo(randO1, field, view);
        new Ufo(randO2, field, view);
        new Ufo(randO3, field, view);




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
        ellipses.add(new Ellipse2D(new Point2D(10,10), new Point2D(20,20), 5, 0.4, objects1));
        ellipses.add(new Ellipse2D(new Point2D(15,15), new Point2D(25,25), 10, -0.4, objects2));
        ellipses.add(new Ellipse2D(new Point2D(20,20), new Point2D(30,30), 15, 0.3, objects3));
        ArrayList<Settler> settlers = new ArrayList<>();
        this.field = new AsteroidField(sun, this, ellipses, settlers);


        Asteroid a1 = new Asteroid(new Point2D(1,4), field.GetEllipses().get(0),5, new Iron());
        Asteroid a2 = new Asteroid(new Point2D(4,4), field.GetEllipses().get(0),0, null);
        Asteroid a3 = new Asteroid(new Point2D(-1,-6), field.GetEllipses().get(0),4, new Ice());
        Asteroid a4 = new Asteroid(new Point2D(10,5), field.GetEllipses().get(1),7, new Coal());
        Asteroid a5 = new Asteroid(new Point2D(3,7), field.GetEllipses().get(1),2, new Uran());
        Asteroid a6 = new Asteroid(new Point2D(9,-9), field.GetEllipses().get(2),0, null);
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

    public void testInitGraphic(){
        Sun sun = new Sun(new Point2D(0,0), null, view);
        ArrayList<Settler> settlers = new ArrayList<>();
        ArrayList<Ellipse2D> ellipses = new ArrayList<>();
        this.field = new AsteroidField(sun, this, ellipses, settlers);
        sun.SetField(this.field);
        ArrayList<OrbitingObject> objects1 = new ArrayList<>();
        ArrayList<OrbitingObject> objects2 = new ArrayList<>();
        ArrayList<OrbitingObject> objects3 = new ArrayList<>();

        ellipses.add(new Ellipse2D(900, 400, 5, 0.04, objects1, view));
        ellipses.add(new Ellipse2D(800, 300, 10, -0.04, objects2, view));
        ellipses.add(new Ellipse2D(700, 200, 15, 0.02, objects3, view));

        Asteroid a1=new Asteroid(new Point2D(1,4), field.GetEllipses().get(0),3, new Iron(), view);
        Asteroid a2=new Asteroid(new Point2D(4,4), field.GetEllipses().get(0),0, null, view);
        Asteroid a3=new Asteroid(new Point2D(-1,-6), field.GetEllipses().get(0),3, new Ice(), view);
        Asteroid a4=new Asteroid(new Point2D(10,5), field.GetEllipses().get(1),1, new Coal(), view);
        Asteroid a5=new Asteroid(new Point2D(3,7), field.GetEllipses().get(1),2, new Uran(), view);
        Asteroid a6=new Asteroid(new Point2D(9,-9), field.GetEllipses().get(2),0, null, view);

        a2.GetNeighbors().add(a1);
        a1.GetNeighbors().add(a2);
        a1.GetNeighbors().add(a3);
        a3.GetNeighbors().add(a1);
        a3.GetNeighbors().add(a4);
        a4.GetNeighbors().add(a3);
        a1.GetNeighbors().add(a5);
        a5.GetNeighbors().add(a1);

        TeleportGate tg1=new TeleportGate(new Point2D(3,3), field.GetEllipses().get(0), view);
        TeleportGate tg2=new TeleportGate(new Point2D(5,2), field.GetEllipses().get(2), view);
        tg1.SetGatePair(tg2);
        tg2.SetGatePair(tg1);
        tg1.GetNeighbors().add(a5);
        a5.GetNeighbors().add(tg1);
        TeleportGate tg3=new TeleportGate(new Point2D(6,6), field.GetEllipses().get(2), view);
        TeleportGate tg4=new TeleportGate(null, null);
        tg4.SetGatePair(tg3);
        tg3.SetGatePair(tg4);

        Robot r1=new Robot(a4, field, view);
        Ufo u1= new Ufo(a5, field, view);
        a4.SetCloseToSun(true);
        a4.AddWorker(r1);
        a5.AddWorker(u1);

        Settler s1=new Settler(a1, field, view);
        s1.GetBackpack().add(new Iron());
        Settler s2=new Settler(a3, field, view);
        s2.AddGate(tg4);
        this.base=new BaseAsteroid(new Point2D(3,3), field.GetEllipses().get(0), 0,this, view);
        field.GetSettlers().add(s1);
        field.GetSettlers().add(s2);
        field.GetSun().GetSolarStorms().add(new SolarStorm(field.GetSun(), 0, 2, view));

        field.SetActiveSettler(field.GetSettlers().get(0));
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
            ArrayList<String> chestItemNames = new ArrayList<>();


            for (Material material : base.GetChest())
            {
                chestItemNames.add(material.GetName());
            }
            chestJson.putAll(chestItemNames);
            baseAsteroidJson.put("chest", chestJson);


            // bazis szomszedai
            JSONArray baseNeighboursJson = new JSONArray();
            ArrayList<String> baseNeighboursNames = new ArrayList<>();


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
                    ArrayList<String> neighboursNames = new ArrayList<>();

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
                    ArrayList<String> neighboursNames = new ArrayList<>();

                    for (OrbitingObject neighbour : orbitingObject.GetNeighbors())
                    {
                        neighboursNames.add(neighbour.GetName());
                    }
                    neighboursJson.putAll(neighboursNames);

                    teleportGateJson.put("neighbours", neighboursJson);
                    teleportGateJson.put("active", ((TeleportGate) orbitingObject).IsActive());

                    teleportGateJson.put("closeToSun", orbitingObject.IsCloseToSun());
                    teleportGateJson.put("pair", ((TeleportGate)orbitingObject).GetPair().GetName());

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
                    ArrayList<String> inventoryNames = new ArrayList<>();
                    for (Material material : ((Settler) worker).GetBackpack()) {
                        inventoryNames.add(material.GetName());
                    }
                    inventoryJson.putAll(inventoryNames);
                    settlerJson.put("inventory", inventoryJson);

                    // teleportGate inventory
                    JSONArray teleportGateJson = new JSONArray();
                    ArrayList<String> teleportGateNames = new ArrayList<>();
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
                        ArrayList<String> inventoryNames = new ArrayList<>();
                        for (Material material : ((Settler) worker).GetBackpack()) {
                            inventoryNames.add(material.GetName());
                        }
                        inventoryJson.putAll(inventoryNames);
                        settlerJson.put("inventory", inventoryJson);

                        // teleportGate inventory
                        JSONArray teleportGateJson = new JSONArray();
                        ArrayList<String> teleportGateNames = new ArrayList<>();
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
        Logger.logOnConsole = false;
        Game game = new Game();
        view.setVisible(true);
        /*
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(140);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    game.GetField().Step();
                    view.repaint();
                }
            }
        });
        t.run();
         */
    }
}
