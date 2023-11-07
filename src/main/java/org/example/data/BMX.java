package org.example.data;

/**
 *
 * @author makar
 */
public class BMX extends Bike {

    public BMX(int id, String num, String name, Color color, double mass, BikeType type)  {
        super(id, num,name, color, mass, BikeType.BMX);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String toTxt() {
        return super.toTxt();
    }

}
