package futarapp;

import java.util.Date;


public abstract class Ember {
    private String nev;
    private Date szulDatum;
    private Lakas lakohely;
    
    public Ember(String nev, Date szulDatum, Lakas lakohely){
        this.lakohely = lakohely;
        this.szulDatum = szulDatum;
        this.nev = nev;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public Date getSzulDatum() {
        return szulDatum;
    }

    public void setSzulDatum(Date szulDatum) {
        this.szulDatum = szulDatum;
    }

    public Lakas getLakohely() {
        return lakohely;
    }

    public void setLakohely(Lakas lakohely) {
        this.lakohely = lakohely;
    }

}
