/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.controller;


import org.example.collection.CollectionException;
import org.example.collection.LinkedList;
import org.example.data.Bike;
import org.example.generator.Generator;
import org.example.perzistence.BinReader;
import org.example.perzistence.BinWriter;
import org.example.perzistence.TxtReader;
import org.example.perzistence.TxtWriter;


import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @param <E>
 * @author makar
 */
public class Controller<E> implements IController<E> {

    private LinkedList<E> list;
    private Consumer<String> errorLog;
    private Comparator<? super E> comparator;
    private final E isEmpty = null;


    public Controller() {
        list = new LinkedList<>();
    }

    @Override
    public void add(E data) {
        try {
            list.addLast(data);
        } catch (Exception ex) {
            error(ex);
        }
    }

    @Override
    public E remove() {
        try {
            return list.removeCurrent();
        } catch (Exception ex) {
            error("Neni nastaven aktualni prvek");
        }
        return null;
    }

    @Override
    public E remove(E key) {
        try {
            list.setFirst();
            while (list.hasNext()) {
                E data = list.removeCurrent();
                if (comparator.compare(data, key) == 0) {
                    list.removeCurrent();
                    return data;
                }
            }
            list.next();
        } catch (Exception ex) {
            error("Neni nastaven aktualni prvek");
        }
        return null;
    }

    @Override
    public void first() {
        try {
            list.setFirst();
        } catch (Exception ex) {
            error(ex);
        }

    }

    @Override
    public void last() {
        try {
            list.setLast();
        } catch (Exception ex) {
            error(ex);
        }
    }

    @Override
    public void next() {
        try {
            list.next();
        } catch (Exception ex) {
            error("Dalsi data neexistuje");
        }

    }

    @Override
    public void generateData(int count) {
        try {
            Generator.generate(count);
            list = Generator.getBikeList();
        } catch (CollectionException ex) {
            error(ex);
        }

    }

    @Override
    public void edit(E key) {
        try {

            E data = list.getCurrent();
            int i = 0;
            list.setFirst();
            while (list.hasNext()) {
                i++;
                if (list.getCurrent() == data) break;
                list.next();
            }
            list.removeCurrent();
            if (i == 1) {
                list.removeFirst();
                list.addFirst(key);
                list.setLast();
            } else {
                list.setFirst();
                for (int j = 1; j < i - 1; j++) list.next();
                list.addAfterCurrent(key);
                list.next();
            }
        } catch (CollectionException e) {
            error("Current is not set");
        }
    }


    @Override
    public E get(E key) {
        return list.stream()
                .filter(obj -> comparator.compare(obj, key) == 0)
                .findFirst()
                .orElse(isEmpty);
    }

    @Override
    public E get() {
        try {
            return list.getCurrent();
        } catch (CollectionException ex) {
            error("Current is not set");
        }
        return null;
    }

    @Override
    public void addNew(E data) {
        list.addLast(data);
    }

    @Override
    public void clear() {
        list.clear();
        Generator.setmId(0);
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public int count() {
        return list.size();
    }

    @Override
    public Iterator iterator() {
        return list.iterator();
    }

    @Override
    public void setErrorLog(Consumer errorLog) {
        this.errorLog = errorLog;
    }

    @Override
    public void error(Exception ex) {
        error(ex.getMessage());
    }

    @Override
    public void before() {
        E data = null;
        try {
            data = list.getCurrent();
            list.setFirst();
            if (list.getFirst() == data)
                error("Aktualni = prvni");
            else {
                while (list.hasNext()) {
                    if (list.getAfterCurrent() == data) {
                        break;
                    }
                    list.next();
                }
            }
        } catch (CollectionException e) {
            error("Aktualni = prvni");
        }
    }

    private void error(String ex) {
        if (errorLog == null) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } else {
            errorLog.accept(ex);
        }
    }

    @Override
    public void setComparator(Comparator<? super E> comparator) {
        Objects.requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public Stream<E> stream() {
        try {
            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(list.iterator(), 0), false);
        } catch (NullPointerException e) {
            error("Seznam je prazdny");
        }
        return null;
    }

    @Override
    public void readTextFile(String fileName) {
        try {
            TxtReader.readFile(fileName, list);
        } catch (IOException | CollectionException e) {
            error(e);
        }
    }

    @Override
    public void saveTextFile(String fileName) {

        try {
            TxtWriter.saveToFile((LinkedList<Bike>) list, fileName);
        } catch (IOException e) {
            error(e);
        }
    }

    @Override
    public void readBinFile(String fileName) {
        try {
            BinReader.readData(fileName, list);
        } catch (IOException | ClassNotFoundException | CollectionException e) {
            error(e);
        }
    }

    @Override
    public void saveBinFile(String fileName) {
        try {
            BinWriter.saveToFile(list, fileName);
        } catch (IOException e) {
            error(e);
        }
    }

    @Override
    public void current(E data) {
        try {
            list.setFirst();
            while (list.hasNext()) {
                if (list.getCurrent() == data) {
                    break;
                }
                list.next();
            }
        } catch (CollectionException e) {
            error(e);
        }
    }

}
