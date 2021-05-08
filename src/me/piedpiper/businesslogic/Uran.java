package me.piedpiper.businesslogic;

public class Uran extends RadioactiveMaterial {
    /**
     * szamlalo, hogy hanyszor volt napkozelben a nyersanyag
     */
    private int blowUpCounter=0;
    /**
     * index
     */
    private static int currentIndex = 0;

    /**
     *  Konstruktor
     */
    public Uran(){
        Logger.logMessage("Uran#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.id = ++currentIndex;

        Logger.tabcount--;
    }

    /**
     * Masodik konstruktor, indexet megfelelore allitja
     */
    public Uran(boolean temp){
        Logger.logMessage("Uran#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        if (temp)
            this.id=-1;
        else
            this.id=++currentIndex;

        Logger.tabcount--;
    }

    /**
     * Felrobbanast megvalosito metodus, a felrobbanto hivhatja
     */
    @Override
    public void BlowUp(OrbitingObject a, boolean mined){
        if(++blowUpCounter >= 3 || mined){
            a.Explode();
        }

    }

    /**
     * Polimorf viselkedes megvalositasa
     * megmondja, hogy a kapott anyag is uran-e
     */
    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Uran#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        if (m instanceof Uran) return true;
        else return false;
    }

    /**
     * A fuggveny visszaadja a nyersanyag nevet
     */
    public String GetName(){
        return "Uran" + id;
    }

    /**
     * A fuggveny reseteli az indexet
     */
    public static void ResetIndex(){
        currentIndex=0;
    }
}
