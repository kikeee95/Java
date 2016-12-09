package futarapp;

import java.util.ArrayList;

public class Benzinkutak {

    private ArrayList<Benzinkut> benzinkutak;

    public Benzinkutak() {
        this.benzinkutak = new ArrayList<>();
    }

    public void addBenzinkut(Benzinkut benzinkut) {
        this.benzinkutak.add(benzinkut);
    }

    public void removeBenzinkut(int index) {
        this.benzinkutak.remove(index);
    }

    public Benzinkut getBenzinkut(int i) {
        return this.benzinkutak.get(i);
    }

    public Benzinkut getBenzinkutByHazSzam(int hazszam) {
        for (Benzinkut benzinkut : benzinkutak) {
            if (benzinkut.getHazszam() == hazszam) {
                return benzinkut;
            }
        }
        return null;
    }

    public int getMeret() {
        return this.benzinkutak.size();
    }

}
