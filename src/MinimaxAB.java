
import java.util.*;

public class MinimaxAB {

    public static class Result {
        public Move move; public int value;
        public Result(Move m,int v){ move=m; value=v; }
    }

    public static Result minimaxAB(Board b) {
        Integer u=b.utility();
        if (u!=null) return new Result(null,u*1000);

        if (b.playerToMove()==Board.X)
            return max(b, Integer.MIN_VALUE, Integer.MAX_VALUE);

        return min(b, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static Result max(Board b, int alpha, int beta){
        Integer u=b.utility();
        if (u!=null) return new Result(null, u*1000);

        int best=Integer.MIN_VALUE;
        Move bestMv=null;

        List<Move> ordered = MoveOrdering.order(b, b.actions(), Board.X);

        for (Move mv : ordered){
            int v = min(b.copyAndApply(mv,Board.X),alpha,beta).value;

            if (v>best || (v==best && (bestMv==null || mv.compareTo(bestMv)<0))){
                best=v; bestMv=mv;
            }
            alpha=Math.max(alpha,best);
            if (alpha>=beta) break;
        }
        return new Result(bestMv,best);
    }

    private static Result min(Board b, int alpha, int beta){
        Integer u=b.utility();
        if (u!=null) return new Result(null, u*1000);

        int best=Integer.MAX_VALUE;
        Move bestMv=null;

        List<Move> ordered = MoveOrdering.order(b, b.actions(), Board.O);

        for (Move mv : ordered){
            int v = max(b.copyAndApply(mv,Board.O),alpha,beta).value;

            if (v<best || (v==best && (bestMv==null || mv.compareTo(bestMv)<0))){
                best=v; bestMv=mv;
            }
            beta=Math.min(beta,best);
            if (alpha>=beta) break;
        }
        return new Result(bestMv,best);
    }
}
