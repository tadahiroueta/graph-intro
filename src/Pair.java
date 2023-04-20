import java.util.Objects;

class Pair<T, D> {
    protected T first;
    protected D second;

    public Pair() {}

    public Pair(T first, D second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() { return "(" + first + ", " + second + ")"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}