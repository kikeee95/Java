
package futarapp;

import javax.swing.*;

import java.awt.image.BufferedImage;


public abstract class Jarmu extends JPanel {

    private BufferedImage image;
    private int x = 0, y = 0;
    private String imageFile;
    private String imageTop;
    private String imageBot;
    private String imageLeft;
    private String imageRight;
    private String marka;
    private String tipus;
    private int sebesseg;
    private int fogyasztas;
    private int rakter;
    
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();
    
    public abstract void tankol();
    public abstract void szallit(Rendeles rendeles, int poz);
    public abstract void vasarol();
    
}
