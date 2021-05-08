package me.piedpiper.businesslogic;

import me.piedpiper.gui.UfoPanel;
import me.piedpiper.gui.View;

import java.util.Random;

public class Ufo extends Worker implements ISteppable{
    /**
     * Az aszteroida mező amiben a robot aktuálisan van
     */
    private final AsteroidField field;
    /**
     * index
     */
    private static int currentIndex = 0;
    /**
     * A kirajzolasert felelos peldany
     */
    private View view;
    /**
     * A kirajzolhato objektum, ami ufot rajzol ki
     */
    private UfoPanel panel;

    /**
     * A ufo konstruktora
     */
    public Ufo(OrbitingObject location, AsteroidField f) {
        super(location);
        /**
         * tagvaltozok beallitasa
         */
        this.field=f;
        this.id = ++currentIndex;
    }

    /**
     * Masodik konstruktor, melyben az elozohoz kepest annzi a valtozas, hogy letrehoz egy UfoPanel-t
     * amit atad a view-nak
     */
    public Ufo(OrbitingObject location, AsteroidField f,View view) {
        super(location);
        /**
         * tagvaltozok beallitasa
         */
        this.field=f;
        this.id = ++currentIndex;
        this.view = view;
        panel=new UfoPanel(this);
        view.AddGraphicObject(panel);
    }

    /**
     * Az ufo "meghal"
     */
    @Override
    public void Die() {
        dead=true;
        view.RemoveGraphicObject(panel);
    }

    /**
     * Az ufo felrobban, meghal
     */
    @Override
    public void Explode() {
        Die();
    }

    /**
     * Az ufo minden korben lep egyet
     */
    @Override
    public void Step() {
        /**
         * Ha nem halott akkor megprobal banyaszni, vagy lep egyet
         */
        if(!dead) {
            /**
             * Ha tud banyaszni, akkor banyaszik
             */
            if (location.GetThickness() == 0 && location.GetMaterial() != null) {
                Mine();
            } else if (location.GetNeighbors().size() > 0) {
                /**
                 * h nem tud, akkor egy random szomszedos aszteroidara lep
                 */
                if (field.IsRandom() && location.GetNeighbors().size() > 1) {
                    Random rand = new Random();
                    int idx = rand.nextInt(location.GetNeighbors().size() - 1);
                    MoveTo(location.GetNeighbors().get(idx));
                } else {
                    MoveTo(location.GetNeighbors().get(0));
                }
            }
        }
    }

    /**
     * furas
     */
    @Override
    public void DrillHole(){
    }

    /**
     * Banyaszas, a fuggveny eltavolitja a nyersanyagot az aszteroidarol, amin az ufo tartozkodik
     */
    @Override
    public void Mine(){
        if(location.GetThickness()==0){
            location.RemoveMaterial();
        }
    }

    /**
     * A fuggveny visszadja az ufo nevet
     */
    public String GetName(){
        return "Ufo"+id;
    }

    /**
     * A fuggveny reseteli az indexet
     */
    public static void ResetIndex(){
        currentIndex=0;
    }

}
