

import java.util.*;

public class MoveOrdering {

    public static List<Move> order(Board b, List<Move> moves, char player) {

        int center = b.m / 2;

        moves.sort(Comparator
            .comparingInt((Move mv) -> Math.abs(mv.r - center) + Math.abs(mv.c - center))
            .thenComparing(mv -> mv));

        List<Scored> S = new ArrayList<>();
        for (Move mv : moves) {
            Board nb = b.copyAndApply(mv, player);
            S.add(new Scored(mv, Heuristic.eval(nb)));
        }

        if (player=='X') S.sort((a,b2)->Integer.compare(b2.score, a.score));
        else S.sort((a,b2)->Integer.compare(a.score, b2.score));

        List<Move> out=new ArrayList<>();
        for (var s : S) out.add(s.mv);
        return out;
    }

    private static class Scored {
        Move mv; int score;
        Scored(Move m,int s){ mv=m; score=s; }
    }
}
