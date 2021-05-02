package me.piedpiper.gui;

import javax.swing.*;

public class Handlers {
    private View view;

    public Handlers(View view) {
        this.view = view;
    }

    public void DrillClicked(){
        view.GetGame().GetActiveSettler().Drill();
    }

    public void MineClicked(){

    }

    public void MoveClicked(){

    }

    public void PlaceMaterialClicked(){

    }

    public void PlaceGatClicked(){

    }

    public void SkipClicked(){

    }

    public void OrbitingObjectClicked(JPanel o){

    }

    public void NewClicked(){

    }
}
