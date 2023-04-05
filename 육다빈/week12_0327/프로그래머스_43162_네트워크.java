import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visit = new boolean[n];

        for(int i=0; i<n; i++){
            if(visit[i]) continue;
            
            answer++;
            visit[i] = true;
            
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(i);
            
            while(!queue.isEmpty()){
                int now = queue.poll();
                for(int j=0; j<n; j++){
                    if(!visit[j] && computers[now][j]==1) {
                        visit[j] = true;
                        queue.add(j);
                    } 
                }
            }
        }
        return answer;
    }
}
