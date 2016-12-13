package futarapp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

public class Terkep {

    public static void main(String[] args) {
        JFrame Terkep = new JFrame();
        Terkep.setTitle("Pizza app");
        Terkep.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        ImageIcon AppIcon = new ImageIcon("pizza-icon.png");
        Terkep.setIconImage(AppIcon.getImage());
        Terkep.setLayout(new BorderLayout());
        JPanel TerkepCont = new JPanel();
        JPanel container = new JPanel();
        container.setLayout(new GridLayout(1, 2));
        Terkep.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Auto auto = new Auto("Opel", "Astra");
        Moped moped = new Moped("s", "s");
        JPanel Glass = new JPanel();
        Terkep.setGlassPane(Glass);
        Terkep.add(container);
        container.add(TerkepCont, 0);
        Interface felulet = new Interface();
        container.add(felulet, 1);
        TerkepCont.setLayout(new GridLayout(Grid.SOR, Grid.OSZLOP));
        Terkep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Pizzeria pizzeria = new Pizzeria();
        Benzinkut benzinkut = new Benzinkut(340, 355);
        Lakasok lakasok = new Lakasok();
        Benzinkutak benzinkutak = new Benzinkutak();
        Bolt bolt = new Bolt();

        String csvFile = "alapanyagok.csv";
        String csvFile2 = "menu.csv";
        String csvFile3 = "nevek.csv";
        String line = "";
        String cvsSplitBy = ";";

        String[] keresztnev, vezeteknev;
        keresztnev = new String[100];
        vezeteknev = new String[100];
        int nevDb = 0;
        try {
            BufferedReader br3 = new BufferedReader(new FileReader(csvFile3));
            while ((line = br3.readLine()) != null) {

                String[] nev = line.split(cvsSplitBy);
                keresztnev[nevDb] = nev[1];
                vezeteknev[nevDb] = nev[0];
                nevDb++;

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A(z) " + csvFile3 + " f�jlt nem lehetett megnyitni!", "Hiba",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        int pizzeriaSzam = (int) (Math.random() * ((Grid.SOR / 2 * Grid.OSZLOP)) / 2);
        int randomSzam;
        do {
            randomSzam = (int) (Math.random() * ((Grid.SOR / 2 * Grid.OSZLOP)) / 2);
        } while (randomSzam == pizzeriaSzam);
        int BenzinkutSzam = randomSzam;

        for (int i = 0; i < Grid.SOR; i++) {
            for (int j = 0; j < Grid.OSZLOP; j++) {
                JPanel panelblock = new JPanel();
                panelblock.setOpaque(true);
                panelblock.setBackground(Color.GRAY);
                TerkepCont.add(panelblock, i * Grid.OSZLOP + j);
            }
        }
        Terkep.setVisible(true);
        int segedWidth = TerkepCont.getComponent(0).getWidth();
        int segedHeight = TerkepCont.getComponent(0).getHeight();
        TerkepCont.removeAll();
        Terkep.setVisible(false);

        try {
            int lakasdarab = 1;
            for (int i = 0; i < Grid.SOR; i++) {
                for (int j = 0; j < Grid.OSZLOP; j++) {
                    if (i % 2 == 0 || j % 2 == 0) {
                        JPanel panelblock = new JPanel();
                        panelblock.setOpaque(true);
                        panelblock.setBackground(Color.GRAY);
                        TerkepCont.add(panelblock, i * Grid.OSZLOP + j);
                    } else if (lakasdarab == pizzeriaSzam) {
                        pizzeria.setHazszam(lakasdarab);
                        pizzeria.setPoz(i * Grid.OSZLOP + j);
                        JLabel thumb = new JLabel();
                        thumb.setText("" + lakasdarab);
                        thumb.setHorizontalAlignment(JLabel.CENTER);
                        thumb.setHorizontalTextPosition(JLabel.CENTER);
                        thumb.setVerticalTextPosition(JLabel.BOTTOM);
                        thumb.setHorizontalAlignment(JLabel.CENTER);
                        thumb.setBackground(Color.WHITE);
                        BufferedImage scaled = pizzeria.getImageB();
                        TerkepCont.add(thumb, i * Grid.OSZLOP + j);
                        thumb.setIcon(new ImageIcon(scaled.getScaledInstance((int) (segedWidth * 0.75), (int) (segedHeight * 0.75), 0)));
                        TerkepCont.add(thumb, i * Grid.OSZLOP + j);
                        lakasdarab++;
                    } else if (lakasdarab == BenzinkutSzam) {
                        benzinkut.setHazszam(lakasdarab);
                        benzinkut.setPoz(i * Grid.OSZLOP + j);
                        JLabel thumb = new JLabel();
                        thumb.setText("" + lakasdarab);
                        thumb.setHorizontalAlignment(JLabel.CENTER);
                        thumb.setHorizontalTextPosition(JLabel.CENTER);
                        thumb.setVerticalTextPosition(JLabel.BOTTOM);
                        thumb.setHorizontalAlignment(JLabel.CENTER);
                        thumb.setBackground(Color.WHITE);
                        BufferedImage scaled = benzinkut.getImageB();
                        TerkepCont.add(thumb, i * Grid.OSZLOP + j);
                        thumb.setIcon(new ImageIcon(scaled.getScaledInstance((int) (segedWidth * 0.75), (int) (segedHeight * 0.75), 0)));
                        TerkepCont.add(thumb, i * Grid.OSZLOP + j);
                        benzinkutak.addBenzinkut(benzinkut);
                        lakasdarab++;
                    } else {
                        System.out.println("M�RET" + TerkepCont.getComponent(0).getHeight());
                        int lakokSzama = (int) (Math.random() * 15);
                        Lakas lakas = new Lakas(lakokSzama);
                        lakas.setHazszam(lakasdarab);
                        JLabel thumb = new JLabel();
                        thumb.setText("" + lakasdarab);
                        thumb.setHorizontalAlignment(JLabel.CENTER);
                        thumb.setHorizontalTextPosition(JLabel.CENTER);
                        thumb.setVerticalTextPosition(JLabel.BOTTOM);
                        thumb.setBackground(Color.WHITE);
                        thumb.setOpaque(true);
                        BufferedImage scaled = lakas.getImageB();
                        TerkepCont.add(thumb, i * Grid.OSZLOP + j);
                        thumb.setIcon(new ImageIcon(scaled.getScaledInstance((int) (segedWidth * 0.75), (int) (segedHeight * 0.75), 0)));
                        lakasdarab++;
                        lakas.setPoz(i * Grid.OSZLOP + j);
                        int eddigiLakok = 0;
                        do {
                            int keresztSzam = (int) (Math.random() * 24);
                            int vezetekSzam = (int) (Math.random() * 24);
                            String nev = vezeteknev[vezetekSzam] + " " + keresztnev[keresztSzam];

                            int year = (int) (Math.random() * 217 + 1900);
                            GregorianCalendar gc = new GregorianCalendar();
                            gc.set(gc.YEAR, year);
                            int dayOfYear = (int) (Math.random() * ((gc.getActualMaximum(gc.DAY_OF_YEAR) - 1)));
                            gc.set(gc.DAY_OF_YEAR, dayOfYear);
                            Date newDate = new Date(gc.getTimeInMillis());

                            int pizzaFugges = (int) (Math.random() * 11);

                            Lako lako = new Lako(nev, newDate, lakas, pizzaFugges);
                            lakas.addLako(lako);
                            eddigiLakok++;
                        } while (eddigiLakok < lakokSzama);
                        lakasok.addLakas(lakas);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A t�rk�p l�trehoz�sa sor�n hiba keletkezett!\n" + e.getMessage(), "Hiba",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                String[] alapanyag = line.split(cvsSplitBy);

                bolt.addAru(new Hozzavalo(alapanyag[0], Integer.parseInt(alapanyag[1]), Integer.parseInt(alapanyag[2])));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A(z) " + csvFile + " f�jlt nem lehetett megnyitni!", "Hiba",
                    JOptionPane.ERROR_MESSAGE);
        }
        try {
            BufferedReader br2 = new BufferedReader(new FileReader(csvFile2));
            while ((line = br2.readLine()) != null) {

                String[] menu = line.split(cvsSplitBy);
                Etel etel = new Etel(menu[0]);
                for (int i = 0; i < menu.length; i++) {
                    for (int j = 0; j < bolt.getAruk().size(); j++) {
                        if (menu[i].equalsIgnoreCase(bolt.getAru(j).getNev())) {
                            etel.addHozzavalo(bolt.getAru(j));
                            break;
                        }
                    }
                }
                pizzeria.addEtel(etel);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A(z) " + csvFile2 + " f�jlt nem lehetett megnyitni!", "Hiba",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        Glass.add(moped);
        Glass.add(auto);
        Glass.setLayout(new OverlayLayout(Glass));
        Terkep.setGlassPane(Glass);
        Glass.setVisible(true);
        moped.setVisible(false);
        auto.setVisible(false);
        Glass.setOpaque(false);
        Terkep.setVisible(true);
        pizzeria.addJarmu(moped);
        moped.setPizzeria(pizzeria);
        moped.setBenzinkutak(benzinkutak);
        pizzeria.addJarmu(auto);
        auto.setPizzeria(pizzeria);
        auto.setBenzinkutak(benzinkutak);
        auto.setOpaque(false);
        moped.setOpaque(false);

        auto.setSegedWidht(TerkepCont.getComponent(2).getWidth());
        auto.setSegedHeight(TerkepCont.getComponent(2).getHeight());
        moped.setSegedWidht(TerkepCont.getComponent(2).getWidth());
        moped.setSegedHeight(TerkepCont.getComponent(2).getHeight());

        pizzeria.setKeszlet();
        System.out.println(pizzeria.getKeszlet());

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (;;) {
                    try {
                        for (int i = 0; i < lakasok.getMeret(); i++) {
                            for (int j = 0; j < lakasok.getLakas(i).getLakokSzama(); j++) {
                                int RendelesRand = (int) (Math.random() * 100);
                                if (RendelesRand <= lakasok.getLakas(i).getLakok().get(j).getPizzaSzeretet()) {

                                    Rendeles ujRendeles = new Rendeles(lakasok.getLakas(i).getHazszam(), lakasok.getLakas(i).getLakok().get(j));
                                    lakasok.getLakas(i).getLako(j).setRendelesreVar(true);
                                    ujRendeles.addEtel(pizzeria.getMenu().get((int) (Math.random() * pizzeria.getMenu().size())));
                                    pizzeria.getElkeszitesreVaro().addRendeles(ujRendeles);
                                }
                                felulet.Textarea1(pizzeria.getElkeszitesreVaro());
                                felulet.Textarea2(pizzeria.getKeszitesAlatt());
                                felulet.Textarea4(pizzeria.getSzallitasraKesz());
                                felulet.Textarea3(pizzeria.getSzallitasAlatt());
                                felulet.JLabel5(pizzeria.getKiszallitottPizzak());
                                felulet.JLabel7(auto);//1000
                                felulet.JLabel8(moped);
                                felulet.JTextArea5(pizzeria);
                                Glass.repaint();

                                Thread.sleep(100);
                            }
                        }

                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        t1.start();

        Thread t3 = new Thread(new Runnable() {

            public void run() {
                for (;;) {
                    pizzeria.keszitEtel();
                }
            }
        });
        t3.start();

        Thread t4 = new Thread(new Runnable() {
            public void run() {
                for (;;) {
                    for (int i = 0; i < lakasok.getMeret(); i++) {
                        boolean var = false;
                        for (int j = 0; j < lakasok.getLakas(i).getLakokSzama(); j++) {
                            if (lakasok.getLakas(i).getLako(j).isRendelesreVar() == true) {
                                var = true;
                            }
                        }
                        if (var) {
                            TerkepCont.getComponent(lakasok.getLakas(i).getPoz()).setBackground(Color.red);
                        } else {
                            TerkepCont.getComponent(lakasok.getLakas(i).getPoz()).setBackground(Color.white);

                        }

                    }
                    try {//1000
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        t4.start();

        Thread t2 = new Thread(new Runnable() {

            public void run() {
                for (;;) {
                    try {//500
                        Thread.sleep(2000);
                        auto.inditSzallitas();

                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        t2.start();

        Thread t5 = new Thread(new Runnable() {

            public void run() {
                for (;;) {
                    try {//500
                        Thread.sleep(3000);
                        moped.inditSzallitas();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });
        t5.start();

    }
}
