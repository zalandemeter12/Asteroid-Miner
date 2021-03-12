package me.piedpiper.businesslogic;

public class Robot extends Worker implements ISteppable{
    private final AsteroidField field;

    public Robot(OrbitingObject location, AsteroidField field){
        super(location);
        this.field = field;
        System.out.println("Robot.Ctor()");
    }

    @Override
    public void Die(){
        System.out.println("Robot.Die()");
    }
    @Override
    public void Explode(){
        System.out.println("Robot.Explode()");
    }

    @Override
    public void Step(){
        System.out.println("Robot.Explode()");
    }
}
