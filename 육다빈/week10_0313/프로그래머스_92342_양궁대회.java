import java.util.*;

class Solution {
    class Arrow{
        int cnt;
        List<Integer> select;
        Arrow(int cnt, List<Integer> select){
            this.cnt = cnt;
            this.select = select;
        }
        Arrow copyArrow(){
            return new Arrow(this.cnt, this.select);
        }
    }
    
    public int[] solution(int n, int[] info) {
        int[] answer = new int[n];
        
        int score = 0;
        for(int i=0; i<=10; i++) score += info[i];
        
        List<Arrow>[][] dp = new List<Arrow>[11][score+1];
        for()
        // if(info[1]<n) dp[1][1] = info[1]+1;
        if(info[1]<n) dp[1][1] = new Arrow(info[1]+1, Arrays.asList(1);
        for(int i=2; i<=10; i++){
            for(int j=0; j<=score; j++){
                if(j<i) dp[i][j] = dp[i-1][j].copyArrow(); //i점 과녁에 쏘지 않음
                else if(j==i) {
                    if (dp[i-1][j].cnt==0) dp[i][j] = new Arrow(info[j], Arrays.asList(j));
                    else {  // dp[i][j] = Math.min(dp[i-1][j], info[j]);
                        if(dp[i-1][j] < info[j]) dp[i][j] = dp[i-1][j].copyArrow();
                        else dp[i][j] = new Arrow(info[j]+1, Arrays.asList(j));
                    }
                }else {
                    if (dp[i-1][j].cnt==0) dp[i][j] = new Arrow(dp[i][j-i].cnt+dp[i][i].cnt, ;
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
