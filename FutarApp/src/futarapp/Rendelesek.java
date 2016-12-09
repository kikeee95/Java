
package futarapp;

import java.util.ArrayList;

public class Rendelesek {

    private ArrayList<Rendeles> rendelesek;

    public Rendelesek() {
        this.rendelesek = new ArrayList<>();
    }

    public void addRendeles(Rendeles rendeles) {
        this.rendelesek.add(rendeles);
    }

    public void removeRendeles(Rendeles rendeles) {
        this.rendelesek.remove(rendeles);
    }

    public int getMeret() {
        return this.rendelesek.size();
    }

    public Rendeles getRendeles(int index) {
        return this.rendelesek.get(index);
    }

    @Override
    public String toString() {
        return "Rendelesek{" + "rendelesek=" + rendelesek + '}';
    }

}
