package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj9095_123더하기 {
    public static void main(String[] args) throws IOException {
        // DP 초깃값 입력
        int[] dp = new int[11];
        dp[1] = 1; dp[2] = 2; dp[3] = 4;

        // DP[i]는 이전 3개 경우의 합이다.
        for(int i = 4 ; i <= 10 ; i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0 ; t < T ; t++){
            System.out.println(dp[Integer.parseInt(br.readLine())]);
        }
    }
}
