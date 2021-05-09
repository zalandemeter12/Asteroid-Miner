package me.piedpiper.gui;

import me.piedpiper.businesslogic.Settler;

public class Handlers {

    /**
     * A view, aminek a komponenseinek az eseményihez a függvényeket hozzárendeljük
     */
    private final View view;

    public Handlers(View view) {
        this.view = view;
    }

    /**
     * A Drill gomb lenyomására beregisztálandó függvény
     */
    public void DrillClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().DrillHole();
        view.repaint();
        view.Refresh();
    }

    /**
     * A Mine gomb lenyomására beregisztálandó függvény
     */
    public void MineClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().Mine();
        view.repaint();
        view.Refresh();
    }
    /**
     * A Move gomb lenyomására beregisztálandó függvény
     */
    public void MoveClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null || view.GetSelectedObject() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().MoveTo(view.GetSelectedObject());
        view.repaint();
        view.Refresh();
    }

    /**
     * A PlaceMaterial gomb lenyomására beregisztálandó függvény
     */
    public void PlaceMaterialClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }

        Settler active = view.GetGame().GetField().GetActiveSettler();
        if (active.GetBackpack().size() != 0) {
            active.PlaceMaterial(active.GetBackpack().get(0));
        }
        view.repaint();
        view.Refresh();
    }

    /**
     * A PlaceGate gomb lenyomására beregisztálandó függvény
     */
    public void PlaceGateClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().PlaceGate();
        view.repaint();
        view.Refresh();
    }

    /**
     * A Skip gomb lenyomására beregisztálandó függvény
     */
    public void SkipClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().SkipAction();
        view.repaint();
        view.Refresh();
    }

    /**
     * Egy RobotPanel lenyomására beregisztálandó függvény
     */
    public void BuildRobotClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().BuildRobot();

        view.repaint();
        view.Refresh();
    }

    /**
     * A BuildGate gomb lenyomására beregisztálandó függvény
     */
    public void BuildGateClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().CraftGate();

        view.repaint();
        view.Refresh();
    }

    /**
     * A New Game gomb lenyomására beregisztálandó függvény
     */
    public void NewClicked(){
        view.GetGame().NewGame();
    }
}
