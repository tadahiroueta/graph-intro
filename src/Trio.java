import java.util.Objects;

class Trio<A, B, C> {
    protected A first;
    protected B second;
    protected C third;

    public Trio() {}
    
    public Trio(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public String toString() { return "(" + first + ", " + second + ", " + third + ")"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trio)) return false;
        Trio<?, ?, ?> trio = (Trio<?, ?, ?>) o;
        return Objects.equals(first, trio.first) && 
            Objects.equals(second, trio.second) && 
            Objects.equals(third, trio.third);
    }

    @Override
    public int hashCode() { return Objects.hash(first, second, third); }
}
