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
}