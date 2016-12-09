
package futarapp;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class Benzinkut extends Epulet{
    private EpuletTipus tipus = EpuletTipus.Benzinkut;
    private ImageIcon image;
    private int dieselAr;
    private int benzinAr;


    public Benzinkut(int benzinAr, int dieselAr){
         this.image = new ImageIcon("mol_1.jpg");
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
