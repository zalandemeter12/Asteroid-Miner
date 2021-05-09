package me.piedpiper.businesslogic;

import me.piedpiper.gui.SunPanel;
import me.piedpiper.gui.View;

import java.util.ArrayList;
import java.util.Random;

public class Sun implements ISteppable {

    /**
     * Nap pozicioja a koordinatarendszerben
     */
    private final Point2D position;

    /**
     * Az aktiv napviharok listaja
     */
    private final ArrayList<SolarStorm> solarStorms;

    /**
     * Megadja, hogy hany kor telt el a legutobbi napkiotores ota
     */
    private int roundsWithoutSS;

    /**
     *  Referencia az aszteroidamezore
     */
    private AsteroidField field;
    /**
     * A kirajzolasert felelos peldany
     */
    private View view;
    /**
     * A kirajzolhato objektum, ami napot rajzol ki
     */
    private SunPanel panel;

    /**
     * Komnstruktor
     */
    public Sun(Point2D position, AsteroidField field) {
        Logger.logMessage("Sun#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * tagvaltozok beallitasa
         */
        this.roundsWithoutSS = 0;
        this.position = position;
        this.field = field;
        this.solarStorms = new ArrayList<>();

        Logger.tabcount--;
    }

    /**
     * Masodik konstruktor, melyben az elozohoz kepest annzi a valtozas, hogy letrehoz egy SunPanel-t
     * amit atad a view-nak
     */
    public Sun(Point2D position, AsteroidField field,View view) {
        Logger.logMessage("Sun#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * tagvaltozok beallitasa
         */
        this.roundsWithoutSS = 0;
        this.position = position;
        this.field = field;
        this.solarStorms = new ArrayList<>();
        this.view = view;
        panel=new SunPanel(this);
        view.AddGraphicObject(panel);
        Logger.tabcount--;
    }

    /**
     * Leptetest megvalosito fuggveny
     */
    @Override
    public void Step() {
        Logger.logMessage("Sun#" + Integer.toHexString(this.hashCode()) + ".Step()");

        /**
         * veletlenszreruen hozzaad egy napvihart az aszteroida mezohoz
         */
        Random rand = new Random(); 
        if (rand.nextInt(25) < roundsWithoutSS) {
            solarStorms.add(new SolarStorm(this, rand.nextInt(359), 3, view));
            roundsWithoutSS=0;
        } else {
            roundsWithoutSS++;
        }
        /**
         * napviharok leptetese, amig veget nem er
         */
        ArrayList<SolarStorm> finishedStorms=new ArrayList<>();
        for (SolarStorm s : solarStorms) {
            if(s.GetWarnTimer()==0) {
                finishedStorms.add(s);
            }
            s.Step();

        }
        /**
         * vegetert napviharok eltavlitasa
         */
        for(SolarStorm s : finishedStorms){
            solarStorms.remove(s);
        }
        
        Logger.tabcount--;
    }

    /**
     *  Visszater az aktiv napviharokkal
     */
    public ArrayList<SolarStorm> GetSolarStorms() {
        Logger.logMessage("Sun#" + Integer.toHexString(this.hashCode()) + ".GetSolarStorms()");
        Logger.tabcount--;
        return solarStorms;
    }

    /**
     * Visszater az aszteroidamezo referenciajaval
     */
    public AsteroidField GetField() {
        Logger.logMessage("Sun#" + Integer.toHexString(this.hashCode()) + ".GetField()");
        Logger.tabcount--;
        return field;
    }

    /**
     *  Beallitja az aszteroidamezo referenciajat
     */
    public void SetField(AsteroidField field) {
        Logger.logMessage("Sun#" + Integer.toHexString(this.hashCode()) + ".SetField()");
        
        this.field = field;
        
        Logger.tabcount--;
    }

    /**
     * A fuggveny visszaadja a nap poziciojat
     */
    public Point2D GetPosition(){
        return position;
    }

    /**
     * A fuggveny hozzaad egy napvihart az oket tartalmazo listahoz
     */
    public void AddSolarStorm(SolarStorm s){
        solarStorms.add(s);
    }
}
