package java.linkedlist.simple.v2;

import linkedlist.List;
import java.util.NoSuchElementException;

public class SimpleLinkedList<T> implements List<T> {
    // Atributos de la lista enlazada;
    private Nodo<T> head;  // apunta el primer nodo
    private Nodo<T> tail;  // apunta al ultimo nodo
    private int size;  // cantidad de nodos

    private Nodo<T> curr;  // posición del nodo actual

    public SimpleLinkedList() {
        clear();
    }

    private boolean insertFirstNode(Nodo<T> n) {
        this.head = n;
        this.tail = n;
        this.size++;
        return true;
    }

    @Override
    public void clear() {
        curr = tail = new Nodo<T>(null, null); // Create trailer
        head = new Nodo<T>(tail);        // Create header
        size = 0;
    }

    @Override
    public boolean insert(T it) {
        //Crear un nuevo nodo siguiente de curr
       curr.setsiguiente(new Nodo<>(curr.getDato(), curr.siguiente));
       curr.setdato(it);
       if (tail == curr){
           tail = tail.siguiente; // nuevo ultimo nodo
       }
       size++;
       return true;
    }

    @Override
    public boolean append(T it) {
        var newNode = new Nodo<>(it);

        if (this.isEmpty()) {
            return this.insertFirstNode(newNode);
        }

        tail.siguiente = newNode;
        tail = newNode;

        size++;
        return true;
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (curr == tail){
            throw new NoSuchElementException();
        }

        T it = curr.dato;// guardar el valor del nodo a eliminar
        // obtengo el dato del nodo siguiente al curr y cambio el valor del dato del curr
        curr.setdato(curr.siguiente.getDato());
        if (curr.siguiente == tail){
            tail = curr;
        }
        // ahora el curr apunta al nodo siguiente del siguiente
        curr.setsiguiente(curr.siguiente.siguiente);
        //disminuimos la cantidad de nodos
        size--;
        // retornamos el valor del nodo eliminado
        return  it;

    }

    @Override
    public void moveToStart() {
        curr = head;
    }

    @Override
    public void moveToEnd() {
        curr = tail;
    }

    @Override
    public void prev() {
        if (head.siguiente == curr){
            return;
        }
        Nodo<T> aux = head;
        while (aux.siguiente != curr){
            aux = aux.siguiente;
        }
        curr = aux;
    }

    @Override
    public void next() {
        if(curr != tail){
            curr = curr.siguiente;
        }
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public int currPos() {
        Nodo<T> aux = head.siguiente;
        int i;
        for (i=0;curr != aux;i++){
            aux = aux.siguiente;
        }
        return i;
    }

    @Override
    public boolean moveToPos(int pos) {
        if (pos < 0 || pos >= size) {
            return false;
        }
        // colocar el curr al inicio de la lista
        curr = head.siguiente;
        // recorrer toda la lista hasta llegar al pos
        for (int i=0;i<pos;i++){
            curr = curr.siguiente;
        }
        return true;
    }

    @Override
    public boolean isAtEnd() {
        return curr == tail;
    }

    @Override
    public T getValue() throws NoSuchElementException {
        if (curr == tail) {
            throw new NoSuchElementException();
        }
        return curr.dato;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        public T getDato() {

            return dato;
        }
        // constructor
         Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
        //constructor
         Nodo(T dato,Nodo<T> n) {
            this.dato = dato;
            this.siguiente = n;
        }
        //constructor
        Nodo(Nodo<T> n){
            this.dato = null;
            this.siguiente = n;
        }
        public T setdato(T it){
            this.dato = it;
            return it;
        }
        public Nodo<T> setsiguiente(Nodo<T> n){
            return siguiente = n;
        }

        @Override
        public String toString() {
            return dato.toString();
        }

    }
}