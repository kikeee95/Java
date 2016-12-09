

package futarapp;

import javax.swing.ImageIcon;

public abstract class Epulet{
    protected int poz;
    protected int hazszam;
    protected ImageIcon image;
    
    public Epulet(){
    }
    
    public abstract void setPoz( int poz);
    
    public abstract void setHazszam(int hazSzam);

    public abstract int getPoz();

    public abstract int getHazszam();

    public abstract EpuletTipus getTipus();

    public abstract ImageIcon getImage();

}
