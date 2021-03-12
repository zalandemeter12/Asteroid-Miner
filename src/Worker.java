public abstract class Worker {

    protected OrbitingObject location;

    public void MoveTo(OrbitingObject o){

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
