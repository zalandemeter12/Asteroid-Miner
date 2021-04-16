package me.piedpiper.businesslogic;

import java.util.ArrayList;

//A játékok összefogó objektum, tertalmazza és létrehozza a fő objektumokat
public class Game {
    //Az aszteroida mmező, ami az aszteroidákat tartalmazza
    private final AsteroidField field;
    //A bázis aszteroidát külön ismeri
    private final BaseAsteroid base;

    private int activeSettlerId;

    //Konstruktor
    public Game(int settlerCount) {
        Logger.logMessage("Game#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
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

        this.base = new BaseAsteroid(new Point2D(5,5), ellipses.get(1), 3, null,this);

        ArrayList<Settler> settlers = new ArrayList<>();
        this.field = new AsteroidField(sun, this, ellipses, settlers);
        for (int i = 0; i < settlerCount; ++i) {
            settlers.add(new Settler(base,field,i));
        }
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

    /*public void WriteJson(){
        String jsonString = new JSONObject()
                .put("JSON1", "Hello World!")
                .put("JSON2", "Hello my World!")
                .put("JSON3", new JSONObject().put("key1", "value1"))
                .toString();

    }*/

    //Belépési pont
    public static void main(String[] args){

    }
}
