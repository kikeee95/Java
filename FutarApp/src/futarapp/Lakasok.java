
package futarapp;

import java.util.ArrayList;

public class Lakasok {
    private ArrayList<Lakas> lakasok;
    

    public Lakasok() {
        this.lakasok = new ArrayList<>();
    }
    
    public void addLakas(Lakas lakas){
        this.lakasok.add(lakas);
    }
    
    public void removeLakas(int index){
        this.lakasok.remove(index);
    }
    
    public Lakas getLakas(int i){
        return this.lakasok.get(i);
    }
    
    public Lakas getLakasByHazSzam(int hazszam){
        for (Lakas lakas : lakasok) {
            if(lakas.getHazszam() == hazszam){
                return lakas;
            }
        }
        return null;
    }

    public int getMeret(){
       return this.lakasok.size();
    }
    
    
    
    
}
