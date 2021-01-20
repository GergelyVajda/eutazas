/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eutazas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gergely.vajda
 */
public class Eutazas {

    public static int napokszama(int e1, int h1, int n1, int e2, int h2, int n2) {
        int vegeredmeny = 0;
        h1 = (h1 + 9) % 12;
        e1 = e1 - h1 / 10;
        int d1 = 360 * e1 + e1 / 4 - e1 / 100 + e1 / 400 + (h1 * 306 + 5) / 10 + n1 - 1;
        h2 = (h2 + 9) % 12;
        e2 = e2 - h2 / 10;
        int d2 = d2 = 360 * e2 + e2 / 4 - e2 / 100 + e2 / 400 + (h2 * 306 + 5) / 10 + n2 - 1;
        h2 = (h2 + 9) % 12;
        vegeredmeny = d2 - d1;
        return vegeredmeny;
    }

    public static void main(String[] args) {
        try {
            //1
            FileReader buta = new FileReader("utasadat.txt");
            BufferedReader okos = new BufferedReader(buta);
            int hossz = 0;
            String ideiglenes = " ";
            String[] vagdalt = new String[5];
            String[] jTipus = new String[2000];
            Integer[][] adatok = new Integer[2000][5];
            String[] datumSplit = new String[2];
            for (int i = 0; i < 2000; i++) {
                ideiglenes = okos.readLine();
                if (ideiglenes == null) {
                    break;
                }

                vagdalt = ideiglenes.split(" ");
                datumSplit = vagdalt[1].split("-");
                adatok[i][0] = Integer.parseInt(vagdalt[0]);
                adatok[i][1] = Integer.parseInt(datumSplit[0]);
                adatok[i][2] = Integer.parseInt(datumSplit[1]);
                adatok[i][3] = Integer.parseInt(vagdalt[2]);
                jTipus[i] = vagdalt[3];
                adatok[i][4] = Integer.parseInt(vagdalt[4]);
                hossz++;
                //System.out.println(adatok[i][0]+" "+adatok[i][1]+" "+adatok[i][2]+" "+jTipus[i]+" "+adatok[i][3]+" "+adatok[i][4]);
            }
            //2
            System.out.println("2. feladat");
            System.out.println("A buszra " + hossz + "db utas szeretett volna felszállni.");
            //3
            System.out.println("3. feladat");
            int ervenytelen = 0;
            for (int i = 0; i < hossz; i++) {
                if (adatok[i][4] <= adatok[i][1] && adatok[i][4] > 20000) {
                    ervenytelen++;
                }
                if (adatok[i][4] == 0) {
                    ervenytelen++;
                }
            }
            System.out.println("A buszra " + ervenytelen + " ember nem szállhatott fel.");
            //4
            System.out.println("4. feladat");
            Integer[] megallo = new Integer[30];
            for (int i = 0; i < 30; i++) {
                megallo[i] = 0;
            }

            int szam = 0;
            for (int i = 0; i < hossz; i++) {
                szam = adatok[i][0];
                megallo[szam]++;
            }
            int legn = 0;
            for (int i = 0; i < 30; i++) {
                if (megallo[i] > legn) {
                    legn = megallo[i];
                }
            }
            System.out.println("A legtöbb egy megállóban felszálló utas " + legn + " volt.");
            //5
            System.out.println("5. feladat");
            int kedv = 0;
            int ingy = 0;
            for (int i = 0; i < hossz; i++) {
                if (adatok[i][4] >= adatok[i][1] && adatok[i][4] > 20000 || adatok[i][4] != 0) {
                    if (jTipus[i].equals("TAB") || jTipus[i].equals("NYB")) {
                        kedv++;
                    }
                    if (jTipus[i].equals("NYP") || jTipus[i].equals("RVS") || jTipus[i].equals("GYK")) {
                        ingy++;
                    }
                }
            }
            System.out.println("Ingyenesen utazók száma: " + ingy);
            System.out.println("Kedvezmányesen utazók száma: " + kedv);
            //6
            int proba = napokszama(1999, 5, 2, 1999, 6, 2);
            System.out.println(proba);

        } catch (FileNotFoundException ex) {
            System.out.println("NnINCS FILE");
        } catch (IOException ex) {
            System.out.println("Olvasási hiba");
        }
    }

}
