package me.piedpiper.businesslogic;

public class Uran extends RadioactiveMaterial {
    private int blowUpCounter=0;
    private static int currentIndex = 0;

    public Uran(){
        Logger.logMessage("Uran#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        this.id = ++currentIndex;

        Logger.tabcount--;
    }
    public Uran(boolean temp){
        Logger.logMessage("Uran#" + Integer.toHexString(this.hashCode()) + ".Ctor()");

        if (temp)
            this.id=-1;
        else
            this.id=++currentIndex;

        Logger.tabcount--;
    }


    @Override
    public void BlowUp(OrbitingObject a, boolean mined){
        if(++blowUpCounter >= 3 || mined){
            a.Explode();
        }

    }

    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Uran#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        return m instanceof Uran;
    }

    public String GetName(){
        return "Uran" + id;
    }

    public static void ResetIndex(){
        currentIndex=0;
    }
}
