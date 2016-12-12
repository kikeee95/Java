
package futarapp;

import java.util.Date;


public class Dolgozo extends Ember {
    String munkakor;
    int fizetes;
    

    public Dolgozo(String nev, Date szulDatum, Lakas lakohely, String munkakor, int fizetes) {
        super(nev, szulDatum, lakohely);
        this.munkakor = munkakor;
        this.fizetes = fizetes;
    }

    public String getMunkakor() {
        return munkakor;
    }

    public void setMunkakor(String munkakor) {
        this.munkakor = munkakor;
    }

    public int getFizetes() {
        return fizetes;
    }

    public void setFizetes(int fizetes) {
        this.fizetes = fizetes;
    }
    
    

}
