package futarapp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Pizzeria extends Epulet {

    private EpuletTipus tipus;
    private Rendelesek elkeszitesreVaro;
    private Rendelesek szallitasraKesz;
    private Rendelesek szallitasAlatt;
    private Rendelesek keszitesAlatt;
    private int kiszallitottPizzak;
    private int osszBevetel;
    private ArrayList<Jarmu> futarok;
    private ArrayList<Dolgozo> dolgozok;
    private ArrayList<Etel> menu;
    private int kapacitas;
    private BufferedImage imageB;
    private String imageFile;
    private HashSet<Hozzavalo> keszlet;
    private Szallito szallito;

    public Pizzeria() {
        this.tipus = EpuletTipus.Pizzeria;
        this.imageFile = "pizza-icon.png";
        try {
            if (imageFile == null) {;
                imageB = ImageIO.read(new File(imageFile));
            } else {
                String ut = this.imageFile;
                imageB = ImageIO.read(new File(ut));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A(z) pizzéria létrehozása nem sikerült !", "Hiba",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        this.kiszallitottPizzak = 0;
        this.osszBevetel = 0;
        this.futarok = new ArrayList<>();
        this.dolgozok = new ArrayList<>();
        this.menu = new ArrayList<>();
        this.szallitasraKesz = new Rendelesek();
        this.szallitasAlatt = new Rendelesek();
        this.elkeszitesreVaro = new Rendelesek();
        this.keszitesAlatt = new Rendelesek();
        this.kapacitas = 7;
        this.keszlet = new HashSet<>();
    }

    public ArrayList<Etel> getMenu() {
        return menu;
    }

    public void keszitEtel() {

        try {
            sutobeTesz();//15000
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
        
        for(Hozzavalo hozzavalo: keszlet){
                if(hozzavalo.getDb() < 15){
                    this.szallito.vasarol();
                    break;
                }
            }

        for (int i = 0; i < elkeszitesreVaro.getMeret(); i++) {
            int db = 0;
            for (int j = 0; j < keszitesAlatt.getMeret(); j++) {
                db += keszitesAlatt.getRendeles(j).getEtelSzam();
            }
            if (db < this.kapacitas) {
                Rendeles ertek = this.getElkeszitesreVaro().getRendeles(i);
                this.getKeszitesAlatt().addRendeles(ertek);
                for (int k = 0; k < ertek.getEtelSzam(); k++) {
                    for (Hozzavalo jelenlegi : ertek.getEtel(k).getHozzavalok()) {
                        this.getHozzavaloByName(jelenlegi.getNev()).setDb(this.getHozzavaloByName(jelenlegi.getNev()).getDb() - 1);
                    }
                }
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
        this.futarok.add(jarmu);
    }

    public void removeJarmu(int index) {
        this.futarok.remove(index);
    }

    public Jarmu getJarmu(int index) {
        return this.futarok.get(index);
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
    public void setPoz(int poz) {
        this.poz = poz;
    }

    @Override
    public void setHazszam(int hazSzam) {
        this.hazszam = hazSzam;
    }

    @Override
    public BufferedImage getImageB() {
        return imageB;
    }

    public HashSet<Hozzavalo> getKeszlet() {
        return keszlet;
    }

    public void setKeszlet(HashSet<Hozzavalo> keszlet) {
        this.keszlet = keszlet;
    }

    public Hozzavalo getHozzavaloByName(String name) {
        for (Hozzavalo hozzavalo : keszlet) {
            if (hozzavalo.getNev().equalsIgnoreCase(name)) {
                return hozzavalo;
            }
        }
        return null;
    }

    public String keszletMutat() {
        StringBuffer sb = new StringBuffer("");
        for (Hozzavalo hozzavalo : keszlet) {
            sb.append(hozzavalo+"\n");
        }
        return sb.toString();
    }

    public void setKeszlet() {
        for (Etel etel : menu) {
            for (int i = 0; i < etel.getHozzavalok().size(); i++) {
                this.keszlet.add(etel.getHozzavalo(i));
                this.getHozzavaloByName(etel.getHozzavalo(i).getNev()).setDb(50);
            }
        }
    }

    public Szallito getSzallito() {
        return szallito;
    }

    public void setSzallito(Szallito szallito) {
        this.szallito = szallito;
    }
    
    

}
