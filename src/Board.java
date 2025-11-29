

import java.util.*;

public class Board {

    public static final char X = 'X';
    public static final char O = 'O';
    public static final char EMPTY = '.';

    public final int m, k;
    public final char[][] a;
    public final int movesMade;

    public Board(int m, int k) {
        this.m = m; this.k = k;
        this.a = new char[m][m];
        for (int i=0;i<m;i++) Arrays.fill(a[i], EMPTY);
        this.movesMade = 0;
    }

    private Board(Board other, char[][] arr, int movesMade) {
        this.m = other.m; 
        this.k = other.k; 
        this.movesMade = movesMade;
        this.a = arr;
    }

    public Board copyAndApply(Move mv, char player) {
        char[][] b = new char[m][m];
        for (int i=0;i<m;i++) System.arraycopy(a[i], 0, b[i], 0, m);
        b[mv.r][mv.c] = player;
        return new Board(this, b, this.movesMade+1);
    }

    public List<Move> actions() {
        List<Move> L = new ArrayList<>();
        for (int i=0;i<m;i++)
            for (int j=0;j<m;j++)
                if (a[i][j]==EMPTY) L.add(new Move(i,j));
        Collections.sort(L);
        return L;
    }

    public char playerToMove() {
        return (movesMade % 2 == 0) ? X : O;
    }

    public Character winner() {
        int[][] dirs = {{0,1},{1,0},{1,1},{1,-1}};
        for (int i=0;i<m;i++)
            for (int j=0;j<m;j++) {
                char s = a[i][j];
                if (s==EMPTY) continue;
                for (var d: dirs) {
                    int cnt=1, r=i+d[0], c=j+d[1];
                    while (in(r,c) && a[r][c]==s) {
                        cnt++; 
                        if (cnt==k) return s;
                        r+=d[0]; c+=d[1];
                    }
                }
            }
        return null;
    }

    private boolean in(int r,int c){return r>=0 && r<m && c>=0 && c<m;}

    public Integer utility() {
        Character w = winner();
        if (w!=null) return w==X ? +1 : -1;
        if (movesMade==m*m) return 0;
        return null;
    }

    public boolean isTerminal() {
        return utility()!=null;
    }

    public void print() {
        for (int i=0;i<m;i++) {
            for (int j=0;j<m;j++) System.out.print(a[i][j]);
            System.out.println();
        }
    }
}
