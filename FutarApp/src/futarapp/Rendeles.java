
package futarapp;

import java.util.ArrayList;


public class Rendeles {
    private int hazSzam;
    private Lako lako;
    private ArrayList<Etel> etelek;
    private int id = 1;

    public Rendeles(int hazSzam, Lako lako) {
        this.hazSzam = hazSzam;
        this.lako = lako;
        this.etelek = new ArrayList<>();
        id++;
    }
    
    public void addEtel(Etel etel){
        this.etelek.add(etel);
    }
    
    public void removeEtel(Etel etel){
        this.etelek.remove(etel);
     }
    
    public Etel getEtel(int index){
        return this.etelek.get(index);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHazSzam() {
        return hazSzam;
    }

    public void setHazSzam(int hazSzam) {
        this.hazSzam = hazSzam;
    }

    public Lako getLako() {
        return lako;
    }

    public void setLako(Lako lako) {
        this.lako = lako;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("     HázSz.: " + hazSzam + "     Név: " + lako.getNev()+"\n     Ételek: ");
        for (Etel etel : etelek) {
            sb.append("     "+etel.getNev()+"\t");
            sb.append("\n----------------------------------------------------------\n\n");
        }
        return sb.toString();
    }
    
    

}
