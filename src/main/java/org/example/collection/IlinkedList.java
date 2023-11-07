package org.example.collection;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This interface declares the methods of a simple linked list. Joint
 * list organizes inserted objects, data entities, into a linked series.
 * <p>
 * For a better understanding of the linked list principle, an analogy with loaded can be used
 * by freight train. Each wagon is connected to the following wagon and on each
 * the wagon is loaded with cargo. In our case, the payload is data of the given type
 * type parameter E and wagons are list elements.
 * <p>
 *
 * Description of the main sets of methods according to their function:
 *
 * <p>
 * In this interface, the signatures of the list traversal methods. And that's because it was
 * possible to reach any embedded data in the list. Those methods are
 * methods that set the current list element to the first position and then the next
 * method moves an imaginary pointer to the next element with data entities in
 * list. Another important method is the one that sets as the current data those
 * which are last so we can insert new data at the end of the list.
 * A variable with a reference to the current list element will be implemented as its
 * hidden attribute.
 * <p>
 * Additional methods handle inserting, taking, or removing data into or from a list
 * at the position of the current element.
 * <p>
 *
 * <b>Note</b>: This interface contains some methods of regular linear
 * list and some special ones, which are those that use the s implementation
 * the current element. You will meet other methods on the subject of data structures.
 *
 * <p>
 * Another set of methods allows you to find out the state of the list in the form of the current number of data,
 * or cancels all inserted data.
 * <p>
 * Last and most important is the method that supplies the iterator object that enables
 * traverse the data in an element-by-element manner without the risk of being
 * broken contiguous array of inner list elements from clients.
 * <p>
 * Implementation notes:
 * <p>
 * Linked list implementation can be based on the principle of specials
 * objects, which we will call an element. These elements create a list by
 * messages (methods) sent by clients to instances with this interface. These
 * special objects/elements are created according to statically nested class or
 * by inner class. Which option is better is a matter of discussion. One
 * variant is more efficient in terms of memory usage. Both variants have v
 * in our case two attributes. One attribute will be designated for association with
 * the following element of the list and the second attribute will be determined for connection with the data one
 * entity
 * <p>
 * If the data entity will be inserted at the end of the list, then in the current one
 * of the last element, a link to the newly created element will be inserted with a link to
 * a data entity object of type E and a reference to the next element will be null, indicating that
 * that is the end of the list.
 * <p>
 * A different, more complex, situation will be when we insert a new element before or after
 * the current element. The more complex methods are those that insert before the current one
 * element. Therefore, these methods already have default bodies defined here (new
 * property since Java 8). It is not necessary to implement these default methods.
 * The implementation of these methods can be chosen by more proficient students, provided that
 * the principle of a one-way connection list.
 *
 * Inserting data into the list does not change the position of the current element and does not set.
 *
 * <b>Implementation limitations</b>
 * <ol>
 * <li>Linked list must be unidirectional. That is, a helper inner class
 * The element contains only one link attribute to store the link towards
 * another instance of the Element class.
 * </ol>
 *
 * @param <E>
 */
public interface IlinkedList<E> extends Iterable<E> {

    /**
     * The move method setFirst() sets the internal current pointer to first
     * list dates.
     *
     * @throws CollectionException Exception thrown when the list is empty.
     */
    void setFirst() throws CollectionException;

    /**
     * The setLast() move method sets the internal current pointer to
     * last list dates.
     *
     * @throws CollectionException Exception thrown when the list is empty.
     */
    void setLast() throws CollectionException;

    /**
     * The move next() method resets the internal current pointer to the next element
     * list. In case the method finds that no element of v follows
     * list throws an exception.
     *
     * Warning: This method advances the internal pointer that is for given
     * an instance of its state components. The list instance is also traversable
     * by an iterator, which is a special object that will be created by
     * inner classes with Iterator interface. Each request for a list iterator
     * creates a new object with its own pointer to loop through the object's items
     * list.
     *
     * @throws CollectionException Exception thrown if not set
     * the current element or was the last element reached in the previous call.
     */
    void next() throws CollectionException;

    /**
     * The method checks if the following element,
     *
     * @return Returns true if the current element has a descendant. When,
     * that the pointer has moved to the last element of the list returns false.
     */
    boolean hasNext();
    /**
     * The insert method addFirst() inserts the data with the data entity first.
     *
     * The previous first data will be the second data in the list after the method is called.
     * Insertion does not change the position of the current element.
     *
     *
     * @param data A data entity of type E
     *
     * @throws NullPointerException Exception thrown when the method argument is
     * null.
     */
    void addFirst(E data);

    /**
     * The insertion method addLast() inserts a new element at the end of the list.
     *
     * If the list is empty, the data will be listed first. By inserting yourself
     * does not change the position of the current element.
     *
     * @param data A data entity of type E
     *
     * @throws NullPointerException Exception thrown when the method argument is
     * null.
     */
    void addLast(E data);

    /**
     * The insertion method vlozZaAktualni() inserts a new element with entities of type E after
     * the current element. The method must unlink the list and rejoin it via new
     * created element.
     *
     * Train analogy: Splitting the train into two parts. The second part after disconnection
     * is pulled to another track. A new freight car would join the first
     * parts and the other part would then rejoin behind the newly added wagon.
     * <p>
     * In our list, the link to the second part of the list will be saved in the new one
     * of the element and the new element is attached after the current one. While the pointer to the current
     * element does not change.
     *
     *
     * @param data A data entity of type E
     *
     * @throws CollectionException Exception thrown when current is not set
     * element.
     *
     * @throws NullPointerException Exception thrown when the method argument is
     * null.
     */
    void addAfterCurrent(E data) throws CollectionException;

