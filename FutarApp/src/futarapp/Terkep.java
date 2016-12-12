package futarapp;

import java.awt.*;
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
        Terkep.setGlassPane(auto);
        Terkep.add(container);
        container.add(TerkepCont, 0);
        Interface felulet = new Interface();
        container.add(felulet, 1);
        TerkepCont.setLayout(new GridLayout(15, 16));
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
            JOptionPane.showMessageDialog(null, "A(z) " + csvFile3 + " fájlt nem lehetett megnyitni!", "Hiba",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        int pizzeriaSzam = (int) (Math.random() * 50);
        int randomSzam;
        do {
            randomSzam = (int) (Math.random() * 50);
        } while (randomSzam == pizzeriaSzam);
        int BenzinkutSzam = randomSzam;

        try {
            int lakasdarab = 1;
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (i % 2 == 0 || j % 2 == 0) {
                        JPanel panelblock = new JPanel();
                        panelblock.setOpaque(true);
                        panelblock.setBackground(Color.GRAY);
                        TerkepCont.add(panelblock, i * 15 + j);
                    } else if (lakasdarab == pizzeriaSzam) {
                        pizzeria.setHazszam(lakasdarab);
                        pizzeria.setPoz(i * 15 + j);
                        JLabel thumb = new JLabel();
                        thumb.setText("" + lakasdarab);
                        thumb.setHorizontalAlignment(JLabel.CENTER);
                        thumb.setHorizontalTextPosition(JLabel.CENTER);
                        thumb.setVerticalTextPosition(JLabel.BOTTOM);
                        thumb.setHorizontalAlignment(JLabel.CENTER);
                        thumb.setBackground(Color.WHITE);
                        thumb.setIcon(pizzeria.getImage());
                        TerkepCont.add(thumb, i * 15 + j);
                        lakasdarab++;
                    } else if (lakasdarab == BenzinkutSzam) {
                        benzinkut.setHazszam(lakasdarab);
                        benzinkut.setPoz(i * 15 + j);
                        JLabel thumb = new JLabel();
                        thumb.setText("" + lakasdarab);
                        thumb.setHorizontalAlignment(JLabel.CENTER);
                        thumb.setHorizontalTextPosition(JLabel.CENTER);
                        thumb.setVerticalTextPosition(JLabel.BOTTOM);
                        thumb.setHorizontalAlignment(JLabel.CENTER);
                        thumb.setBackground(Color.WHITE);
                        thumb.setIcon(benzinkut.getImage());
                        TerkepCont.add(thumb, i * 15 + j);
                        benzinkutak.addBenzinkut(benzinkut);
                        lakasdarab++;
                    } else {
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
                        thumb.setIcon(lakas.getImage());
                        TerkepCont.add(thumb, i * 15 + j);
                        lakasdarab++;
                        lakas.setPoz(i * 15 + j);
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
            JOptionPane.showMessageDialog(null, "A térkép létrehozása során hiba keletkezett!\n" + e.getMessage(), "Hiba",
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
            JOptionPane.showMessageDialog(null, "A(z) " + csvFile + " fájlt nem lehetett megnyitni!", "Hiba",
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
            JOptionPane.showMessageDialog(null, "A(z) " + csvFile2 + " fájlt nem lehetett megnyitni!", "Hiba",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        pizzeria.addJarmu(auto);
        auto.setPizzeria(pizzeria);
        auto.setBenzinkutak(benzinkutak);

        auto.setOpaque(false);

        Terkep.setVisible(true);

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
                            if (lakasok.getLakas(i).getLako(j).isRendelesreVar()==true) {
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
                    auto.inditSzallitas();
                    try {//500
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        t2.start();

    }
}
