package futarapp;

import javax.swing.*;

import java.awt.image.BufferedImage;

public abstract class Jarmu extends JPanel {

    protected BufferedImage image;
    protected int x = 0, y = 0;
    protected String marka;
    protected String tipus;
    protected double jelenlegiUzemanyag;
    protected int haladasiSebesseg;
    protected double fogyasztas;
    protected int segedHeight;
    protected int segedWidht;
    protected int position;
    protected int rakter;

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void moveLeft();

    public abstract void moveRight();


}
