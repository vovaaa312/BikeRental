package org.example.perzistence;

import org.example.collection.LinkedList;
import org.example.data.Bike;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author makar
 */
public class TxtWriter {

    private static FileWriter fw;
    private static PrintWriter pw;


    public static void saveToFile(LinkedList<Bike> list, String fileName) throws IOException {
        fw = new FileWriter(fileName);
        pw = new PrintWriter(fw);

        for (Bike bike : list) {
            pw.write(bike.toTxt());
        }
        pw.flush();
        pw.close();

    }

}
