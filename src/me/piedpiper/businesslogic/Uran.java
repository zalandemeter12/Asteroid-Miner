package me.piedpiper.businesslogic;

public class Uran extends RadioactiveMaterial {
    private int blowUpCounter=0;

    public Uran(){
        Logger.logMessage("Uran#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        Logger.tabcount--;
    }

    public Uran(int i){
        Logger.logMessage("Uran#" + Integer.toHexString(this.hashCode()) + ".Ctor()");
        Logger.tabcount--;
        id=i;
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
}
