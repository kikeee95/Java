
package futarapp;

import java.util.Date;


public class Lako extends Ember{
        private int pizzaSzeretet;
        private boolean rendelesreVar;
    
    public Lako(String nev, Date szulDatum, Lakas lakohely, int pizzaSzeretet) {
        super(nev, szulDatum, lakohely);
        this.pizzaSzeretet = pizzaSzeretet;
        this.rendelesreVar = false;
    }

    public int getPizzaSzeretet() {
        return pizzaSzeretet;
    }

    public void setPizzaSzeretet(int pizzaSzeretet) {
        this.pizzaSzeretet = pizzaSzeretet;
    }

    public boolean isRendelesreVar() {
        return rendelesreVar;
    }

    public void setRendelesreVar(boolean rendelesreVar) {
        this.rendelesreVar = rendelesreVar;
    }
    
    
    
    
}
