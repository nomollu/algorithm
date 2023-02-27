import java.util.*;

class Solution {
    static class Node implements Comparable<Node>{
        int multi;
        List<Integer> list;
        Node(int multi, List<Integer> list) {
            this.multi = multi;
            this.list = list;
        }
        @Override
        public int compareTo(Node o){
            return this.multi - o.multi;
        }
    }
    public int[] solution(int n, int s) {
        int[] answer;
        if(s<n) {
            answer = new int[] {-1};
            return answer;
        }
        
        Node[][] dp = new Node[n+1][s+1]; // n개의 원소로 만든 합이 s인 최고의 곱
        for(int i=1; i<=s; i++){
            dp[1][i] = new Node(i, Arrays.asList(i));
        }
        // int cnt = 0;
        for(int i=2; i<=n; i++){
            for(int j=i; j<=s; j++){
                dp[i][j] = new Node(-1, new ArrayList<Integer>());
                for(int k=1; k<j; k++){
                    int tmp_mul = dp[i-1][j-k].multi * k;
                    if(dp[i][j].multi < tmp_mul){
                        ArrayList<Integer> tmp_list = new ArrayList<Integer>(dp[i-1][j-k].list);
                        tmp_list.add(k);
                        dp[i][j] = new Node(tmp_mul, tmp_list);
                    }
                }
            }
        }
        
            
        int len = dp[n][s].list.size();
        answer = new int[len];
        int idx = 0;
        for(int i : dp[n][s].list) answer[idx++] = i;
        Arrays.sort(answer);
        return answer;
    }
}
