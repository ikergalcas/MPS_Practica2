package org.mps.deque;

import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Una DoublyLinkedListDeque")
class DoublyLinkedListDequeTest {

    /*
    *
    *
    *
     */

    DoublyLinkedListDeque<Object> queue;

    @Test
    @DisplayName("se inicializa con DoublyLinkedListDequeTest()")
    void seCreaNuevaCola(){
        new DoublyLinkedListDequeTest();
    }

    @Nested
    @DisplayName("cuando se crea una nueva cola")
    class WhenNew {
        @BeforeEach
        void setUp(){
            queue = new DoublyLinkedListDeque<>();
        }

        @AfterEach
        void shutDown(){
            queue = null;
        }

        @Test
        @DisplayName("está vacía")
        void isEmpty(){
            assertTrue(queue.size() == 0);
        }

        @Test
        @DisplayName("lanza DoubleEndedeQueueException cuando deleteFirst()")
        void excepcionConDeleteFirst() {
            assertThrows(DoubleEndedQueueException.class, ()-> queue.deleteFirst(),"No se puede borrar de una cola vacía");
        }

        @Test
        @DisplayName("lanza DoubleEndedeQueueException cuando deleteLast()")
        void excepcionConDeleteLast() {
            assertThrows(DoubleEndedQueueException.class, ()-> queue.deleteLast(),"No se puede borrar de una cola vacía");
        }

        @Test
        @DisplayName("lanza DoubleEndedeQueueException cuando first()")
        void excepcionConFirst(){
            assertThrows(DoubleEndedQueueException.class, ()->queue.first(), "La cola está vacía");
        }

        @Test
        @DisplayName("lanza DoubleEndedeQueueException cuando last()")
        void excepcionConLast(){
            assertThrows(DoubleEndedQueueException.class, ()->queue.last(), "La cola está vacía");
        }
    }
}