package me.piedpiper.businesslogic;

public class Point2D {
    private double x;
    private double y;

    public Point2D(double x, double y){
        Logger.logMessage("Point2D#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.x = x;
        this. y = y;

        Logger.tabcount--;
    }

    public double GetX(){ 
        Logger.logMessage("Point2D#" + Integer.toHexString(this.hashCode()) + ".GetX()");
        Logger.tabcount--;
        return x;
    }

    public double GetY(){ 
        Logger.logMessage("Point2D#" + Integer.toHexString(this.hashCode()) + ".GetY()");
        Logger.tabcount--;
        return y;
    }

    public void SetX(int x){ 
        Logger.logMessage("Point2D#" + Integer.toHexString(this.hashCode()) + ".SetX()");
        
        this.x = x;
        
        Logger.tabcount--;
    }

    public void SetY(int y){ 
        Logger.logMessage("Point2D#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        
        this.y = y;
        
        Logger.tabcount--;
    }

    public double DistanceFrom(Point2D p) {
        Logger.logMessage("Point2D#" + Integer.toHexString(this.hashCode()) + ".DistanceFrom()");
        Logger.tabcount--;
        return Math.sqrt((x-p.GetX())*(x-p.GetX())+(y-p.GetY())*(y-p.GetY()));
    }


}
