package org.example.data;

/**
 *
 * @author makar
 */
public class Mountain extends Bike {

    public Mountain(int id, String num, String name, Color colour, double mass, BikeType typ) {
        super(id, num,name, colour, mass, BikeType.MOUNTAIN);
//        if (typ != TypKola.HORSKE) {
//            throw new KolekceException("Nespravne zadan typ kola");
//        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String toTxt() {
        return super.toTxt();
    }

}
