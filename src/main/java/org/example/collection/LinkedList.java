/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.collection;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @param <E>
 * @author makar
 */
public class LinkedList<E> implements IlinkedList<E> {

    private static class Element<E> implements Serializable {

        public E data;
        public Element<E> next;

        public Element(E data) {
            this.data = data;
            next = null;
        }
    }

    private int count = 0;
    private Element<E> first;
    private Element<E> last;
    private Element<E> current;

    @Override
    public void setFirst() throws CollectionException {
        if (isEmpty()) {
            throw new CollectionException("Seznam je prazdny");
        }

        current = first;
        current.next = first.next;
    }

    @Override
    public void setLast() throws CollectionException {
        if (isEmpty()) {
            throw new CollectionException("List is empty");
        }
        current = last;
    }

    @Override
    public void next() throws CollectionException {
        if (current == null || isEmpty()) {
            throw new CollectionException("Current is null");
        }
        current = current.next;
    }

    @Override
    public boolean hasNext() {
        if (current.next != null) {
            return true;
        }
        return current.next != last;
    }

    @Override
    public void addFirst(E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        Element<E> newElement = new Element<>(data);
        if (isEmpty()) {
            first = newElement;
            last = newElement;

        } else {
            newElement.next = first;
            first = newElement;
        }
        count++;
    }

    @Override
    public void addLast(E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            addFirst(data);
        } else {
            Element newElement = new Element(data);
            last.next = newElement;
            last = newElement;
            count++;
        }
    }

    @Override
    public void addAfterCurrent(E data) throws CollectionException {
        if (data == null) {
            throw new NullPointerException();
        }
        if (current == null) {
            throw new CollectionException("Neni nastaven aktualni prvek");
        }

        if (count == 1 || current == last) {
            addLast(data);
        } else if (count >= 2) {

            Element<E> novy = new Element<>(data);
            Element<E> newNext = current.next;
            current.next = novy;
            current.next.next = newNext;

            count++;
        }

    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public E getFirst() throws CollectionException {
        if (isEmpty()) {
            throw new CollectionException("List is empty");
        }
        return first.data;
    }

    @Override
    public E getLast() throws CollectionException {
        if (isEmpty()) {
            throw new CollectionException("List is empty");
        }
        return last.data;
    }

    @Override
    public E getCurrent() throws CollectionException {
        if (isEmpty()) {
            throw new CollectionException("List is empty");
        }
        if (current == null) {
            throw new CollectionException("Current is not set");
        }

        return current.data;
    }

    @Override
    public E getAfterCurrent() throws CollectionException {
        if (isEmpty()) {
            throw new CollectionException("List is empty");
        }
        if (current == null) {
            throw new CollectionException("Current is not set");
        }

        if (current.next.data == null) {
            throw new NullPointerException();
        }
        return current.next.data;
    }

    @Override
    public E removeFirst() throws CollectionException {
        if (isEmpty()) {
            throw new CollectionException("List is empty");
        }

        Element<E> save = first;

        if (count == 1) {
            clear();
        } else if (count > 1) {
            first = first.next;
            if (current == first) {
                current = null;
            }
            count--;
        }

        return save.data;
    }

    @Override
    public E removeLast() throws CollectionException {
        if (isEmpty()) {
            throw new CollectionException("List is empty");
        }

        Element<E> save = last;
        if (count == 1) {
            clear();
        } else if (count > 1) {
            Element<E> help = first;
            if (current == last) {
                current = null;
            }
            for (int i = 0; i < count; i++) {
                if (help.next == last) {
                    last = help;

                    last.next = null;
                    break;
                }
                help = help.next;
            }
            count--;
        }


        return save.data;
    }

    @Override
    public E removeCurrent() throws CollectionException {
        if (isEmpty() || current == null) {
            throw new CollectionException("Seznam je prazdny nebo neni nastaven aktualni prvek");
        }

        Element<E> save = current;
        Element<E> pom = first;

        for (int i = 0; i < count; i++) {
            if (pom.next == current) {
                pom.next = current.next;
                break;
            }
            pom = pom.next;
        }
        current = null;
        count--;
        return save.data;
    }

    @Override
    public E removeAfterCurrent() throws CollectionException {
        if (isEmpty() || current == null || current.next == null) {
            throw new CollectionException("Seznam je prazdny nebo neni nastaven aktualni prvek");
        }

        count--;
        Element<E> save = current.next;
        current.next = null;
        return save.data;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        current = null;
        first = null;
        last = null;
        count = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Element<E> dalsi = first;

            @Override
            public boolean hasNext() {
                return dalsi != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E data = dalsi.data;
                    dalsi = dalsi.next;
                    return data;
                } else {
                    return null;
                }

            }
        };
    }
}
