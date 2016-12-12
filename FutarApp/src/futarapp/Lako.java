
package futarapp;

import java.util.Date;


public class Lako extends Ember{
        private int pizzaSzeretet;
    
    public Lako(String nev, Date szulDatum, Lakas lakohely, int pizzaSzeretet) {
        super(nev, szulDatum, lakohely);
        this.pizzaSzeretet = pizzaSzeretet;
    }

    public int getPizzaSzeretet() {
        return pizzaSzeretet;
    }

    public void setPizzaSzeretet(int pizzaSzeretet) {
        this.pizzaSzeretet = pizzaSzeretet;
    }
    
    
}
