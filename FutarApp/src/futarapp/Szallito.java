package futarapp;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Szallito extends Jarmu {

    private Pizzeria pizzeria;
    private Rendelesek szallitasAlatt;
    private boolean felvettRendeles;
    private Benzinkutak benzinkutak;
    private String imageFile = "truckleft.png";
    private String imageTop = "trucktop.png";
    private String imageBot = "truckbot.png";
    private String imageLeft = "truckleft.png";
    private String imageRight = "truckright.png";
    private Bolt bolt;
    private HashSet<Hozzavalo> szallitmany;

    public Szallito(String marka, String tipus) {
        super();
        try {
            if (imageFile == null) {;
                image = ImageIO.read(new File(imageFile));
            } else {
                String ut = this.imageFile;
                image = ImageIO.read(new File(ut));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A(z) szállító létrehozása nem sikerült !", "Hiba",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        this.tipus = tipus;
        this.marka = marka;
        this.szallitmany = new HashSet<>();
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

    public void menjHaza(int poz) {
        boolean kesz = false;
        do {
            // Ha elosztom protected-el és maradékos osztás protected ha páros ----> mehet fel-le!!!!
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

    private void setKezdoPont() {
        int seged = this.pizzeria.getPoz();
        int Yeltol = seged / Grid.OSZLOP + 1;
        int Xeltol = seged % Grid.OSZLOP;
        this.x = Xeltol * this.getSegedWidht();
        this.y = Yeltol * this.getSegedHeight();
        this.position = this.pizzeria.getPoz() + Grid.OSZLOP;
    }

    public void vasarol() {
        this.setKezdoPont();
        this.setVisible(true);
        this.menjHaza(this.bolt.getPoz());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Szallito.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.menjHaza(pizzeria.poz);
        for (Hozzavalo hozzavalo : pizzeria.getKeszlet()) {
            for (int i = 0; i < bolt.getAruk().size(); i++) {
                if (hozzavalo.getNev().equalsIgnoreCase(bolt.getAru(i).getNev())) {
                    Hozzavalo aruCikk = bolt.getAru(i);
                    aruCikk.setDb((50 - aruCikk.getDb()) / 2 + aruCikk.getDb());
                    szallitmany.add(aruCikk);
                }
            }
        }
        this.setVisible(false);
        JOptionPane.showMessageDialog(null, "A készlet sikeresen feltöltve!");
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

    public Bolt getBolt() {
        return bolt;
    }

    public void setBolt(Bolt bolt) {
        this.bolt = bolt;
    }

}
