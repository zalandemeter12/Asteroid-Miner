public class Coal extends Material {
    @Override
    public boolean IsCompatibleWith(Material m){
        System.out.println("Coal.IsCompatibleWith()");
        return false;
    }
}
