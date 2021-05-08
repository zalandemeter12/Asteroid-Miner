package me.piedpiper.businesslogic;

import me.piedpiper.gui.AsteroidPanel;
import me.piedpiper.gui.SolarStormPanel;
import me.piedpiper.gui.View;

import java.util.ArrayList;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.tan;

public class SolarStorm implements ISteppable{

    //A nap, ami a napkitörést okozta
    private final Sun sun;
    //A szög, amilyen irányban a napkitörésnek hatása lesz
    private final double angle;
    //Az emlékeztető körök száma
    private int warnTimer;
    //index
    private static int currentIndex = 0;
    private final int id;
    //celpontokat tartalmazo lista
    private ArrayList<OrbitingObject> targets;
    //A kirajzolasert felelos peldany
    private View view;
    //A kirajzolhato objektum, ami napvihart rajzol ki
    private SolarStormPanel panel;

    //A napkitörés konstruktora
    public SolarStorm(Sun sun, double angle, int warnTimer) {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        //tagvaltozok beallitasa
        this.sun = sun;
        this.angle = angle;
        this.warnTimer = warnTimer;
        this.targets = new ArrayList<>();
        this.id = ++currentIndex;

        Logger.tabcount--;
    }

    //Masodik konstruktor, melyben az elozohoz kepest annzi a valtozas, hogy letrehoz egy SolarStromPanel-t
    //amit atad a view-nak
    public SolarStorm(Sun sun, double angle, int warnTimer,View view) {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        //tagvaltozok beallitasa
        this.sun = sun;
        this.angle = angle;
        this.warnTimer = warnTimer;
        this.targets = new ArrayList<>();
        this.id = ++currentIndex;
        this.view = view;
        panel=new SolarStormPanel(this);
        view.AddGraphicObject(panel, 1);
        Logger.tabcount--;
    }

    //A napkitörést vezérlő kontroller által végrehajtott lépés
    @Override
    public void Step() {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Step()");
        //Megöli a sérthető telepeseket és tönkreteszi a robotokat, ha lejárt a figyelmeztető idő
        if (warnTimer > 0) {
            warnTimer--;
        } else if (warnTimer == 0) {
            for (Ellipse2D e : sun.GetField().GetEllipses()) {
                for (OrbitingObject o : e.GetObjects()) {
                    double bottomLine = Math.tan((angle + 0.000001) / 180 * PI); //y=bottomline*x
                    double topLine = Math.tan((angle + 30.000001) / 180 * PI); //y=topline*x
                    if ((angle >= 270 || angle <= 75 )
                            && (bottomLine * o.GetPosition().GetX()) < o.GetPosition().GetY()
                            && (topLine * o.GetPosition().GetX()) > o.GetPosition().GetY()) {
                        o.UnderSolarStorm();
                    } else if ((angle > 75 && angle < 90)
                            && (bottomLine * o.GetPosition().GetX()) < o.GetPosition().GetY()
                            && (topLine * o.GetPosition().GetX()) < o.GetPosition().GetY()) {
                        o.UnderSolarStorm();
                    } else if ((angle >= 90 && angle <= 255)
                            && (bottomLine * o.GetPosition().GetX()) > o.GetPosition().GetY()
                            && (topLine * o.GetPosition().GetX()) < o.GetPosition().GetY()) {
                        o.UnderSolarStorm();
                    } else if ((angle > 255 && angle < 270)
                            && (bottomLine * o.GetPosition().GetX()) > o.GetPosition().GetY()
                            && (topLine * o.GetPosition().GetX()) > o.GetPosition().GetY()) {
                        o.UnderSolarStorm();
                    }


                }
            /*for (OrbitingObject o : targets)
                o.UnderSolarStorm();*/

            }
            //eltavolitja a napvihart kirajzolo objektumot, ha mar vege van
            view.RemoveGraphicObject(panel);

            Logger.tabcount--;
        }
    }

    //A fuggveny visszadja a napvihar nevet
    public String GetName(){
        return "SolarStorm"+id;
    }

    //A fuggveny visszaadja a warnTimer erteket
    public int GetWarnTimer(){
        return warnTimer;
    }

    //A fuggveny visszaadja az angle erteket
    public double GetAngle(){
        return angle;
    }

    //A fuggveny hozzaad egy OrbitingObject-et a targets listahoz
    public void AddTarget(OrbitingObject o) {
        targets.add(o);
    }

    //A fuggveny reseteli az indexet
    public static void ResetIndex(){
        currentIndex=0;
    }
}
