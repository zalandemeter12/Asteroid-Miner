public class Ice extends Material {
    @Override
    public void BlowUp(Asteroid a) {
        System.out.println("Ice.BlowUp()");
    }

    @Override
    public boolean IsCompatibleWith(Material m){
        System.out.println("Ice.IsCompatibleWith()");
        return false;
    }
}
