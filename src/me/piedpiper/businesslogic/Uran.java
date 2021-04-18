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



    public void BlowUp(Asteroid a){
        if(blowUpCounter==3){
            super.BlowUp(a);
        } else if(blowUpCounter<3){
            blowUpCounter++;
        }
    }

    @Override
    public boolean IsCompatibleWith(Material m){
        Logger.logMessage("Uran#" + Integer.toHexString(this.hashCode()) + ".IsCompatibleWith()");
        Logger.tabcount--;
        if (m instanceof Uran) return true;
        else return false;
    }

    public String GetName(){
        return "Uran" + id;
    }

    public static void ResetIndex(){
        currentIndex=0;
    }
}
