package me.piedpiper.businesslogic;

public class Point2D {
    private double x;
    private double y;

    public Point2D(double x, double y){
        this.x = x;
        this. y = y;
        System.out.println("Point2D.Ctor()");
    }

    public double GetX(){ return x;}

    public double GetY(){ return y;}

    public void SetX(int x){ this.x = x;}

    public void SetY(int y){ this.y = y;}

    public double DistanceFrom(Point2D p) {
        System.out.println("Point2D.DistanceFrom()");
        return Math.sqrt((x-p.GetX())*(x-p.GetX())+(y-p.GetY())*(y-p.GetY()));
    }


}
