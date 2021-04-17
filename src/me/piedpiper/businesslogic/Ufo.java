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
        } else if(location.GetNeighbors().size()!=0){
            Random random=new Random();
            MoveTo(location.GetNeighbors().get(random.nextInt()%location.GetNeighbors().size()));
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

}
