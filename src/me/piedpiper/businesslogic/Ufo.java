package me.piedpiper.businesslogic;

import java.util.Random;

public class Ufo extends Worker implements ISteppable{
    private final AsteroidField field;
    private static int currentIndex = 0;

    public Ufo(OrbitingObject location, AsteroidField f) {
        super(location);
        this.field=f;
        this.id = ++currentIndex;
    }

    @Override
    public void Die() {
        location.RemoveWorker(this);
        field.RemoveSteppable(this);
    }

    @Override
    public void Explode() {
        Die();
    }

    @Override
    public void Step() {
        if(location.GetThickness()==0){
            Mine();
        } else if(location.GetNeighbors().size()>0){
            if (field.IsRandom()) {
                Random rand = new Random();
                int idx = rand.nextInt(location.GetNeighbors().size()-1);
                MoveTo(location.GetNeighbors().get(idx));
            } else {
                MoveTo(location.GetNeighbors().get(0));
            }
        }
    }

    @Override
    public void DrillHole(){
    }

    @Override
    public void Mine(){
        if(location.GetThickness()==0){
            location.RemoveMaterial();
        }
    }

    public String GetName(){
        return "Ufo"+id;
    }

    public static void ResetIndex(){
        currentIndex=0;
    }

}
