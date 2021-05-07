package me.piedpiper.gui;

import me.piedpiper.businesslogic.Settler;

public class Handlers {
    private final View view;

    public Handlers(View view) {
        this.view = view;
    }

    public void DrillClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().DrillHole();
        view.repaint();
        view.Refresh();
    }

    public void MineClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().Mine();
        view.repaint();
        view.Refresh();
    }

    public void MoveClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null || view.GetSelectedObject() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().MoveTo(view.GetSelectedObject());
        view.repaint();
        view.Refresh();
    }

    public void PlaceMaterialClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        //TODO modal window a kiválasztáshoz

        Settler active = view.GetGame().GetField().GetActiveSettler();
        if (active.GetBackpack().size() != 0) {
            active.PlaceMaterial(active.GetBackpack().get(0));
        }
        view.repaint();
        view.Refresh();
    }

    public void PlaceGateClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().PlaceGate();
        view.repaint();
        view.Refresh();
    }

    public void SkipClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().SkipAction();
        view.repaint();
        view.Refresh();
    }
    public void BuildRobotClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().BuildRobot();

        view.repaint();
        view.Refresh();
    }
    public void BuildGateClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().CraftGate();

        view.repaint();
        view.Refresh();
    }
    public void NewClicked(){

        view.GetGame().NewGame();
        //view.repaint();
    }
}
