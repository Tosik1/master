package double_linked_list;

import java.util.LinkedList;
import java.util.Objects;

public class DoubleLinkedList<T> {
    private ListItem<T> head;
    private ListItem<T> tail;
    private int size;

    public ListItem<T> popHeadElement() {
        ListItem<T> item = head;
        if (head != null){
            head = head.next;
            item.next = null;
            size--;
        }
        return item;
    }

    public ListItem<T> popTailElement() {
        ListItem item = tail;
        if (head != null){
            tail = tail.prev;
            item.prev = null;
            size--;
        }
        return item;
    }

    public void removeHeadElement() {
        if (head != null){
            head = head.next;
            size--;
        }
    }

    public void removeTailElement() {
        if (head != null){
            tail = tail.prev;
            size--;
        }
    }

    public void addToHead(T data) {
        if (head == null){
            head = new ListItem<>(data);
            tail = head;
        }
        else{
            ListItem item = new ListItem(data);
            item.prev = null;
            item.next = head;
            head.prev = item;
            head = item;
        }
        size++;
    }

    public void addToTail(T data) {
        if (head == null){
            head = new ListItem<>(data);
            tail = head;
        }
        else {
            ListItem item = new ListItem(data);
            item.prev = tail;
            item.next = null;
            tail.next = item;
            tail = item;
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    public ListItem<T> getHeadElement() {
        return head;
    }

    public ListItem<T> getTailElement() {
        return tail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleLinkedList<T> that = (DoubleLinkedList<T>) o;
        return Objects.equals(head, that.head) && Objects.equals(tail, that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail);
    }

    @Override
    public String toString() {
        if (head == null) {
            return "DoubleLinkedList is empty size = " + size;
        }

        StringBuilder stringBuilder = new StringBuilder(head.toString());
        ListItem<T> item = head;
        while (item.next != null) {
            if (item.next.prev == item) {
                stringBuilder.append("<-");
            }

            stringBuilder.append(" -> ").append(item.next);
            item = item.next;
        }

        return "DoubleLinkedList{size=" + size + "\n" + stringBuilder.toString() + "}";
    }
}