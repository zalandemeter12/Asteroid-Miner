package me.piedpiper.businesslogic;

import java.util.ArrayList;

public class Settler extends Worker {
    private final AsteroidField field;
    private boolean canStep;
    private ArrayList<Material> backpack;
    private ArrayList<TeleportGate> gateInventory;

    public Settler(OrbitingObject location, AsteroidField field){
        super(location);
        this.field = field;
        this.canStep = false;
        this.backpack = new ArrayList<>();
        this.gateInventory = new ArrayList<>();
        System.out.println("Settler.Ctor()");
    }

    public void Mine() { System.out.println("Settler.Mine()"); }

    public void  PlaceMaterial(Material m){
        System.out.println("Settler.PlaceMaterial()");
        location.AddMaterial(m);
    }

    public void  PlaceGate(TeleportGate t){
        System.out.println("Settler.PlaceGate()");
    }

    public void  CraftGate(){
        System.out.println("Settler.CraftGate()");
    }

    public void  BuildRobot(){
        System.out.println("Settler.BuildRobot()");
    }

    @Override
    public void Explode(){
        System.out.println("Settler.Explode()");
    }

    public void SkipAction(){
        System.out.println("Settler.SkipAction()");
    }

    @Override
    public void Die(){
        System.out.println("Settler.Die()");
    }

    public ArrayList<Material> GetBackpack(){
        return backpack;
    }
}
