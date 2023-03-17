package org.mps.deque;

import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.*;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Una DoublyLinkedListDeque")
class DoublyLinkedListDequeTest {

    DoublyLinkedListDeque<String> queue;

    @Test
    @DisplayName("Se inicializa con new DoublyLinkedListDeque()")
    void seCreaNuevaCola(){
        new DoublyLinkedListDeque<>();
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
        @DisplayName("esta vacia")
        void isEmpty(){
            assertTrue(queue.size() == 0);
        }

        @Test
        @DisplayName("lanza DoubleEndedeQueueException cuando deleteFirst()")
        void excepcionConDeleteFirst() {
            assertThrows(DoubleEndedQueueException.class, ()-> queue.deleteFirst(),"No se puede borrar de una cola vacia");
        }

        @Test
        @DisplayName("lanza DoubleEndedeQueueException cuando deleteLast()")
        void excepcionConDeleteLast() {
            assertThrows(DoubleEndedQueueException.class, ()-> queue.deleteLast(),"No se puede borrar de una cola vacia");
        }

        @Test
        @DisplayName("lanza DoubleEndedeQueueException cuando first()")
        void excepcionConFirst(){
            assertThrows(DoubleEndedQueueException.class, ()->queue.first(), "La cola esta vacia");
        }

        @Test
        @DisplayName("lanza DoubleEndedeQueueException cuando last()")
        void excepcionConLast(){
            assertThrows(DoubleEndedQueueException.class, ()->queue.last(), "La cola esta vacia");
        }

        @Test
        @DisplayName("lanza DoubleEndedQueueException cuando get(0)")
        void excepcionConGet(){
            assertThrows(DoubleEndedQueueException.class,()->queue.get(0), "El índice esta fuera de limites");
        }

        @Test
        @DisplayName("contains(elemento) devuelve false")
        void containsReturnsFalse(){
            assertFalse(queue.contains("elemento"));
        }

        @Test
        @DisplayName("remove(elemento) lanza DoubleEndedQueueException")
        void excepcionConRemove(){
            assertThrows(DoubleEndedQueueException.class,()->queue.remove("elemento"), "El elemento no se encuentra en la cola");
        }

        @Nested
        @DisplayName("despues del primer append")
        class afterAppend {
            String elemento = "elemento";

            @BeforeEach
            void appendElement(){
                queue.append(elemento);
            }

            @Test
            @DisplayName("ya no esta vacia")
            void isNotEmpty(){
                assertFalse(queue.size()==0);
            }

            @Test
            @DisplayName("el primer elemento es el insertado")
            void firstElement() {
                assertEquals(elemento, queue.first());
            }

            @Test
            @DisplayName("el ultimo elemento es el insertado")
            void lastElement() {
                assertEquals(elemento, queue.last());
            }

            @Test
            @DisplayName("get(0) devuelve el elemento añadido")
            void getElement(){
                assertEquals(elemento,queue.get(0));
            }

            @Test
            @DisplayName("get(-1) lanza una DoubleEndedQueueException")
            void getNegativeElementReturnsException() {
                assertThrows(DoubleEndedQueueException.class,()->queue.get(-1), "El indice esta fuera de limites");
            }

            @Test
            @DisplayName("get(1) lanza una DoubleEndedQueueException")
            void getIndexOutOfBoundsReturnsException(){
                assertThrows(DoubleEndedQueueException.class,()->queue.get(1), "El indice esta fuera de limites");
            }

            @Test
            @DisplayName("contains(elemento) devuelve true")
            void containsReturnsTrue(){
                assertTrue(queue.contains("elemento"));
            }

            @Nested
            @DisplayName("despues de insertar el segundo elemento")
            class secondAppend {
                String segundo = "segundo";
                @BeforeEach
                void appendSecond() {
                    queue.append(segundo);
                }
                @Test
                @DisplayName("el primer elemento es el anterior (elemento)")
                void firstElementDoesNotChange() {
                    assertEquals(elemento, queue.first());
                }

                @Test
                @DisplayName("el ultimo elemento es el nuevo (segundo)")
                void lastElementIsNew() {
                    assertEquals(segundo, queue.last());
                }

                @Test
                @DisplayName("sort() no hace nada porque la lista ya esta ordenada")
                void listIsSorted(){
                    Comparator<String> comparator = new Comparator<String>() {
                        @Override
                        public int compare(String s1, String s2) {
                            return s1.compareTo(s2);
                        }
                    };
                    queue.sort(comparator);
                    assertEquals("elemento", queue.get(0));
                    assertEquals("segundo",queue.get(1));
                }

                @Nested
                @DisplayName("despues de remove(elemento)")
                class afterRemoveElemento{
                    @BeforeEach
                    void removeElemento(){queue.remove("elemento");}

                    @Test
                    @DisplayName("la cola tiene tamaño 1")
                    void sizeIsOne(){
                        assertEquals(1,queue.size());
                    }

                    @Test
                    @DisplayName("el primer elemento es 'segundo'")
                    void secondIsFirst() {
                        assertEquals(queue.first(), segundo);
                    }
                }

                @Nested
                @DisplayName("despues de deleteFirst()")
                class afterDeleteFirst{
                    @BeforeEach
                    void deleteElement(){queue.deleteFirst();}

                    @Test
                    @DisplayName("la cola tiene tamaño 1")
                    void deleteFirstWorks(){
                        assertEquals(1,queue.size());
                    }

                    @Test
                    @DisplayName("el primer elemento es 'segundo'")
                    void secondIsFirst() {
                        assertEquals(queue.first(), segundo);
                    }
                }

                @Nested
                @DisplayName("despues de insertar el tercer elemento")
                class afterThirdAppend{
                    @BeforeEach
                    void appendThird(){queue.append("primero");}

                    @Test
                    @DisplayName("sort() ordena el ultimo elemento añadido")
                    void sortToTheCorrectPosition() {
                        Comparator<String> comparator = new Comparator<String>() {
                            @Override
                            public int compare(String s1, String s2) {
                                return s1.compareTo(s2);
                            }
                        };
                        queue.sort(comparator);
                        assertEquals("primero", queue.get(1));
                    }

                    @Test
                    @DisplayName("al insertar un nuevo elemento '1', sort() lo ordena en primera posicion")
                    void sortToFirstPosition(){
                        queue.append("1");
                        Comparator<String> comparator = new Comparator<String>() {
                            @Override
                            public int compare(String s1, String s2) {
                                return s1.compareTo(s2);
                            }
                        };
                        queue.sort(comparator);
                        assertEquals("1", queue.get(0));
                    }

                    @Nested
                    @DisplayName("despues de remove(segundo)")
                    class afterRemoveSegundo{

                        @BeforeEach
                        void removeSegundo(){queue.remove("segundo");}

                        @Test
                        @DisplayName("la cola tiene tamaño 2")
                        void sizeIsTwo(){
                            assertEquals(2,queue.size());
                        }

                        @Test
                        @DisplayName("'primero' pasa a ser el segundo elemento")
                        void thirdIsSecond() {
                            assertEquals("primero",queue.get(1));
                        }

                        @Test
                        @DisplayName("'elemento' continua siendo el primer elemento")
                        void firstIsFirst(){
                            assertEquals("elemento", queue.get(0));
                        }
                    }
                }
            }
        }

        @Nested
        @DisplayName("despues del primer prepend")
        class afterPrepend {
            String elemento = "elemento";

            @BeforeEach
            void prependElement() {
                queue.prepend(elemento);
            }

            @Test
            @DisplayName("Ya no esta vacia")
            void isNotEmpty(){
                assertFalse(queue.size() == 0);
            }

            /**
             * Como solo hemos insertado un elemento el first y last deben de ser dicho elemento
             */

            @Test
            @DisplayName("El primer elemento es el insertado")
            void firstElement() {
                assertEquals(elemento, queue.first());
            }

            @Test
            @DisplayName("El ultimo elemento es el insertado")
            void lastElement() {
                assertEquals(elemento, queue.last());
            }

            @Nested
            @DisplayName("despues de insertar el segundo elemento")
            class secondPrepend {
                String segundo = "segundo";
                @BeforeEach
                void prependSecond() {
                    queue.prepend(segundo);
                }
                @Test
                @DisplayName("el primer elemento es el anterior (elemento)")
                void lastElementDoesNotChange() {
                    assertEquals(elemento, queue.last());
                }

                @Test
                @DisplayName("el ultimo elemento es el nuevo (segundo)")
                void firstElementIsNew() {
                    assertEquals(segundo, queue.first());
                }

                @Nested
                @DisplayName("despues de remove(elemento)")
                class afterRemoveSegundo{
                    @BeforeEach
                    void deleteElement(){queue.remove("elemento");}

                    @Test
                    @DisplayName("la cola tiene tamaño 1")
                    void sizeIsOne(){
                        assertEquals(1,queue.size());
                    }

                    @Test
                    @DisplayName("el ultimo elemento es 'segundo'")
                    void secondIsFirst() {
                        assertEquals(queue.last(), segundo);
                    }
                }

                @Nested
                @DisplayName("despues de deleteLast()")
                class afterDeleteFirst{
                    @BeforeEach
                    void deleteElement(){queue.deleteLast();}

                    @Test
                    @DisplayName("la cola tiene tamaño 1")
                    void deleteLastWorks(){
                        assertEquals(1,queue.size());
                    }

                    @Test
                    @DisplayName("el ultimo elemento es 'segundo'")
                    void secondIsFirst() {
                        assertEquals(queue.last(), segundo);
                    }
                }
            }
        }
    }
}