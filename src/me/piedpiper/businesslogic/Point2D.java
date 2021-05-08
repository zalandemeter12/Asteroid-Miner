package me.piedpiper.businesslogic;

public class Point2D {
    //x koordinata
    private double x;
    //y koordinata
    private double y;

    //Konstruktor
    public Point2D(double x, double y){
        Logger.logMessage("Point2D#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.x = x;
        this. y = y;

        Logger.tabcount--;
    }

    //A fuggveny visszaadja az x attributumot
    public double GetX(){
        Logger.logMessage("Point2D#" + Integer.toHexString(this.hashCode()) + ".GetX()");
        Logger.tabcount--;
        return x;
    }

    //A fuggveny visszaadja az y attributumot
    public double GetY(){
        Logger.logMessage("Point2D#" + Integer.toHexString(this.hashCode()) + ".GetY()");
        Logger.tabcount--;
        return y;
    }

    //A fuggveny beallitja az x attributumot
    public void SetX(double x){
        Logger.logMessage("Point2D#" + Integer.toHexString(this.hashCode()) + ".SetX()");
        
        this.x = x;
        
        Logger.tabcount--;
    }

    //A fuggveny beallitja az y attributumot
    public void SetY(double y){
        Logger.logMessage("Point2D#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.y = y;
        
        Logger.tabcount--;
    }

    //A fuggveny megadja milyen tavol van a parameterkent kapott pont
    public double DistanceFrom(Point2D p) {
        Logger.logMessage("Point2D#" + Integer.toHexString(this.hashCode()) + ".DistanceFrom()");
        Logger.tabcount--;
        return Math.sqrt((x-p.GetX())*(x-p.GetX())+(y-p.GetY())*(y-p.GetY()));
    }
}
