package me.piedpiper.businesslogic;

public abstract class Worker {
    protected OrbitingObject location;

    public Worker(OrbitingObject location) {
        this.location = location;
        System.out.println("Worker.Ctor()");

    }

    public void MoveTo(OrbitingObject o){
        System.out.println("Worker.MoveTo()");
    }
    public void Die(){
        System.out.println("Worker.Die()");
    }
    public void DrillHole(){
        System.out.println("Worker.DrillHole()");
    }
    public void Explode(){
        System.out.println("Worker.Explode()");
    }

}
