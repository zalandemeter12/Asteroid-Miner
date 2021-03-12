public class Robot extends Worker implements ISteppable{

    private final AsteroidField field;

    public Robot(AsteroidField field){
        this.field = field;
    }

    @Override
    public void Die(){
        System.out.println("Robot.Die()");
    }
    @Override
    public void Explode(){
        System.out.println("Robot.Explode()");
    }

    //@Override
    public void Step(){
        System.out.println("Robot.Explode()");
    }


}
