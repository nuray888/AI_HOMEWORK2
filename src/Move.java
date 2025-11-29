

public class Move implements Comparable<Move> {
    public final int r, c;

    public Move(int r, int c) { this.r = r; this.c = c; }

    @Override
    public int compareTo(Move other) {
        if (r != other.r) return Integer.compare(r, other.r);
        return Integer.compare(c, other.c);
    }

    @Override
    public String toString() {
        return "(" + r + "," + c + ")";
    }
}
