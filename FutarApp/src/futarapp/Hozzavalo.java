/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futarapp;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Hozzavalo {

    private String nev;
    private int egysegAr;
    private Afak afa;
    private int db;

    public Hozzavalo(String nev, int egysegAr, int afa) throws Exception {
        this.nev = nev;
        this.egysegAr = egysegAr;
        if (afa == 1) {
            this.afa = Afak.KiemeltKevezmenyes;
        } else if (afa == 2) {
            this.afa = Afak.Kedvezmenyes;
        } else if (afa == 3) {
            this.afa = Afak.Normal;
        } else {
            throw new Exception("Hibás áfatípus!");
        }
    }

    public Hozzavalo(String nev, int egysegAr, Afak afa) {
        this.nev = nev;
        this.egysegAr = egysegAr;
        this.afa = afa;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getEgysegAr() {
        return egysegAr;
    }

    public void setEgysegAr(int egysegAr) {
        this.egysegAr = egysegAr;
    }

    public Afak getAfa() {
        return afa;
    }

    public void setAfa(Afak afa) {
        this.afa = afa;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    @Override
    public String toString() {
        return "   " + this.nev + " \t\t\t" + this.db;
    }

}
