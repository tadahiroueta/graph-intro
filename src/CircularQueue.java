import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class CircularQueue<T> implements Queue<T> {
    
    class Node {
        T value;
        Node previous, next;

        Node(T value) { this.value = value; }
        
        Node(T value, Node previous, Node next) {
            this.value = value;
            this.previous = previous;
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
            if (head.value.equals(o)) return true;
            head = head.next;
        } while (start != head);
        return false;
    }

    @Override
    public Object[] toArray() {
        Node start = head;
        List<T> list = new ArrayList<>();
        do {
            list.add(head.value);
            head = head.next;
        } while (start != head);
        return list.toArray();
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) return false;
        if (size == 1 && head.value.equals(o)) {
            clear();
            return true;
        }
        T start = head.value;
        do {
            if (head.value.equals(o)) {
                head.previous.next = head.next;
                head.next.previous = head.previous;
                size--;
                return true;
            }
            head = head.next;
        } while (!start.equals(head.value));

        size--;
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
        for (T t : c) changed = add(t) || changed;
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
    public void clear() { 
        head = null;
        size = 0;
    }

    @Override
    public boolean add(T e) {
        if (isEmpty()) {
            head = new Node(e);
            head.next = head.previous = head;
            size++;
            return true;
        } 
        head.previous.next = head.previous = new Node(e, head.previous, head);
        size++;
        return true;
    }

    @Override
    public boolean offer(T e) { return add(e); }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        
        T value = head.value;
        head.previous.next = head.next;
        head.next.previous = head.previous;
        head = head.next;
        size--;
        return value;
    }

    @Override
    public T poll() { return remove(); }

    @Override
    public T element() { return isEmpty() ? null : head.value; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public T peek() { return element(); }

    @Override
    public String toString() {
        Node start = head;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(start.value);
            sb.append(" ");
            start = start.next;
        } while (start != head);

        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() { throw new UnsupportedOperationException("Unimplemented method 'iterator'"); }

    @Override
    @SuppressWarnings("hiding")
    public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException("Unimplemented method 'toArray'"); }
}
