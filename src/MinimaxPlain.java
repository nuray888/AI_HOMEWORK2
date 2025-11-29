public class MinimaxPlain {

    public static class Result {
        public Move move; public int value;
        public Result(Move m,int v){ move=m; value=v; }
    }

    public static MinimaxPlain.Result minimax(Board b) {
        Integer u=b.utility();
        if (u!=null) return new MinimaxPlain.Result(null, u*1000);

        if (b.playerToMove()==Board.X) return max(b);
        return min(b);
    }

    private static MinimaxPlain.Result max(Board b){
        Integer u=b.utility();
        if (u!=null) return new MinimaxPlain.Result(null, u*1000);

        int best=Integer.MIN_VALUE;
        Move bestMv=null;

        for (Move mv : b.actions()) {
            int val = minimax(b.copyAndApply(mv,Board.X)).value;
            if (val>best || (val==best && (bestMv==null || mv.compareTo(bestMv)<0))){
                best=val; bestMv=mv;
            }
        }
        return new MinimaxPlain.Result(bestMv,best);
    }

    private static MinimaxPlain.Result min(Board b){
        Integer u=b.utility();
        if (u!=null) return new MinimaxPlain.Result(null, u*1000);

        int best=Integer.MAX_VALUE;
        Move bestMv=null;

        for (Move mv : b.actions()) {
            int val = minimax(b.copyAndApply(mv,Board.O)).value;
            if (val<best || (val==best && (bestMv==null || mv.compareTo(bestMv)<0))){
                best=val; bestMv=mv;
            }
        }
        return new MinimaxPlain.Result(bestMv,best);
    }
}