    /**
     * Insertion method addBeforeCurrent() inserts a new element with data before the current one
     * element. This method is more complicated to implement because we are limited
     * by one-way sorting of list elements.
     *
     * That's why this method is declared as "default" and therefore has a definition
     * body already in the interface. This allows this method to be omitted in the implementation
     * class. Implementation is voluntary.
     *
     * @param data A data entity of type E
     *
     * @throws CollectionException Exception thrown when current is not set
     * element.
     *
     * @throws NullPointerException Exception thrown when the method argument is
     * null.
     */
    default void addBeforeCurrent(E data) throws CollectionException {
        throw new UnsupportedOperationException();
    }

    /**
     * The detection method isEmpty() checks if the list contains elements with data.
     *
     * @return Returns true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * The getFirst() accessor method returns a reference to a data entity of type E first
     * of a list element.
     *
     * @return A reference to a data entity of type E
     *
     * @throws CollectionException Exception thrown when the list is empty.
     */
    E getFirst() throws CollectionException;
    /**
     * The access method getLast() returns a reference to a data entity of type E
     * of the last element of the list.
     *
     * @return A reference to a data entity of type E
     *
     * @throws CollectionException Exception thrown when the list is empty.
     */
    E getLast() throws CollectionException;
    /**
     * The access method getCurrent() returns a reference to in the return value
     * object, a data entity of type E, of the currently set element by the movement methods
     * after the list.
     *
     * @return returns a reference to the object/data entity of type E from the current element
     * list.
     *
     * @throws CollectionException This exception is thrown when the list is empty
     * or when the current element is not set.
     */
    E getCurrent() throws CollectionException;

    /**
     * Access method getAfterCurrent() will return a reference to in the return value
     * object, a data entity of type E, behind the currently set element by methods
     * move through the list.
     *
     * @return returns a reference to the object/data entity of type E behind the current element
     * list.
     *
     * @throws CollectionException This exception is thrown when the list is empty
     * or when the current element is not set.
     */
    E getAfterCurrent() throws CollectionException;

    /**
     * The accessor method getBeforeCurrent() returns a reference to in the return value
     * object, a data entity of type E that precedes the currently set element
     * methods of moving through the list.
     *
     * @return returns a reference to the object/data entity of type E behind the current element
     * list.
     *
     * This method is more complicated to implement because we are limited
     * by one-way sorting of list elements. Therefore, this method is declared as
     * "default" and therefore has a defined body already in the interface. It allows
     * omit this method in the implementation class. Implementation is voluntary.
     *
     * @throws CollectionException This exception is thrown when the list is empty
     * or when the current element is not set.
     */
    default E getBeforeCurrent() throws CollectionException {
        throw new UnsupportedOperationException();
    }

    /**
     * The removeFirst() method removes the first element from the list.
     *
     * The method changes the current number of objects in the list.
     *
     * After removing the first element, the pointer to the current element does not change if
     * was not set to the first, i.e., data to be received. When the first element was
     * at the same time current, so the pointer to the current element will be after removal
     * undefined.
     *
     * @return Returns a reference to the subscribed object, a data entity of type E
     *
     * @throws CollectionException this exception is thrown when the list was
     * empty.
     */
    E removeFirst() throws CollectionException;

    /**
     * The removeLast() method removes the last element from the list.
     *
     * The method changes the current number of objects in the list.
     *
     * After removing the last element, the pointer to the current element does not change unless
     * was not set to the last, i.e. removed, element. When the last element was
     * at the same time current, so the pointer to the current element will be after removal
     * undefined.
     *
     * @return Returns a reference to the subscribed object, a data entity of type E
     *
     * @throws CollectionException this exception is thrown when the list was
     * empty.
     */
    E removeLast() throws CollectionException;

    /**
     * The removeCurrent() method removes the current element from the list and secures it
     * continuous linking of the remaining elements of the list.
     *
     * The method changes the current number of objects in the list.
     *
     * When the current element is removed, the internal pointer will be to the current one
     * undefined.
     *
     * @return Returns a reference to the subscribed object, a data entity of type E
     *
     * @throws CollectionException this exception is thrown when it has not been set
     * the current element or the list was empty.
     */
    E removeCurrent() throws CollectionException;

    /**
     * The removeAfterCurrent() method removes the element from the list after the current element a
     * ensures a continuous connection of the remaining elements of the list.
     *
     * The method changes the number of objects in the list.
     *
     * @return Returns a reference to the subscribed object, a data entity of type E
     *
     * @throws CollectionException this exception is thrown when it has not been set
     * the current element or the list was empty.
     */
    E removeAfterCurrent() throws CollectionException;

    /**
     * The removeBeforeCurrent() method removes an element from the list that is before
     * the current one and ensures a continuous connection of the rest of the list elements.
     *
     * The method changes the number of data in the list.
     *
     * @return Returns a reference to the subscribed object, a data entity of type E
     *
     * @throws CollectionException this exception is thrown when it has not been set
     * the current element or when the list was empty.
     */
    default E removeBeforeCurrent() throws CollectionException {
        throw new UnsupportedOperationException();
    }

    /**
     * The size() method returns the current number of data in the list
     *
     * @return Returns a value with the number of data in the list.
     */
    int size();

    /**
     * The clear() method removes all data from the list.
     */
    void clear();

    /**
     * The method will convert the contents of the list to a stream to pass on return.
     *
     * This method does not overlap.
     *
     * @return the data stream
     */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
