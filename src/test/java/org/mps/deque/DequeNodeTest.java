package org.mps.deque;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Un DequeNode")
class DequeNodeTest {

    DequeNode<Object> node;

    @Test
    @DisplayName("se inicializa con new DequeNode()")
    void newDequeNode() {
        new DequeNode<>(null, null, null);
    }

    @Nested
    @DisplayName("se crea un nuevo DequeNode asignandole solo el item (previous = next = null)")
    class whenNew {
        @BeforeEach
        void setUp() {
            node = new DequeNode<>("item", null, null);
        }

        /**
         * Como hemos definido queue sin nodos previo ni siguiente, este es el primer y ultimo nodo de la cola
         */

        @Test
        @DisplayName("node es el primer nodo de la cola")
        void queueIsFirst() {
            assertTrue(node.isFirstNode());
        }

        @Test
        @DisplayName("node es el ultimo nodo de la cola")
        void queueIsLast() {
            assertTrue(node.isLastNode());
        }

        @Test
        @DisplayName("se puede cambiar el elemento del nodo")
        void setItemWorks(){
            node.setItem("newItem");
            assertEquals(node.getItem(),"newItem");
        }

        @Nested
        @DisplayName("asignamos un nodo previo")
        class previousSetted {
            DequeNode<Object> previo = new DequeNode("previo", null, node);

            @BeforeEach
            void setPrev() {
                node.setPrevious(previo);
            }

            @Test
            @DisplayName("node ya no es el primer nodo de la cola")
            void queueNotFirst() {
                assertFalse(node.isFirstNode());
            }

            @Test
            @DisplayName("previo es el primer nodo")
            void prevIsFirst() {
                assertTrue(previo.isFirstNode());
            }

            @Test
            @DisplayName("node sigue siendo el ultimo nodo")
            void queueStillLast() {
                assertTrue(node.isLastNode());
            }

            @Test
            @DisplayName("ambos son nodos terminales")
            void bothTerminal() {
                assertFalse(node.isNotATerminalNode());
                assertFalse(previo.isNotATerminalNode());
            }

            @Nested
            @DisplayName("asignamos un nodo siguiente a queue")
            class nextSetted{
                DequeNode<Object> sig = new DequeNode<>("siguiente", node, null);

                @BeforeEach
                void setNext() {
                    node.setNext(sig);
                }

                @Test
                @DisplayName("node ya no es el ultimo nodo de la cola")
                void queueNotLast() {
                    assertFalse(node.isLastNode());
                }

                @Test
                @DisplayName("sig es el ultimo nodo")
                void sigIsFirst() {
                    assertTrue(sig.isLastNode());
                }

                @Test
                @DisplayName("node ya no es un nodo terminal")
                void queueNotTerminal() {
                    assertTrue(node.isNotATerminalNode());
                }
            }
        }
    }
}