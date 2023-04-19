import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class CircularQueue<T> implements Queue<T> {
    
    class Node {
        T value;
        Node next;

        Node(T value) { this.value = value; }
        
        Node(T value, Node next) { 
            this.value = value; 
            this.next = next; 
        }
        
        @Override
        public String toString() { return value.toString(); }
    }

    Node head = null;
    int size = 0;

    @Override
    public int size() { return size; }

    @Override
    public boolean contains(Object o) {
        Node start = head;
        do {
            if (start.value.equals(o)) return true;
            start = start.next;
        } while (start != head);        
        
        return false;
    }

    @Override
    public Object[] toArray() {
        Node start = head;
        List<T> list = new ArrayList<>();
        do {
            list.add(start.value);
            start = start.next;
        } while (start != head);
        return list.toArray();
    }

    @Override
    public boolean remove(Object o) {
        Node start = head;
        do {
            if (start.next.value.equals(o)) {
                start.next = start.next.next;
                size--;
                return true;
            }
            start = start.next;
        } while (start != head);
        
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) if (!contains(o)) return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        for (T t : c) changed = changed || add(t);
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) changed = changed || remove(o);
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) if (!contains(o)) changed = changed || remove(o);
        return changed;
    }

    @Override
    public void clear() { head = null; }

    @Override
    public boolean add(T e) {
        if (head == null) {
            head = new Node(e);
            head.next = head;
        } else {
            Node start = head;
            do {
                if (start.next == head) {
                    start.next = new Node(e, head);
                    break;
                }
                start = start.next;
            } while (start != head);
        }
        size++;
        return true;
    }

    @Override
    public boolean offer(T e) { return add(e); }

    @Override
    public T remove() {
        if (head == null) throw new IllegalStateException("Queue is empty");
        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    @Override
    public T poll() { 
        try { return remove(); }
        catch (IllegalStateException e) { return null; }
    }

    @Override
    public T element() {
        if (head == null) throw new IllegalStateException("Queue is empty");
        return head.value;
    }

    @Override
    public boolean isEmpty() { return head == null; }

    @Override
    public T peek() { return head == null ? null : head.value; }

    @Override
    public Iterator<T> iterator() { throw new UnsupportedOperationException("Unimplemented method 'iterator'"); }

    @Override
    public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException("Unimplemented method 'toArray'"); }
}
