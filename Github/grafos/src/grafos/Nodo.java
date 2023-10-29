/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

/**
 *
 * @author LStanislao
 * @param <T>
 */
public class Nodo<T> {

    private T data;
    private Nodo next;

    /**
     *
     * @param datum
     */
    public Nodo(T datum) {
        this.data = datum;
        this.next = null;
    }

    /**
     * Get Node's data
     *
     * @return the data inside Node
     */
    public T getData() {
        return data;
    }

    /**
     * Sets new data to Node
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     *
     * @return a reference to the next Node
     */
    public Nodo getNext() {
        return next;
    }

    /**
     * Sets new Node to reference to
     *
     * @param next
     */
    public void setNext(Nodo next) {
        this.next = next;
    }

}
