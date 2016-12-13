package futarapp;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Moped extends Jarmu {

    private Pizzeria pizzeria;
    private Rendelesek szallitasAlatt;
    private boolean felvettRendeles;
    private Benzinkutak benzinkutak;
    private String imageFile = "mopedleft.png";
    private String imageTop = "mopedtop.png";
    private String imageBot = "mopedbot.png";
    private String imageLeft = "mopedleft.png";
    private String imageRight = "mopedright.png";

    public Moped(String marka, String tipus) {
        super();
        try {
            if (imageFile == null) {;
                image = ImageIO.read(new File(imageFile));
            } else {
                String ut = this.imageFile;
                image = ImageIO.read(new File(ut));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A(z) mpped l�trehoz�sa nem siker�lt !", "Hiba",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        this.tipus = tipus;
        this.marka = marka;
        this.szallitasAlatt = new Rendelesek();
        this.felvettRendeles = false;
        this.rakter = 3;
        this.fogyasztas = 0.1;
        this.jelenlegiUzemanyag = 20;
        this.haladasiSebesseg = 15;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, this.getSegedWidht(), this.getSegedHeight(), null);
    }

    @Override
    public void moveDown() {
        for (int i = 0; i < this.getSegedHeight(); i++) {
            try {
                y++;
                this.imageFile = imageBot;
                String ut = this.imageFile;
                image = ImageIO.read(new File(ut));
                repaint();
                this.jelenlegiUzemanyag -= this.getFogyasztas() / this.getSegedHeight();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(this.haladasiSebesseg);
            } catch (InterruptedException ex) {
                Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.position += Grid.OSZLOP;
    }

    @Override
    public void moveUp() {
        for (int i = 0; i < this.getSegedHeight(); i++) {
            try {
                try {
                    y--;
                    this.imageFile = imageTop;
                    String ut = this.imageFile;
                    image = ImageIO.read(new File(ut));
                    repaint();
                    this.jelenlegiUzemanyag -= this.getFogyasztas() / this.getSegedHeight();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Thread.sleep(this.haladasiSebesseg);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        this.position -= Grid.OSZLOP;
    }

    @Override
    public void moveLeft() {
        for (int i = 0; i < this.getSegedWidht(); i++) {
            try {
                try {
                    x--;
                    this.imageFile = imageLeft;
                    String ut = this.imageFile;
                    image = ImageIO.read(new File(ut));
                    repaint();
                    this.jelenlegiUzemanyag -= this.getFogyasztas() / this.getSegedWidht();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Thread.sleep(this.haladasiSebesseg);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        this.position--;
    }

    @Override
    public void moveRight() {
        for (int i = 0; i < this.getSegedWidht(); i++) {
            try {
                x++;
                this.imageFile = imageRight;
                String ut = this.imageFile;
                image = ImageIO.read(new File(ut));
                repaint();
                this.jelenlegiUzemanyag -= this.getFogyasztas() / this.getSegedWidht();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(this.haladasiSebesseg);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        this.position++;
    }

    public double getFogyasztas() {
        return fogyasztas;
    }

    public void setFogyasztas(double fogyasztas) {
        this.fogyasztas = fogyasztas;
    }

    public double getJelenlegiUzemanyag() {
        return jelenlegiUzemanyag;
    }

    public void setJelenlegiUzemanyag(double jelenlegiUzemanyag) {
        this.jelenlegiUzemanyag = jelenlegiUzemanyag;
    }

    public void inditSzallitas() {

        if (pizzeria.getSzallitasraKesz().getMeret() != 0) {
            if (felvettRendeles == false) {

                for (int i = 0; i < pizzeria.getSzallitasraKesz().getMeret(); i++) {
                    if (szallitasAlatt.getMeret() < this.rakter) {
                        Rendeles ertek = pizzeria.getSzallitasraKesz().getRendeles(i);
                        szallitasAlatt.addRendeles(ertek);
                        pizzeria.getSzallitasAlatt().addRendeles(ertek);
                        pizzeria.getSzallitasraKesz().removeRendeles(ertek);
                    }
                }
                felvettRendeles = true;
                this.setKezdoPont();
                this.setVisible(true);

                for (int i = 0; i < this.getSzallitasAlatt().getMeret(); i++) {
                    Rendeles cel = this.legkozelebbiKeres();

                    szallit(cel, cel.getLako().getLakohely().getPoz());
                    i--;

                }
                this.menjHaza(this.getPizzeria().getPoz());
                this.setVisible(false);
            }

        }

    }

    public void menjHaza(int poz) {
        boolean kesz = false;
        do {
            // Ha elosztom protected-el �s marad�kos oszt�s protected ha p�ros ----> mehet fel-le!!!!
            boolean vertikalis = false;
            boolean horizontalis = false;
            if ((this.position % Grid.OSZLOP) % 2 == 0) {
                if (this.position / Grid.OSZLOP <= poz / Grid.OSZLOP) {
                    this.moveDown();
                    vertikalis = true;
                } else if (this.position / Grid.OSZLOP > (poz / Grid.OSZLOP) + 1) {
                    this.moveUp();
                    vertikalis = true;
                }
            }

            if (!vertikalis) {
                if (this.position % Grid.OSZLOP > poz % Grid.OSZLOP) {
                    this.moveLeft();
                    horizontalis = true;
                } else if (this.position % Grid.OSZLOP < poz % Grid.OSZLOP) {
                    this.moveRight();
                    horizontalis = true;
                }
            }

            if (this.position + Grid.OSZLOP == poz || this.position - Grid.OSZLOP == poz) {
                this.felvettRendeles = false;
                kesz = true;
            }

            if (!vertikalis && !horizontalis) {
                this.moveLeft();
            }

        } while (!kesz);

    }

    public void szallit(Rendeles rendeles, int poz) {
        boolean kesz = false;

        do {
            if (30 > this.getJelenlegiUzemanyag() / this.fogyasztas) {
                this.tankol();
            }
            // Ha elosztom protected-el �s marad�kos oszt�s protected ha p�ros ----> mehet fel-le!!!!
            boolean vertikalis = false;
            boolean horizontalis = false;
            if ((this.position % Grid.OSZLOP) % 2 == 0) {
                if (this.position / Grid.OSZLOP <= poz / Grid.OSZLOP) {
                    this.moveDown();
                    vertikalis = true;
                } else if (this.position / Grid.OSZLOP > (poz / Grid.OSZLOP) + 1) {
                    this.moveUp();
                    vertikalis = true;
                }
            }

            if (!vertikalis) {
                if (this.position % Grid.OSZLOP > poz % Grid.OSZLOP) {
                    this.moveLeft();
                    horizontalis = true;
                } else if (this.position % Grid.OSZLOP < poz % Grid.OSZLOP) {
                    this.moveRight();
                    horizontalis = true;
                }
            }

            if (this.position + Grid.OSZLOP == poz || this.position - Grid.OSZLOP == poz) {
                kesz = true;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (!kesz && !vertikalis && !horizontalis) {
                this.moveLeft();
            }

        } while (!kesz);
        rendeles.getLako().setRendelesreVar(false);
        this.pizzeria.setKiszallitottPizzak(this.pizzeria.getKiszallitottPizzak() + 1);
        szallitasAlatt.removeRendeles(rendeles);
        pizzeria.getSzallitasAlatt().removeRendeles(rendeles);
    }

    private Rendeles legkozelebbiKeres() {
        Rendeles min = null;
        int db = 0;
        for (int i = 0; i < szallitasAlatt.getMeret(); i++) {
            Rendeles ertek = szallitasAlatt.getRendeles(i);
            if (db == 0) {
                min = ertek;
            } else if (this.getTav(ertek) < this.getTav(min)) {
                min = ertek;
            }
        }
        return min;
    }

    private Benzinkut benzinkutKeres(Benzinkutak benzinkutLista) {
        Benzinkut min = null;
        int db = 0;
        for (int i = 0; i < benzinkutLista.getMeret(); i++) {
            Benzinkut ertek = benzinkutLista.getBenzinkut(i);
            if (db == 0) {
                min = ertek;
            } else if (this.getTav(ertek) < this.getTav(min)) {
                min = ertek;
            }
        }
        return min;
    }

    private int getTav(Benzinkut benzinkut) {
        int tav = 0;
        tav += Math.abs(this.position - benzinkut.getPoz()) / Grid.OSZLOP;
        tav += Math.abs(this.position - benzinkut.getPoz()) % Grid.OSZLOP;
        return tav;
    }

    private int getTav(Rendeles rendeles) {
        int tav = 0;
        tav += Math.abs(this.position - rendeles.getLako().getLakohely().getPoz()) / Grid.OSZLOP;
        tav += Math.abs(this.position - rendeles.getLako().getLakohely().getPoz()) % Grid.OSZLOP;
        return tav;

    }

    @Override
    public void tankol() {
        boolean message = false;
        this.menjHaza(this.benzinkutKeres(this.benzinkutak).getPoz());
        if (message == false) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null, "Az aut� �ppen tankol");
                }
            });
            t.start();
            message = true;

        }

        while (this.getJelenlegiUzemanyag() < 50) {

            this.setJelenlegiUzemanyag(this.getJelenlegiUzemanyag() + 1);
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setKezdoPont() {
        int seged = this.pizzeria.getPoz();
        int Yeltol = seged / Grid.OSZLOP + 1;
        int Xeltol = seged % Grid.OSZLOP;
        this.x = Xeltol * this.getSegedWidht();
        this.y = Yeltol * this.getSegedHeight();
        this.position = this.pizzeria.getPoz() + Grid.OSZLOP;
    }

    public Benzinkutak getBenzinkutak() {
        return benzinkutak;
    }

    public void setBenzinkutak(Benzinkutak benzinkutak) {
        this.benzinkutak = benzinkutak;
    }

    @Override
    public void vasarol() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public Pizzeria getPizzeria() {
        return pizzeria;
    }

    public void setPizzeria(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    private void setPosition(int position) {
        this.position = position;
    }

    private int getPosition() {
        return this.position;
    }

    public Rendelesek getSzallitasAlatt() {
        return szallitasAlatt;
    }

    public int getSegedHeight() {
        return segedHeight;
    }

    public void setSegedHeight(int segedHeight) {
        this.segedHeight = segedHeight;
    }

    public int getSegedWidht() {
        return segedWidht;
    }

    public void setSegedWidht(int segedWidht) {
        this.segedWidht = segedWidht;
    }

}
