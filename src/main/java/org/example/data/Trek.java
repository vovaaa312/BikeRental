/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.data;

/**
 *
 * @author makar
 */
public class Trek extends Bike {

    public Trek(int id, String num, String name, Color colour, double mass, BikeType type) {
        super(id, num, name, colour, mass, BikeType.TREK);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String toTxt() {
        return super.toTxt();
    }

}
