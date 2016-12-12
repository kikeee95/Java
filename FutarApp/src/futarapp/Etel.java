/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futarapp;

import java.util.ArrayList;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Etel {

    private String nev;
    private ArrayList<Hozzavalo> hozzavalok;
    private int ar;
    private Afak afa;

    public Etel(String nev) {
        this.nev = nev;
        this.hozzavalok = new ArrayList<>();
        this.afa = Afak.Normal;
    }

    private void arKalk() {
        double osszeg = 0;
        for (Hozzavalo hozzavalo : hozzavalok) {
            osszeg += (hozzavalo.getEgysegAr()) * (hozzavalo.getAfa().getAfa());
        }
        osszeg = osszeg *this.afa.getAfa();
        this.ar = (int)osszeg;
    }
    
    public void addHozzavalo(Hozzavalo hozzavalo){
        hozzavalok.add(hozzavalo);
        arKalk();
    }
    
    public void removeHozzavalo(int index){
        this.hozzavalok.remove(index);
        arKalk();
    }
    
    public void getHozzavalo(int index){
        this.hozzavalok.get(index);
    }

    public ArrayList<Hozzavalo> getHozzavalok() {
        return hozzavalok;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }



    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("");
        sb.append("Név:  " + this.nev);
        sb.append("  Összetevõk:   ");
        for (Hozzavalo hozzavalo : hozzavalok) {
            sb.append(hozzavalo.getNev()+"  ");
        }
        sb.append("Ár:  "+ar);
        return sb.toString();
    }

}
