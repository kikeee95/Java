package futarapp;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Pizzeria extends Epulet {

    private EpuletTipus tipus;
    private Rendelesek elkeszitesreVaro;
    private Rendelesek szallitasraKesz;
    private Rendelesek szallitasAlatt;
    private Rendelesek keszitesAlatt;
    private int kiszallitottPizzak;
    private int osszBevetel;
    private ArrayList<Jarmu> jarmuvek;
    private ArrayList<Dolgozo> dolgozok;
    private ArrayList<Etel> menu;
    private int kapacitas;

    public Pizzeria() {
        this.tipus = EpuletTipus.Pizzeria;
        image = new ImageIcon("pizzeria.jpg");
        this.kiszallitottPizzak = 0;
        this.osszBevetel = 0;
        this.jarmuvek = new ArrayList<>();
        this.dolgozok = new ArrayList<>();
        this.menu = new ArrayList<>();
        this.szallitasraKesz = new Rendelesek();
        this.szallitasAlatt = new Rendelesek();
        this.elkeszitesreVaro = new Rendelesek();
        this.keszitesAlatt = new Rendelesek();
        this.kapacitas = 7;
    }

    public ArrayList<Etel> getMenu() {
        return menu;
    }

    public void keszitEtel() {

        try {
            sutobeTesz();
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pizzeria.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < this.keszitesAlatt.getMeret(); i++) {
            try {
                Rendeles ertek = this.keszitesAlatt.getRendeles(i);
                this.szallitasraKesz.addRendeles(ertek);
                keszitesAlatt.removeRendeles(ertek);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pizzeria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sutobeTesz() {

        for (int i = 0; i < elkeszitesreVaro.getMeret(); i++) {
            if(keszitesAlatt.getMeret()< this.kapacitas){
            Rendeles ertek = this.getElkeszitesreVaro().getRendeles(i);
            this.getKeszitesAlatt().addRendeles(ertek);
            elkeszitesreVaro.removeRendeles(ertek);
            }
        }
    }

    public void addEtel(Etel etel) {
        this.menu.add(etel);
    }

    public Etel getEtel(int index) {
        return this.menu.get(index);
    }

    public void removeEtel(int index) {
        this.menu.remove(index);
    }

    public void getEgeszMenu() {
        int db = 1;
        for (Etel etel : menu) {
            System.out.println(db + "  " + etel);
            db++;
        }
    }

    public void addJarmu(Jarmu jarmu) {
        this.jarmuvek.add(jarmu);
    }

    public void removeJarmu(int index) {
        this.jarmuvek.remove(index);
    }

    public Jarmu getJarmu(int index) {
        return this.jarmuvek.get(index);
    }

    public void addDolgozo(Dolgozo dolgozo) {
        this.dolgozok.add(dolgozo);
    }

    public void removeDolgozo(int index) {
        this.dolgozok.remove(index);
    }

    public Dolgozo getDolgozo(int index) {
        return this.dolgozok.get(index);
    }

    public Rendelesek getKeszitesAlatt() {
        return keszitesAlatt;
    }

    public Rendelesek getElkeszitesreVaro() {
        return elkeszitesreVaro;
    }

    public Rendelesek getSzallitasraKesz() {
        return szallitasraKesz;
    }

    public Rendelesek getSzallitasAlatt() {
        return szallitasAlatt;
    }

    public int getKiszallitottPizzak() {
        return kiszallitottPizzak;
    }

    public void setKiszallitottPizzak(int kiszallitottPizzak) {
        this.kiszallitottPizzak = kiszallitottPizzak;
    }

    public int getOsszBevetel() {
        return osszBevetel;
    }

    public void setOsszBevetel(int osszBevetel) {
        this.osszBevetel = osszBevetel;
    }

    @Override
    public int getPoz() {
        return poz;
    }

    @Override
    public int getHazszam() {
        return hazszam;
    }

    @Override
    public EpuletTipus getTipus() {
        return this.tipus;
    }

    @Override
    public ImageIcon getImage() {
        return this.image;
    }

    @Override
    public void setPoz(int poz) {
        this.poz = poz;
    }

    @Override
    public void setHazszam(int hazSzam) {
        this.hazszam = hazSzam;
    }

}
