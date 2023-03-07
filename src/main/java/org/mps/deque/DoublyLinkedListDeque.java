package org.mps.deque;

public class DoublyLinkedListDeque<T> implements DoubleEndedQueue<T> {

    private DequeNode<T> first;
    private DequeNode<T> last;
    private int size;

    public DoublyLinkedListDeque(){
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void prepend(T value) {
        if (size == 0){
            DequeNode<T> node = new DequeNode<>(value,null, null);
            first = node;
            last = node;
        } else {
            DequeNode<T> node = new DequeNode<>(value,null, first);
            first.setPrevious(node);
            first = node;
        }
        size++;
    }

    @Override
    public void append(T value) {
        if (size == 0){
            DequeNode<T> node = new DequeNode<>(value,null, null);
            first = node;
            last = node;
        } else {
            DequeNode<T> node = new DequeNode<>(value, last, null);
            last.setNext(node);
            last = node;
        }
        size++;
    }

    @Override
    public void deleteFirst() {
        if (size == 0){
            throw new DoubleEndedQueueException("No se puede borrar de una cola vacía");
        }
        first = first.getNext();
        first.setPrevious(null);
        size--;
    }

    @Override
    public void deleteLast() {
        if (size == 0){
            throw new DoubleEndedQueueException("No se puede borrar de una cola vacía");
        }
        last = last.getPrevious();
        last.setNext(null);
        size--;
    }

    @Override
    public T first() {
        if (size == 0){
            throw new DoubleEndedQueueException("La cola está vacía");
        }
        return first.getItem();
    }

    @Override
    public T last() {
        if (size == 0){
            throw new DoubleEndedQueueException("La cola está vacía");
        }
        return last.getItem();
    }

    @Override
    public int size() {
        return size;
    }
}
