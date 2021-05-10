package me.piedpiper.businesslogic;
/**
 * Segedosztaly 3D pontokkal valo szamolasra
 */
public class Point3D {
    /**
     * koordinatak
     */
    private double x;
    private double y;
    private double z;

    public Point3D(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    /**
     * Visszater y ertekevel
     */
    public double getY(){
        return y;
    }
    /**
     * Visszater x ertekevel
     */
    public double getX(){
        return x;
    }
    /**
     * Visszater z ertekevel
     */
    public double getZ(){
        return z;
    }
    /**
     * Skalaris szorzas
     */
    public double dotProduct(Point3D p){
        return x*p.getX()+y*p.getY()+z*p.getZ();
    }
}
