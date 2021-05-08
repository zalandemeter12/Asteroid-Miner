package me.piedpiper.businesslogic;

import me.piedpiper.gui.BaseAsteroidPanel;
import me.piedpiper.gui.View;

import java.util.ArrayList;

/**
 * Bazisaszteroidat valositja meg
 */
public class BaseAsteroid extends Asteroid {
    /**
     * Osszegyujtott anyagokat tartalmazo lista
     */
    private final ArrayList<Material> chest;
    /**
     * Jatekmenentet kezelo objektum referenciaja
     */
    private final Game game;
    /**
     * A kirajzolasert felelos peldany
     */
    private View view;
    /**
     * A kirajzolhato objektum, ami bazisaszteroidat rajzol ki
     */
    private BaseAsteroidPanel panel;

    /**
     * Konstruktor
     */
    public BaseAsteroid(Point2D position, Ellipse2D ellipse, int thickness, Game game) {
        super(position, ellipse, thickness, null);
        Logger.logMessage("BaseAsteroid#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * tagvaltozok beallitasa
         */
        chest=new ArrayList<>();
        this.game = game;
        this.thickness = 0;

        Logger.tabcount--;
    }

    /**
     * Masodik konstruktor, melyben az elozohoz kepest annzi a valtozas, hogy letrehoz egy BaseAsteroidPanel-t
     * amit atad a view-nak
     */
    public BaseAsteroid(Point2D position, Ellipse2D ellipse, int thickness, Game game,View view) {
        super(position, ellipse, thickness, null);
        Logger.logMessage("BaseAsteroid#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * tagvaltozok beallitasa
         */
        chest=new ArrayList<>();
        this.game = game;
        this.thickness = 0;
        this.view = view;
        panel=new BaseAsteroidPanel(this);
        view.AddGraphicObject(panel);
        Logger.tabcount--;
    }

    /**
     *  Anyag belerakasa a chestbe
     */
    @Override
    public boolean AddMaterial(Material m) {
        Logger.logMessage("BaseAsteroid#" + Integer.toHexString(this.hashCode()) + ".AddMaterial()");

        /**
         * ellenorzi, hogy a nyersanyagbol hozza lehet-e meg adni a bazisaszteroida chestjehez
         */
        ArrayList<Material> materials = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            materials.add(new Uran(true));
            materials.add(new Coal(true));
            materials.add(new Ice(true));
            materials.add(new Iron(true));
        }
        BillOfMaterials bill = new BillOfMaterials(materials);
        for (Material material : chest) {
            bill.IsNeeded(material);
        }
        /**
         * Ha meg elfer a ladaban, akkor hozzaadja, es megvizsgalja, hogy minden nyersanyag megvan e a jatek befejezesehez
         */
        if (bill.IsNeeded(m)) {
            chest.add(m);
            if(bill.GetBill().size() == 0)
                game.EndGame(true);


            Logger.tabcount--;
            return true;
        } else {
            Logger.tabcount--;
            return false;
        }
    }

    /**
     * A mar osszegyujtott anyagok listajaval ter vissza
     */
    @Override
    public ArrayList<Material> GetChest() {
        Logger.logMessage("BaseAsteroid#" + Integer.toHexString(this.hashCode()) + ".GetChest()");
        Logger.tabcount--;
        return chest;
    }

    /**
     * A fuggveny visszater a bazisaszteroidaa nevevel
     */
    public String GetName(){
        return "BaseAsteroid";
    }

    public int GetNumberOfUrans(){
        return 0;
    }
    public int GetNumberOfIce(){
        return 0;
    }
    public int GetNumberOfCoals(){
        return 0;
    }
    public int GetNumberOfIrons(){
        return 0;
    }


}
