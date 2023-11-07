/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.perzistence;


import org.example.collection.CollectionException;
import org.example.collection.LinkedList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author makar
 */
public class BinReader<E> {
    
    private static FileInputStream fis;
    private static ObjectInputStream ois;

    
    
    
    public static void readData(String fileName, LinkedList list) throws IOException, ClassNotFoundException, CollectionException {
        fis = new FileInputStream(fileName);
        ois = new ObjectInputStream(fis);
        int pocet = ois.readInt();
        
        for (int i = 0; i < pocet; i++) {
            list.addLast(ois.readObject());
        }        

    }
    

    
}
