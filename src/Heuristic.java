

import java.util.*;

public class Heuristic {

    private static final int[] W = {0,1,10,50,200,1000};

    public static int eval(Board b) {
        int score=0;
        for (int i=0;i<b.m;i++) score += evalLine(row(b,i), b.k);
        for (int j=0;j<b.m;j++) score += evalLine(col(b,j), b.k);
        score += evalDiags(b);
        return score;
    }

    private static int evalLine(char[] line, int k) {
        int n=line.length, score=0;
        for (int s=0;s<n;s++)
            for (int len=1; len<=k && s+len<=n; len++) {
                int x=0,o=0;
                for (int t=s;t<s+len;t++) {
                    if (line[t]=='X') x++;
                    else if (line[t]=='O') o++;
                }
                if (x>0 && o>0) continue;
                if (len==k && x==k) return 100000;
                if (len==k && o==k) return -100000;

                if (x>0) score+=W[Math.min(x,W.length-1)];
                if (o>0) score-=W[Math.min(o,W.length-1)];
            }
        return score;
    }

    private static int evalDiags(Board b) {
        int s=0;
        for (int i=0;i<b.m;i++) s+=evalLine(diagSE(b,i,0), b.k);
        for (int j=1;j<b.m;j++) s+=evalLine(diagSE(b,0,j), b.k);
        for (int i=0;i<b.m;i++) s+=evalLine(diagNE(b,i,0), b.k);
        for (int j=1;j<b.m;j++) s+=evalLine(diagNE(b,b.m-1,j), b.k);
        return s;
    }

    private static char[] row(Board b, int r){ return b.a[r]; }
    private static char[] col(Board b, int c){
        char[] r=new char[b.m];
        for(int i=0;i<b.m;i++) r[i]=b.a[i][c];
        return r;
    }

    private static char[] diagSE(Board b,int r,int c){
        List<Character> L=new ArrayList<>();
        while(r<b.m && c<b.m){ L.add(b.a[r][c]); r++; c++; }
        char[] arr=new char[L.size()];
        for(int i=0;i<L.size();i++) arr[i]=L.get(i);
        return arr;
    }
    private static char[] diagNE(Board b,int r,int c){
        List<Character> L=new ArrayList<>();
        while(r>=0 && c<b.m){ L.add(b.a[r][c]); r--; c++; }
        char[] arr=new char[L.size()];
        for(int i=0;i<L.size();i++) arr[i]=L.get(i);
        return arr;
    }
}
