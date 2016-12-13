
package futarapp;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



public class Benzinkut extends Epulet{
    private EpuletTipus tipus = EpuletTipus.Benzinkut;
    private ImageIcon image;
    private int dieselAr;
    private int benzinAr;
    private String imageFile;
    private BufferedImage imageB;


    public Benzinkut(int benzinAr, int dieselAr){
         this.imageFile = "MOL_logo.jpg";
        try {
            if (imageFile == null) {;
                imageB = ImageIO.read(new File(imageFile));
            } else {
                String ut = this.imageFile;
                imageB = ImageIO.read(new File(ut));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A(z) benzinkút létrehozása nem sikerült !", "Hiba",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        this.benzinAr = (int)(benzinAr*Afak.Uzemanyag.getAfa());
        this.dieselAr =(int) (dieselAr*Afak.Uzemanyag.getAfa());
    }

    public int getDieselAr() {
        return dieselAr;
    }

    public void setDieselAr(int dieselAr) {
        this.dieselAr = dieselAr;
    }

    public int getBenzinAr() {
        return benzinAr;
    }

    public void setBenzinAr(int benzinAr) {
        this.benzinAr = benzinAr;
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

}
