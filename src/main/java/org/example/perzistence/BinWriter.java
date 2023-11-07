package org.example.perzistence;

import org.example.collection.LinkedList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;

/**
 *
 * @author makar
 */
public class BinWriter {

    private static FileOutputStream fos;
    private static ObjectOutputStream oos;

    public BinWriter() {
    }

    public static void saveToFile(LinkedList list, String fileName) throws IOException {
        if (list == null || list.size() == 0) {
            throw new NullPointerException();
        }

        fos = new FileOutputStream(fileName, false);
        oos = new ObjectOutputStream(fos);
        oos.writeInt(list.size());
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            oos.writeObject(iter.next());
        }
        oos.flush();
        oos.close();

    }

}
