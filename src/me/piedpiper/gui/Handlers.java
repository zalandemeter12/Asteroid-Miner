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
        view.GetGame().GetField().GetActiveSettler().DrillHole();
        view.repaint();
    }

    public void MineClicked(){
        view.GetGame().GetField().GetActiveSettler().Mine();
        view.repaint();
    }

    public void MoveClicked(){
        view.GetGame().GetField().GetActiveSettler().MoveTo(view.GetSelectedObject());
        view.repaint();
    }

    public void PlaceMaterialClicked(){

        //TODO modal window a kiválasztáshoz

        Settler active = view.GetGame().GetField().GetActiveSettler();
        if (active.GetBackpack().size() != 0) {
            active.PlaceMaterial(active.GetBackpack().get(0));
        }
        view.repaint();
    }

    public void PlaceGateClicked(){
        view.GetGame().GetField().GetActiveSettler().PlaceGate();
        view.repaint();
    }

    public void SkipClicked(){
        view.GetGame().GetField().GetActiveSettler().SkipAction();
        view.repaint();
    }

    public void OrbitingObjectClicked(OrbitingObject o){
        view.SetSelectedObject(o);
        view.repaint();
    }

    public void NewClicked(){
        view.SetGame(new Game());
        view.repaint();
    }
}
