public class Iron extends Material {
    @Override
    public boolean IsCompatibleWith(Material m){
        System.out.println("Iron.IsCompatibleWith()");
        return false;
    }
}
