import java.util.*;

class Solution {
    class Arrow{
        int cnt;
        List<Integer> select;
        Arrow(int cnt, List<Integer> select){
            this.cnt = cnt;
            this.select = select;
        }
    }
    
    public int[] solution(int n, int[] info) {
        int[] answer = new int[n];
        
        int score = 0;
        for(int i=0; i<=10; i++) score += info[i];
        
        int[][] dp = new int[11][score+1];
        if(info[1]<n) dp[1][1] = info[1]+1;
        // if(info[1]<n) dp[1][1] = new Arrow(info[1]+1, ;
        for(int i=2; i<=10; i++){
            for(int j=0; j<=score; j++){
                if(j<i) dp[i][j] = dp[i-1][j]; //i점 과녁에 쏘지 않음
                else if(j==i) {
                    if (dp[i-1][j]==0) dp[i][j] = info[j];
                    else dp[i][j] = Math.min(dp[i-1][j], info[j]);
                }else {
                    if (dp[i-1][j]==0) dp[i][j] = dp[i][j-i]+dp[i][i];
                    else dp[i][j] = Math.min(dp[i-1][j], dp[i][j-i]+dp[i][i]);
                }
                if(dp[i][j]<n) dp[i][j] = 0;
            }
        }
        for(int i=1; i<11; i++) if(dp[i][score]!=0) {
             answer[0] = dp[i][score];
            return answer;
        }
        return answer;
    }
}
