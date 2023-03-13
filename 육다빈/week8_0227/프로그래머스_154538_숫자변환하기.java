import java.util.*;

class Solution {
    int[] dp;
    Queue<Integer> queue = new LinkedList<Integer>();
    
    void calc(int res, int now, int y){
        if(res<=y && (dp[res]==0 || dp[now]+1<dp[res])){
            dp[res] = dp[now]+1;
            queue.add(res);
        } 
    }
    
    public int solution(int x, int y, int n) {
        if(x==y) return 0;
        dp = new int[y+1];
        queue.add(x);
        while(!queue.isEmpty()){
            int now = queue.poll();
            calc(now+n , now, y);
            calc(now*2 , now, y);
            calc(now*3 , now, y);
        }
        
        return dp[y]==0 ? -1 : dp[y];
    }
}
