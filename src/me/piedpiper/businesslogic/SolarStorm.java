package me.piedpiper.businesslogic;

import javafx.geometry.Point3D;
import me.piedpiper.gui.SolarStormPanel;
import me.piedpiper.gui.View;

import java.util.ArrayList;

import static java.lang.StrictMath.PI;

public class SolarStorm implements ISteppable{

    /**
     * A nap, ami a napkitörést okozta
     */
    private final Sun sun;
    /**
     * A szög, amilyen irányban a napkitörésnek hatása lesz
     */
    private final double angle;
    /**
     * Az emlékeztető körök száma
     */
    private int warnTimer;
    /**
     * inde
     */
    private static int currentIndex = 0;
    private final int id;
    /**
     * celpontokat tartalmazo lista
     */
    private final ArrayList<OrbitingObject> targets;
    /**
     * A kirajzolasert felelos peldany
     */
    private View view;
    /**
     * A kirajzolhato objektum, ami napvihart rajzol ki
     */
    private SolarStormPanel panel;

    /**
     * A napkitörés konstruktora
     */
    public SolarStorm(Sun sun, double angle, int warnTimer) {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * tagvaltozok beallitasa
         */
        this.sun = sun;
        this.angle = angle;
        this.warnTimer = warnTimer;
        this.targets = new ArrayList<>();
        this.id = ++currentIndex;

        Logger.tabcount--;
    }

    /**
     * Masodik konstruktor, melyben az elozohoz kepest annzi a valtozas, hogy letrehoz egy SolarStromPanel-t
     * amit atad a view-nak
     */
    public SolarStorm(Sun sun, double angle, int warnTimer,View view) {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        /**
         * tagvaltozok beallitasa
         */
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

    /**
     * A napkitörést vezérlő kontroller által végrehajtott lépés
     */
    @Override
    public void Step() {
        Logger.logMessage("SolarStorm#" + Integer.toHexString(this.hashCode()) + ".Step()");
        /**
         * Megöli a sérthető telepeseket és tönkreteszi a robotokat, ha lejárt a figyelmeztető idő
         */
        if (warnTimer > 0) {
            warnTimer--;
        } else if (warnTimer == 0) {
            for (Ellipse2D e : sun.GetField().GetEllipses()) {
                for (OrbitingObject o : e.GetObjects()) {

                    Point2D p = new Point2D(o.GetEllipse().GetA()/2 * Math.cos(o.GetT()), o.GetEllipse().GetB()/2 * Math.sin(o.GetT()));
                    Point2D stormStart=new Point2D(-450 * Math.cos(angle/180*PI), -200 * Math.sin(angle/180*PI));
                    Point2D stormEnd=new Point2D(-450 * Math.cos((angle+30)/180*PI), -200 * Math.sin((angle+30)/180*PI));

                    Point3D normal=Point2D.Cross(stormEnd, stormStart);
                    if(Point2D.Cross(stormStart, p).dotProduct(normal) > 0 &&
                            Point2D.Cross(new Point2D( -stormEnd.GetX(), -stormEnd.GetY()), new Point2D(p.GetX()-stormEnd.GetX(),p.GetY()-stormEnd.GetY())).dotProduct(normal) > 0
                    ){
                        o.UnderSolarStorm();
                    }

                }

            }
            /**
             * eltavolitja a napvihart kirajzolo objektumot, ha mar vege van
             */
            view.RemoveGraphicObject(panel);

            Logger.tabcount--;
        }
    }

    /**
     * A fuggveny visszadja a napvihar nevet
     */
    public String GetName(){
        return "SolarStorm"+id;
    }

    /**
     * A fuggveny visszaadja a warnTimer erteket
     */
    public int GetWarnTimer(){
        return warnTimer;
    }

    /**
     * A fuggveny visszaadja az angle erteket
     */
    public double GetAngle(){
        return angle;
    }

    /**
     * A fuggveny hozzaad egy OrbitingObject-et a targets listahoz
     */
    public void AddTarget(OrbitingObject o) {
        targets.add(o);
    }

    /**
     * A fuggveny reseteli az indexet
     */
    public static void ResetIndex(){
        currentIndex=0;
    }
}
