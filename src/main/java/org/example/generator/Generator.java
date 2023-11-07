/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.generator;

import org.example.collection.CollectionException;
import org.example.collection.LinkedList;
import org.example.data.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;


/**
 * @param <E>
 * @author makar
 */
public class Generator<E> {

    private  final Random
            rand = new Random();
    public static LinkedList<Bike>
            bikeList = new LinkedList<>();
    static public int
            mId = 0;




    public static Stream<? extends Bike> generate(int count) throws CollectionException {
        Bike[] kola = new Bike[count];
        for (int i = 0; i < count; i++) {
            var bike = generateBike();
            bikeList.addLast(bike);
            kola[i] = bike;
        }

        return Arrays.stream(kola);
    }

    public static Bike generateBike() {
        Random random = new Random();
        Bike bike = null;
        StringBuilder num = new StringBuilder();
        num.append(ran()).append(ran()).append(random.nextInt(300) + 180).append("_").append(ran());
        int rnd = 1 + (int) (Math.random() * 3);

        mId++;
        switch (rnd) {
            case 1 -> bike = new Mountain(mId, num.toString(), "mtb_b", randomColor(), 3, BikeType.MOUNTAIN);
            case 2 -> bike = new Trek(mId, num.toString(), "trek_b", randomColor(), 3, BikeType.TREK);
            case 3 -> bike = new BMX(mId, num.toString(), "bmx_b", randomColor(), 3, BikeType.BMX);

        }
        return bike;
    }

    private static Color randomColor() {
        Color[] clr = Color.values();
        int rnd = (int) (Math.random() * 5) + 1;
        return clr[rnd - 1];
    }

    public static LinkedList getBikeList() {
        return bikeList;
    }

    private static String ran() {
        ArrayList<String> alphaNum = new ArrayList<>();

        for (char c = 'A'; c <= 'z'; c++) {
            String s = "";
            s += c;
            alphaNum.add(s);
            if (c == 'Z') {
                c = 'a' - 1;
            }
        }

        for (int c = 0; c < 10; c++) {
            String s = "";
            s += c;
            alphaNum.add(s);
        }
        return (alphaNum.get((int) (Math.random() * alphaNum.size())));
    }

    public static void setmId(int mId) {
        Generator.mId = mId;
    }
}
