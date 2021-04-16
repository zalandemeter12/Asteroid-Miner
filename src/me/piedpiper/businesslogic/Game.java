package me.piedpiper.businesslogic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//A játékok összefogó objektum, tertalmazza és létrehozza a fő objektumokat
public class Game {
    //Az aszteroida mmező, ami az aszteroidákat tartalmazza
    private final AsteroidField field;
    //A bázis aszteroidát külön ismeri
    private BaseAsteroid base;

    private int activeSettlerId;

    //Konstruktor
    public Game(int settlerCount) {
        Logger.logMessage("Game#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        Sun sun = new Sun(new Point2D(0.0,0.0),null);
        
        ArrayList<OrbitingObject> objects1 = new ArrayList<>();
        
        ArrayList<OrbitingObject> objects2 = new ArrayList<>();
        ArrayList<OrbitingObject> objects3 = new ArrayList<>();
        ArrayList<Ellipse2D> ellipses = new ArrayList<>();
        ellipses.add(new Ellipse2D(new Point2D(10,10), new Point2D(20,20), 5, 3, objects1, 1));
        ellipses.add(new Ellipse2D(new Point2D(15,15), new Point2D(25,25), 10, 3, objects2,2));
        ellipses.add(new Ellipse2D(new Point2D(20,20), new Point2D(30,30), 15, 3, objects3, 3));
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

        this.base = new BaseAsteroid(new Point2D(5,5), ellipses.get(1), 3, null,this);

        ArrayList<Settler> settlers = new ArrayList<>();
        this.field = new AsteroidField(sun, this, ellipses, settlers);
        for (int i = 0; i < settlerCount; ++i) {
            settlers.add(new Settler(base,field,i));
        }
        activeSettlerId = settlers.get(0).getId();
        sun.SetField(this.field);
        
        Logger.tabcount--;
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
        Logger.tabcount--;
    }

    //Játék befejezése
    public void EndGame(){
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
        jsonObject.put("ActiveSettler", "Settler" + activeSettlerId);

        // innentol a keringo objektumok felepitese
        JSONObject orbitingObjectsJson = new JSONObject();

        // bazis aszteroida
        JSONObject baseAsteroidJson = new JSONObject();
        OrbitingObject baseAsteroid = (OrbitingObject)base;
        baseAsteroidJson.put("xCoordinate", baseAsteroid.GetPosition().GetX());
        baseAsteroidJson.put("yCoordinate", baseAsteroid.GetPosition().GetY());
        baseAsteroidJson.put("thickness", baseAsteroid.GetThickness());

        // chest tartalma
        JSONArray chestJson = new JSONArray();
        ArrayList<String> chestItemNames = new ArrayList<String>();

        /*base.AddMaterial(new Ice(0));
        base.AddMaterial(new Uran(1));
        base.AddMaterial(new Iron(2));
        */

        for (Material material : base.GetChest())
        {
            chestItemNames.add(material.GetName());
        }
        chestJson.putAll(chestItemNames);
        baseAsteroidJson.put("chest", chestJson);


        // bazis szomszedai
        JSONArray baseNeighboursJson = new JSONArray();
        ArrayList<String> baseNeighboursNames = new ArrayList<String>();

        /*base.AddNeighbor(field.GetEllipses().get(0).GetObjects().get(0));
        base.AddNeighbor(field.GetEllipses().get(0).GetObjects().get(1));
         */

        for (OrbitingObject neighbour : base.GetNeighbors())
        {
            baseNeighboursNames.add(neighbour.GetName());
        }
        baseNeighboursJson.putAll(baseNeighboursNames);
        baseAsteroidJson.put("neighbours", baseNeighboursJson);

        orbitingObjectsJson.put("BaseAsteroid", baseAsteroidJson);

        // lista az aszteroidakrol
        JSONArray asteroidListJson = new JSONArray();


        //field.GetEllipses().get(0).GetObjects().get(0).AddNeighbor(field.GetEllipses().get(0).GetObjects().get(1));
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


        //field.GetEllipses().get(0).GetObjects().get(0).AddNeighbor(field.GetEllipses().get(0).GetObjects().get(1));
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

                    teleportGateJson.put("closeToSun", orbitingObject.IsCloseToSun());

                    JSONObject asteroidJsonWrapper = new JSONObject();
                    asteroidJsonWrapper.put(orbitingObject.GetName(), teleportGateJson);
                    asteroidListJson.put(asteroidJsonWrapper);
                }
            }
        }

        orbitingObjectsJson.put("Asteroids:", asteroidListJson);




        jsonObject.put("OrbitingObjects", orbitingObjectsJson);

        System.out.println(jsonObject.toString(4));
        /*'
            .put("ActiveSettler", "Hello World!")
            .put("JSON2", "Hello my World!")
            .put("JSON3", new JSONObject().put("key1", "value1")

        */

    }

    //Belépési pont
    public static void main(String[] args){
        Game game = new Game(5);
        game.WriteJson();

    }
}
