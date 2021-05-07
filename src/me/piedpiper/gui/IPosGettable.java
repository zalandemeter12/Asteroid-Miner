package me.piedpiper.gui;

import me.piedpiper.businesslogic.OrbitingObject;

import java.util.ArrayList;

public interface IPosGettable {
    /**
     * Pozicio x koordinataja
     */

    int GetPosX();
    /**
     * Pozicio y koordinataja
     */

    int GetPosY();
    /**
     * Szomszedok listajaval ter vissza
     */

    ArrayList<OrbitingObject> GetNeighbours();
    /**
     * Megjelenites z "melysegi" koordinataja
     */

    int GetZOrder();
}
