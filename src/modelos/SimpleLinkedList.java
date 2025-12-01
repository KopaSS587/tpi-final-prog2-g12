package src.modelos;

import java.util.ArrayList;
import java.util.List;

public class SimpleLinkedList<T> {


    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private int size = 0;

    public void insertAtEnd(T data) {
        Node<T> nuevo = new Node<>(data);

        if (head == null) {
            head = nuevo;
        } else {
            Node<T> aux = head;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = nuevo;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) return null;

        Node<T> aux = head;
        int i = 0;

        while (aux != null) {
            if (i == index) return aux.data;
            aux = aux.next;
            i++;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public List<T> toList() {
        List<T> lista = new ArrayList<>();
        Node<T> aux = head;

        while (aux != null) {
            lista.add(aux.data);
            aux = aux.next;
        }

        return lista;
    }


    public String toString() {
        return toList().toString();
    }
}