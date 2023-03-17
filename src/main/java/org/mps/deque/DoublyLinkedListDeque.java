package org.mps.deque;

import java.util.Comparator;
import java.util.Deque;

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
            throw new DoubleEndedQueueException("No se puede borrar de una cola vacia");
        }
        first = first.getNext();
        first.setPrevious(null);
        size--;
    }

    @Override
    public void deleteLast() {
        if (size == 0){
            throw new DoubleEndedQueueException("No se puede borrar de una cola vacia");
        }
        last = last.getPrevious();
        last.setNext(null);
        size--;
    }

    @Override
    public T first() {
        if (size == 0){
            throw new DoubleEndedQueueException("La cola esta vacia");
        }
        return first.getItem();
    }

    @Override
    public T last() {
        if (size == 0){
            throw new DoubleEndedQueueException("La cola esta vacia");
        }
        return last.getItem();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {

        if (index>=size || index<0){
            throw new DoubleEndedQueueException("El indice esta fuera de limites");
        }
        /**
        int cnt;
        DequeNode<T> node = first;
        if(size/2 > index){
            cnt = 0;
            while(cnt != index){
                node = node.getNext();
                cnt++;
            }
        } else {
            cnt = size-1;
            while(cnt != index){
                node = node.getPrevious();
                cnt--;
            }
        }
        return node.getItem();
         */
        DequeNode<T> node = first;
        int cnt = 0;
        for(int i = 0; i < index; i++){
            node = node.getNext();
        }
        return node.getItem();
    }

    @Override
    public boolean contains(T value) {
        int cnt = 0;
        DequeNode<T> node = first;
        while(cnt < size && !node.getItem().equals(value)){
        //while(cnt < size && node.getItem() != value){
            node = node.getNext();
            cnt++;
        }
        return cnt == size ? false : true;
    }

    @Override
    public void remove(T value) {
        if (!contains(value)){
            throw new DoubleEndedQueueException("El elemento no se encuentra en la cola");
        }
        DequeNode<T> node = first;
        while(!node.getItem().equals(value)){
            node = node.getNext();
        }

        // Is first
        if(node.getPrevious() == null){
            deleteFirst();
        }

        // Is last
        else if (node.getNext() == null){
            deleteLast();
        }

        else {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
            size--;
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        DequeNode<T> node = first;
        DequeNode<T> next = node.getNext();
        for (int i = 0; i < size - 1; i++) {
            if (comparator.compare(node.getItem(), next.getItem()) > 0) {
                sort(comparator,next);
            }
            node = node.getNext();
            next = node.getNext();
        }
    }

    private void sort(Comparator<? super T> comparator, DequeNode<T> node){
        T aux;
        DequeNode<T> previous = node.getPrevious();
        if(node.getPrevious() != null && comparator.compare(node.getPrevious().getItem(), node.getItem()) > 0){
            aux = previous.getItem();
            previous.setItem(node.getItem());
            node.setItem(aux);
            sort(comparator, previous);
        }
    }
}

