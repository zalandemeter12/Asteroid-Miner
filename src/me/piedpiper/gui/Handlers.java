package me.piedpiper.gui;

import me.piedpiper.businesslogic.Game;
import me.piedpiper.businesslogic.OrbitingObject;
import me.piedpiper.businesslogic.Settler;

public class Handlers {
    private View view;

    public Handlers(View view) {
        this.view = view;
    }

    public void DrillClicked(){
        view.GetGame().GetField().Step();
        view.repaint();
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().DrillHole();
        view.repaint();
    }

    public void MineClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().Mine();
        view.repaint();
    }

    public void MoveClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null || view.GetSelectedObject() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().MoveTo(view.GetSelectedObject());
        view.repaint();
    }

    public void PlaceMaterialClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        //TODO modal window a kiválasztáshoz

        Settler active = view.GetGame().GetField().GetActiveSettler();
        if (active.GetBackpack().size() != 0) {
            active.PlaceMaterial(active.GetBackpack().get(0));
        }
        view.repaint();
    }

    public void PlaceGateClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().PlaceGate();
        view.repaint();
    }

    public void SkipClicked(){
        if (view.GetGame().GetField().GetActiveSettler() == null) { return; }
        view.GetGame().GetField().GetActiveSettler().SkipAction();
        view.repaint();
    }

    public void NewClicked(){
        view.SetGame(new Game());
        view.repaint();
    }
}
