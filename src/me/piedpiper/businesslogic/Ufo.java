package me.piedpiper.businesslogic;

import me.piedpiper.gui.UfoPanel;
import me.piedpiper.gui.View;

import java.util.Random;

public class Ufo extends Worker implements ISteppable{
    private final AsteroidField field;
    private static int currentIndex = 0;
    private View view;
    private UfoPanel panel;

    public Ufo(OrbitingObject location, AsteroidField f) {
        super(location);
        this.field=f;
        this.id = ++currentIndex;
    }

    public Ufo(OrbitingObject location, AsteroidField f,View view) {
        super(location);
        this.field=f;
        this.id = ++currentIndex;
        this.view = view;
        view.AddGraphicObject(panel);
    }

    @Override
    public void Die() {
        location.RemoveWorker(this);
        field.RemoveSteppable(this);
        view.RemoveGraphicObject(panel);
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
