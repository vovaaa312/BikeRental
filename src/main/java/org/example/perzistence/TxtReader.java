/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.perzistence;

import org.example.collection.CollectionException;
import org.example.collection.LinkedList;
import org.example.data.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author makar
 */
public class TxtReader {

    private static FileReader fr;
    private static BufferedReader br;



    public static LinkedList<Bike> readFile(String fileName, LinkedList readedList) throws FileNotFoundException, IOException, CollectionException {
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        String[] bike;
        String line;
        Bike nacteneKolo = null;
        while ((line = br.readLine()) != null) {
            bike = line.split(",");

            int id;
            String cislo;
            String nazevRd;
            Color barvaRd;
            double hmontostRd;
            BikeType typRd;

            id = Integer.parseInt(bike[0]);
            cislo = bike[1];
            nazevRd = bike[2];
            hmontostRd = Double.parseDouble(bike[4]);

            switch (bike[5]) {
                case "bílá","bila" ->
                    barvaRd = Color.WHITE;
                case "černá","cerna" ->
                    barvaRd = Color.BLACK;
                case "červená","cervena" ->
                    barvaRd = Color.RED;
                case "zelená","zelena" ->
                    barvaRd = Color.GREEN;
                case "modrá","modra" ->
                    barvaRd = Color.BLUE;
                default ->
                    barvaRd = Color.BLACK;
            }

            switch (bike[3]) {
                case "bmx":
                    typRd = BikeType.BMX;
                    nacteneKolo = new BMX(id,cislo,nazevRd, barvaRd, hmontostRd, typRd);
                    break;
                case "horske":
                    typRd = BikeType.MOUNTAIN;
                    nacteneKolo = new Mountain(id,cislo,nazevRd, barvaRd, hmontostRd, typRd);
                    break;
                case "trekingove":
                    typRd = BikeType.TREK;
                    nacteneKolo = new Trek(id,cislo,nazevRd, barvaRd, hmontostRd, typRd);
                    break;
                default:
                    typRd = BikeType.TREK;
                    nacteneKolo = new Trek(id,cislo,nazevRd, barvaRd, hmontostRd, typRd);
                    break;
            }

            readedList.addLast(nacteneKolo);

        }

//        if (readedList == null || readedList.size() == 0) {
//            throw new NullPointerException();
//        }
        return readedList;
    }

}
