public class Point2D {
    private int x;
    private int y;

    public Point2D(int x, int y){
        this.x = x;
        this. y = y;
        System.out.println("Point2D.Constructor()");
    }

    public int GetX(){ return x;}

    public int GetY(){ return y;}

    public void SetX(int x){ this.x = x;}

    public void SetY(int y){ this.y = y;}

    public double DistanceFrom(Point2D p) {
        System.out.println("Point2D.DistanceFrom()");
        return Math.sqrt((x-p.GetX())*(x-p.GetX())+(y-p.GetY())*(y-p.GetY()));
    }


}
