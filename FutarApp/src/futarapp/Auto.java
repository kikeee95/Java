package futarapp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.Timer;

public class Auto extends Jarmu {

    private BufferedImage image;
    private int x = 0, y = 0;
    private String imageFile = "car.png";
    private String imageTop = "carTop.png";
    private String imageBot = "carBot.png";
    private String imageLeft = "carLeft.png";
    private String imageRight = "carRight.png";
    private String marka;
    private String tipus;
    private int rakter;
    private Pizzeria pizzeria;
    private int position;
    private Rendelesek szallitasAlatt;
    private boolean felvettRendeles;
    private double fogyasztas;
    private double jelenlegiUzemanyag;
    private Benzinkutak benzinkutak;
    private int haladasiSebesseg;

    public Auto(String marka, String tipus) {
        super();
try{
            if (imageFile == null) {;
                image = ImageIO.read(new File(imageFile));
            } else {
                String ut = this.imageFile;
                image = ImageIO.read(new File(ut));
            }
   } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A(z) autó létrehozása nem sikerült !", "Hiba",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        this.tipus = tipus;
        this.marka = marka;
        this.szallitasAlatt = new Rendelesek();
        this.felvettRendeles = false;
        this.rakter = 5;
        this.fogyasztas = 0.5;
        this.jelenlegiUzemanyag = 50;
        this.haladasiSebesseg = 10;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, null);
    }

    @Override
    public void moveDown() {
        for (int i = 0; i < 69; i++) {
            try {
                y++;
                this.imageFile = imageBot;
                String ut = this.imageFile;
                image = ImageIO.read(new File(ut));
                repaint();
                        this.jelenlegiUzemanyag -= this.getFogyasztas()/69;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(this.haladasiSebesseg);
            } catch (InterruptedException ex) {
                Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.position += 15;
    }

    @Override
    public void moveUp() {
        for (int i = 0; i < 69; i++) {
            try {
                try {
                    y--;
                    this.imageFile = imageTop;
                    String ut = this.imageFile;
                    image = ImageIO.read(new File(ut));
                    repaint();
                     this.jelenlegiUzemanyag -= this.getFogyasztas()/69;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Thread.sleep(this.haladasiSebesseg);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        this.position -= 15;
    }

    @Override
    public void moveLeft() {
        for (int i = 0; i < 63; i++) {
            try {
                try {
                    x--;
                    this.imageFile = imageLeft;
                    String ut = this.imageFile;
                    image = ImageIO.read(new File(ut));
                    repaint();
                     this.jelenlegiUzemanyag -= this.getFogyasztas()/63;
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
        for (int i = 0; i < 63; i++) {
            try {
                x++;
                this.imageFile = imageRight;
                String ut = this.imageFile;
                image = ImageIO.read(new File(ut));
                repaint();
                 this.jelenlegiUzemanyag -= this.getFogyasztas()/63;
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

        if (pizzeria.getSzallitasraKesz().getMeret() != 0 && this.felvettRendeles == false) {
            System.out.println(pizzeria.getSzallitasraKesz());
            while (felvettRendeles == false) {
                this.setKezdoPont();
                this.setVisible(true);
                for (int i = 0; i < pizzeria.getSzallitasraKesz().getMeret(); i++) {
                    if(szallitasAlatt.getMeret() < this.rakter){
                    Rendeles ertek = pizzeria.getSzallitasraKesz().getRendeles(i);
                    szallitasAlatt.addRendeles(ertek);
                    pizzeria.getSzallitasAlatt().addRendeles(ertek);
                    pizzeria.getSzallitasraKesz().removeRendeles(ertek);
                    }
                }
                felvettRendeles = true;

                for (int i = 0; i < this.getSzallitasAlatt().getMeret(); i++) {
                    Rendeles cel = this.legkozelebbiKeres();

                    szallit(cel, cel.getLako().getLakohely().getPoz());
                    i--;

                }
            }
            this.menjHaza(this.getPizzeria().getPoz());
            this.setVisible(false);
        }

    }

    public void menjHaza(int poz) {
        boolean kesz = false;
        do {
            // Ha elosztom 15-el és maradékos osztás 15 ha páros ----> mehet fel-le!!!!
            boolean vertikalis = false;
            boolean horizontalis = false;
            if ((this.position % 15) % 2 == 0) {
                if (this.position / 15 <= poz / 15) {
                    this.moveDown();
                    vertikalis = true;
                } else if (this.position / 15 > (poz / 15) + 1) {
                    this.moveUp();
                    vertikalis = true;
                }
            }

            if (!vertikalis) {
                if (this.position % 15 > poz % 15) {
                    this.moveLeft();
                    horizontalis = true;
                } else if (this.position % 15 < poz % 15) {
                    this.moveRight();
                    horizontalis = true;
                }
            }

            if (this.position + 15 == poz || this.position - 15 == poz) {
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
            if ( 30 > this.getJelenlegiUzemanyag()/this.fogyasztas) {
                this.tankol();
            } else {
                // Ha elosztom 15-el és maradékos osztás 15 ha páros ----> mehet fel-le!!!!
                boolean vertikalis = false;
                boolean horizontalis = false;
                if ((this.position % 15) % 2 == 0) {
                    if (this.position / 15 <= poz / 15) {
                        this.moveDown();
                        vertikalis = true;
                    } else if (this.position / 15 > (poz / 15) + 1) {
                        this.moveUp();
                        vertikalis = true;
                    }
                }

                if (!vertikalis) {
                    if (this.position % 15 > poz % 15) {
                        this.moveLeft();
                        horizontalis = true;
                    } else if (this.position % 15 < poz % 15) {
                        this.moveRight();
                        horizontalis = true;
                    }
                }

                if (this.position + 15 == poz || this.position - 15 == poz) {
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
            }
        } while (!kesz);
        rendeles.getLako().getLakohely().setRendelesreVar(false);
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
        tav += Math.abs(this.position - benzinkut.getPoz()) / 15;
        tav += Math.abs(this.position - benzinkut.getPoz()) % 15;
        return tav;
    }

    private int getTav(Rendeles rendeles) {
        int tav = 0;
        tav += Math.abs(this.position - rendeles.getLako().getLakohely().getPoz()) / 15;
        tav += Math.abs(this.position - rendeles.getLako().getLakohely().getPoz()) % 15;
        return tav;

    }

    @Override
    public void tankol() {
        boolean message = false;
        this.menjHaza(this.benzinkutKeres(this.benzinkutak).getPoz());
        if (message == false) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null, "Az autó éppen tankol");
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
        int Yeltol = seged / 15 + 1;
        int Xeltol = seged % 15;
        this.x = Xeltol * 63;
        this.y = Yeltol * 69;
        this.position = this.pizzeria.getPoz() + 15;
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

}
