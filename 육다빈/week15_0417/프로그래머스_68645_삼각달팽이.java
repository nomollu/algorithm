import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[] di = {1, 0, -1};
        int[] dj = {0, 1, -1};
        
        int[][] tri = new int[n][n];
        int max = ((1+n)*n)/2;
        int d=0, ni=-1, nj=0;
        for(int i=1; i<=max; i++){
            ni += di[d];
            nj += dj[d];
            if(ni<0 || ni>=n || nj<0 || nj>=n || tri[ni][nj]!=0){
                ni -= di[d];
                nj -= dj[d];
                d = (d+1) % 3;
                ni += di[d];
                nj += dj[d];
            }
            tri[ni][nj] = i;
        }
        
        int[] answer = new int[max];
        int idx = 0;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<=i; j++) answer[idx++] = tri[i][j];
        }
        
        return answer;
    }
}
