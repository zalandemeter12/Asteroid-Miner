package me.piedpiper.businesslogic;

import java.util.ArrayList;
import java.util.Random;

public class Robot extends Worker implements ISteppable{
    //Az aszteroida mező amiben a robot aktuálisan van
    private final AsteroidField field;

    //A robot konstruktora
    public Robot(OrbitingObject location, AsteroidField field){
        super(location);
        Logger.logMessage("Robot#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.field = field;

        Logger.tabcount--;
    }

    public Robot(OrbitingObject location, AsteroidField field, int i){
        super(location);
        Logger.logMessage("Robot#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.field = field;
        id=i;
        Logger.tabcount--;
    }

    //A robot "meghal" - tönkremegy
    @Override
    public void Die(){
        Logger.logMessage("Robot#" + Integer.toHexString(this.hashCode()) + ".Die()");
        
        field.RemoveSteppable(this);
        
        Logger.tabcount--;
    }

    //A robot felrobban, de nem megy tönkre
    @Override
    public void Explode(){
        Logger.logMessage("Robot#" + Integer.toHexString(this.hashCode()) + ".Explode()");
        
        //És átrepül egy véletlenszerű szomszédos aszteroidára
        ArrayList<OrbitingObject> neighbours = location.GetNeighbors();
        if (neighbours.size() > 0) {
            Random rand = new Random();
            int idx = rand.nextInt(neighbours.size()-1);
            MoveTo(neighbours.get(idx));
        }
        System.out.println("Robot.Explode()");
        
        Logger.tabcount--;
    }

    @Override
    public String GetName() {
        return "Robot"+id;
    }

    //A robotot vezérlő AI egy lépésre készteti a robotot
    @Override
    public void Step(){
        Logger.logMessage("Robot#" + Integer.toHexString(this.hashCode()) + ".Step()");
        
        //Ha tud ásni ás, ha nem tud tovább megy egy szomszédos helyre
        if (location.GetThickness() > 0) {
            location.DrilledOn();
        } else if (location.GetNeighbors().size() > 0) {
            Random rand = new Random();
            int idx = rand.nextInt(location.GetNeighbors().size()-1);
            MoveTo(location.GetNeighbors().get(idx));
            location.GetWorkers().remove(this);
        }
        
        Logger.tabcount--;
    }
}
