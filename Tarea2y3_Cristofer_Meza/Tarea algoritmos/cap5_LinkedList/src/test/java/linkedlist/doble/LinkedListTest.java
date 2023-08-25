package java.linkedlist.doble.;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    private linkedlist.doble.LinkedList<Integer> lista;
    private linkedlist.doble.LinkedList.Nodo<Integer> n;
    @Test
    void testAppendVarios() {
        lista.append(10);
        lista.append(20);
        lista.append(30);

        assertEquals(3, lista.length());
        lista.moveToStart();
        assertEquals(10, lista.getValue());

        lista.moveToEnd();
        assertEquals(30, lista.getValue());
    }

    @Test
    void testNavegaciones() {
        lista.append(10);
        lista.append(20);
        lista.append(30);

        lista.moveToStart();
        assertFalse(lista.isAtEnd());

        lista.moveToEnd();
        assertTrue(lista.isAtEnd());

        lista.prev();
        assertEquals(20, lista.getValue());

        lista.next();
        assertEquals(30, lista.getValue());

        lista.next();
        assertEquals(30, lista.getValue());

        lista.moveToStart();
        lista.prev();
        assertEquals(10, lista.getValue());

        lista.moveToPos(1);
        assertEquals(20, lista.getValue());

        assertFalse(lista.moveToPos(100));
    }
}