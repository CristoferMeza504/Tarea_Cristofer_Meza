package linkedlist.doble;
/*
En este caso se usa un Nodo con doble enlace: uno que apunta al siguiente y
otro al anterior.

Reutilizar los métodos existentes pensando en mantener en las operaciones de
inserción y remoción ambos enlaces.
 */

import linkedlist.List;


import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
    // Atributos de la lista enlazada;
    private Nodo<T> head;  // apunta el primer nodo
    private Nodo<T> tail;  // apunta al ultimo nodo
    private int size;  // cantidad de nodos

    private Nodo<T> curr;  // posicion del nodo actual

    public LinkedList() {
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
        head = tail;
        curr = tail = new Nodo<T>(null,null,null);
        size = 0;
    }

    @Override
    public boolean insert(T it) {
        // agregar el nuevo nodo en la lista
        curr = new Nodo<T>(it,curr.getSiguiente(),curr.getAnterior());
        // el anterior nodo a curr apunte hacia la derecha al nuevo curr
        curr.getAnterior().setSiguiente(curr);
        // el nodo siguiente a curr apunte hacia la izquierda al nuevo curr
        curr.getSiguiente().setAnterior(curr);
        //incrementar el tamaño de los nodos
        this.size++;

        return true;
    }

    @Override
    public boolean append(T it) {
        // creamos un nuevo nodo al final de la lista
        tail.setAnterior(new Nodo<>(it,tail,tail.getAnterior()));
        // el nodo anterior al anterior de tail que apunte al anterior a tail
        tail.getAnterior().getAnterior().setSiguiente(tail.getAnterior());
        //si el curr apunta al tail entonces ahora apunta al anterior a tail
        if (curr == tail){
            curr = tail.getAnterior();
        }
        //incrementar el tamaño de los nodos
        this.size++;
        return true;

    }

    @Override
    public T remove() {
        // si curr esta en el ultimo nodo entonces no tiene ningún dato
        if (curr == tail){
            return null;
        }
        // recolectar el dato del nodo
        T e = curr.getDato();
        // el nodo anterior a curr apunte al nodo siguiente de curr
        curr.getAnterior().setSiguiente(curr.getSiguiente());
        // el nodo siguiente del curr apunte al nodo anterior al curr
        curr.getSiguiente().setAnterior(curr.getAnterior());
        // el nuevo curr
        curr= curr.getSiguiente();
        //tamaño de los nodos disminuye
        this.size--;
        // retorna el dato del nodo eliminado
        return e;
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
        if(head.getAnterior() != head){
            curr.getAnterior();
        }
    }

    @Override
    public void next() {
        if (curr != tail){
            curr.getSiguiente();
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
        curr = head.getSiguiente();
        // recorrer toda la lista hasta llegar al pos
        for (int i=0;i<pos;i++){
            curr = curr.getSiguiente();
        }
        return true;
    }

    @Override
    public boolean isAtEnd() {
        return curr == tail;
    }

    @Override
    public T getValue() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return curr.getDato();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public static class Nodo<T> {
        private  T dato;
        private Nodo<T> siguiente;  // enlace al siguiente nodo
        private Nodo<T> anterior;  // enlace al nodo previo

        // constructor cuando no hay nodos
        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
            this.anterior = null;
        }
        //constructor cuando ya hay nodos
        public Nodo(T d, Nodo<T> s, Nodo<T> a){
            this.dato = d;
            this.siguiente = s;
            this.anterior = a;
        }
        public Nodo( Nodo<T> s, Nodo<T> a){
            this.siguiente = s;
            this.anterior = a;
        }

        //funciones

        public T getDato() {
            return dato;
        }

        public  T setDato(T d){
            return this.dato =d;
        }

        public Nodo<T> getSiguiente(){
            return this.siguiente;
        }

        public Nodo<T> setSiguiente(Nodo<T> s){
            return this.siguiente =s;
        }
        public  Nodo<T> getAnterior(){
            return this.anterior;
        }
        public Nodo<T> setAnterior(Nodo<T> a){
            return this.anterior = a;
        }





        @Override
        public String toString() {
            return dato.toString();
        }

    }

}