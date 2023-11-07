/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.example.controller;

import org.example.collection.CollectionException;

import java.io.IOException;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 *
 * @author makar
 * @param <E>
 */
public interface IController<E> extends Iterable<E> {

    void add(E data);

    void first();
    //- nastav jako aktuální první data v seznamu

    void last();
    //- přejdi na poslední data

    void next();
    //- přejdi na další data

    void generateData(int count);
    //- generuj náhodně data pro testování

    void edit(E key);
    //- edituj aktuální data v seznamu

    void readTextFile(String fileName) throws CollectionException, IOException;
    //- načti seznam data z textového souboru

    void saveTextFile(String fileName);
    //- ulož seznam data do textového souboru

    void readBinFile(String fileName) throws CollectionException, IOException, ClassNotFoundException;  //obnov
    //- obnov seznam data z binárního souboru

    void saveBinFile(String fileName) throws IOException;  //zalohuj
    //- zálohuj seznam dat do binárního souboru


    Stream<E> stream();

    E get(E key);
    //- najdi v seznamu data podle hodnoty nějakém atributu

    E remove(E key);
    //- odeber data ze seznamu podle nějaké hodnoty atributu 

    E remove();

    E get();
    //- zobraz aktuální data v seznamu

    void addNew(E data);
    //- vytvoř novou instanci a vlož data za aktuální prvek

    void clear();
    //- zruš všechny data v seznamu

    void exit();

    int count();
    //- zobraz počet položek v seznamu

    void setErrorLog(Consumer<String> errorLog);

    void setComparator(Comparator<? super E> comparator);

    void error(Exception ex);

    void before();

    void current(E data);
}
