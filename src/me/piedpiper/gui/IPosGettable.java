package me.piedpiper.gui;

import me.piedpiper.businesslogic.OrbitingObject;

import java.util.ArrayList;

public interface IPosGettable {
    int GetPosX();
    int GetPosY();
    ArrayList<OrbitingObject> GetNeighbours();
}
