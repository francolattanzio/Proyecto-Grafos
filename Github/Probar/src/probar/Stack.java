/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probar;

/**
 *
 * @author user
 */
public class Stack<T> {

    private Nodo<T> head;

    /**
     *
     */
    public Stack() {
        this.head = null;
    }

    /**
     *
     * @param datum
     */
    public Stack(T datum) {
        Nodo<T> n = new Nodo(datum);
        this.head = n;
    }

    boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Adds a new element to the Stack
     *
     * @param datum The element to be added to the Stack
     * @complexity O(1)
     */
    public void push(T datum) {
        Nodo<T> n = new Nodo(datum);
        if (isEmpty()) {
            this.head = n;
        } else {
            n.setNext(this.head);
            this.head = n;
        }
    }

    /**
     * Deletes the last element in the Stack and returns its data
     *
     * @return The data of the last element in the Stack
     * @complexity O(1)
     */
    public T pop() {
        if (isEmpty()) {
            return null;
        }

        Nodo<T> temp = this.head;
        this.head = temp.getNext();
        temp.setNext(null);
        return temp.getData();
    }

    /**
     * Gets the data contained in the last element in the Stack
     *
     * @return The Data of the last element in the Stack
     * @complexity O(1)
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }

        Nodo<T> temp = this.head;
        return temp.getData();
    }

    public void print() {
        T aux;

        if (isEmpty()) {
        } else {

            aux = pop();
            System.out.println(aux);
            print();
            push(aux);

        }

    }

    public Stack reverse(Stack origin, Stack copy) {

        T aux;

        if (!isEmpty()) {
            aux = peek();
            pop();
            copy.push(aux);
            reverse(origin, copy);
            origin.push(aux);

        }

        return copy;

    }

    public Stack copy(Stack origin, Stack result) {
        T aux;

        if (!isEmpty()) {
            aux = peek();
            pop();
            copy(origin, result);
            result.push(aux);

            origin.push(aux);

        }

        return result;
    }
    


}
