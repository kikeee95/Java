package futarapp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Lakas extends Epulet {

    private EpuletTipus tipus;
    private String imageFile;
    private BufferedImage imageB;
    private ImageIcon image;
    private int lakokSzama;
    private ArrayList<Lako> lakok;


    public Lakas(int lakokSzama) throws Exception {
        this.lakok = new ArrayList<>();
        this.tipus = EpuletTipus.Lakas;
        if (lakokSzama > 4) {
            try {
                imageFile = "houseScale.png";
                String ut = this.imageFile;
                imageB = ImageIO.read(new File(ut));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "A(z) lakás létrehozása nem sikerült !", "Hiba",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }

        } else {
            try {
                imageFile = "apartment.png";
                String ut = this.imageFile;
                imageB = ImageIO.read(new File(ut));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "A(z) lakás létrehozása nem sikerült !", "Hiba",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
        this.image = new ImageIcon(imageB);

        this.lakokSzama = lakokSzama;


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
        return tipus;
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    public int getLakokSzama() {
        return lakokSzama;
    }

    public void setLakokSzama(int lakokSzama) {
        this.lakokSzama = lakokSzama;
    }

    public Lako getLako(int index) {
        return this.lakok.get(index);
    }

    public ArrayList<Lako> getLakok() {
        return lakok;
    }

    public void addLako(Lako lako) throws Exception {
        if (lakok.size() <= this.lakokSzama) {
            this.lakok.add(lako);
        } else {
            throw new Exception("A lakóépület betelt!");
        }
    }

    @Override
    public void setPoz(int poz) {
        this.poz = poz;
    }

    @Override
    public void setHazszam(int hazSzam) {
        this.hazszam = hazSzam;
    }

    public BufferedImage getImageB() {
        return imageB;
    }

    public void setImageB(BufferedImage imageB) {
        this.imageB = imageB;
    }
    
    
    
    

}
