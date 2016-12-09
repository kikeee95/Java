package futarapp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Bolt extends Epulet {

    private EpuletTipus tipus = EpuletTipus.Bolt;
    private ImageIcon image;
    private ArrayList<Hozzavalo> aruk;
    private String imageFile;
    private BufferedImage imageB;

    public Bolt() {
        this.imageFile = "tesco-icon.png";
        this.aruk = new ArrayList<>();

        try {
            if (imageFile == null) {;
                imageB = ImageIO.read(new File(imageFile));
            } else {
                String ut = this.imageFile;
                imageB = ImageIO.read(new File(ut));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A(z) bolt létrehozása nem sikerült !", "Hiba",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        this.image = new ImageIcon(imageB);
    }

    public void addAru(Hozzavalo aru) {
        this.aruk.add(aru);
    }

    public void removeAru(int index) {
        this.aruk.remove(index);
    }

    public Hozzavalo getAru(int index) {
        return this.aruk.get(index);
    }

    public ArrayList<Hozzavalo> getAruk() {
        return aruk;
    }

    @Override
    public int getPoz() {
        return this.poz;
    }

    @Override
    public int getHazszam() {
        return this.hazszam;
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
