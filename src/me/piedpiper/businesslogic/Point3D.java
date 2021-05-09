package me.piedpiper.businesslogic;

public class Point3D {
    private double x;
    private double y;
    private double z;

    public Point3D(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public double getY(){
        return y;
    }

    public double getX(){
        return x;
    }

    public double getZ(){
        return z;
    }

    public double dotProduct(Point3D p){
        return x*p.getX()+y*p.getY()+z*p.getZ();
    }
}
