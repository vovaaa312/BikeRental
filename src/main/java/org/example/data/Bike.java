/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 *
 * @author makar
 */

@AllArgsConstructor
@Getter
public abstract class Bike implements Serializable {

    private int id;
    private final String num;
    private final String name;
    private final Color colour;
    private final double mass;
    private final BikeType type;



    public String getTypeName(){
        return type.getName();
    }

    @Override
    public String toString() {

        return  id + " ; " + num +" ; " + name
                + " ; " + type.getName() + String.format( " ; %.2f", mass)
                + " ; " + colour;
    }
//    String.format(", hmotnost=%.2f", hmotnost)

    public String toTxt() {
        return id + "," + num +  " , " + name + "," + getTypeName() + ","
                + String.format("%.2f", mass) + "," + colour + ",\n";
    }


}
