package me.piedpiper.businesslogic;

import me.piedpiper.gui.View;

public abstract class RadioactiveMaterial extends Material {
    private View view;

    /**
     * A radioaktív anyag felrobban
     */
    @Override
    public void BlowUp(OrbitingObject o, boolean mined) {
        Logger.logMessage("RadioactiveMaterial#" + Integer.toHexString(this.hashCode()) + ".BlowUp()");

        /**
         * Megöli a telepeseket azon az aszteroidán amin helyezkedik
         */
        for (Worker w : o.GetWorkers()) {
            w.Explode();
        }
        o.GetEllipse().RemoveObject(o);
        o.RemoveMaterial();
        view.RemoveGraphicObject(((Asteroid)o).GetPanel());
        Logger.tabcount--;
    }
}
