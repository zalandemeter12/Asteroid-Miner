package me.piedpiper.businesslogic;

public class Asteroid extends OrbitingObject {
    private int thickness;
    private boolean closeToSun;
    private Material material;

    public Asteroid(Point2D position, Ellipse2D ellipse, int thickness, Material material) {
        super(position, ellipse);
        this.thickness = thickness;
        this.closeToSun = false;
        this.material = material;
        System.out.println("Asteroid.Ctor()");
    }

    @Override
    public void DrilledOn() {
        System.out.println("Asteroid.DrilledOn()");
    }

    @Override
    public void RemoveMaterial() {
        this.material = null;
        System.out.println("Asteroid.RemoveMaterial()");
    }

    @Override
    public boolean AddMaterial(Material m) {
        System.out.println("Asteroid.AddMaterial()");
        if (material == null) {
            this.material = m;
            return true;
        }
        else return false;
    }

    @Override
    public void Explode() {
        System.out.println("Asteroid.Explode()");
    }

    @Override
    public int GetThickness() {
        System.out.println("Asteroid.GetThickness()");
        return this.thickness;
    }
    
    @Override
    public void SetMaterial(Material m) {
        material = m;
    }

    @Override
    public Material GetMaterial() {
        return material;
    }
}
