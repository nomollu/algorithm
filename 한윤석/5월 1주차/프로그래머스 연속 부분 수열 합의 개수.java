class Solution {
    public int solution(int[] elements) {
        int len = elements.length;
        Map<Integer, Integer> dic = new HashMap<>();
        int dp [][] = new int [len+1][len];
        
        // 길이 1인 부분 합 기입
        for(int i=0; i<len; i++) {
        	dp[1][i] = elements[i];
        	dic.put(elements[i], 1);
        }
        
        // 누적합으로 길이 2~ 의 연속 부분 수열 합 구함
        for(int i=2; i<=len; i++) {
        	for(int j=0; j<len; j++) {
        		int idx = (j+i-1) % len;
        		dp[i][j] = dp[i-1][j] + dp[1][idx];
        		dic.put(dp[i][j], 1); // map에 계속해서 나온 수들 저장해둠
        	}
        }
        
        // map의 사이즈 = 저장된 수의 개수
        return dic.size();
    }
}
