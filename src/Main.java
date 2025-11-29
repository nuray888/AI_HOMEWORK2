
public class Main {
    public static void main(String[] args) {

        Board b = new Board(3,3);
        var res = MinimaxAB.minimaxAB(b);

        System.out.println("Best move = " + res.move);
        System.out.println("Value     = " + res.value);

        Board b4 = new Board(4,3);
        b4 = b4.copyAndApply(new Move(1,1), 'X');
        b4 = b4.copyAndApply(new Move(0,0), 'O');

        var r2 = DepthLimited.search(b4, 4);
        System.out.println("\nDepth-limited result = " + r2.move);
    }
}
