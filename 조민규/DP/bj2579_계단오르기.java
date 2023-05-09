package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj2579_계단오르기 {
    static int N, ans;
    static int[] weight;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = new int[N];
        dp = new int[N][2];

        for(int i = 0 ; i < N ; i++){
            weight[i] = Integer.parseInt(br.readLine());
        }

        if(N == 1){
            ans = weight[0];
        } else {
            dp[0][0] = weight[0];
            dp[1][0] = weight[1];
            dp[1][1] = weight[0] + weight[1];
            ans = Math.max(dp[1][0], dp[1][1]);

            solve(2);
        }

        System.out.println(ans);
    }

    public static void solve(int now){

        if(now == N) return;

        dp[now][0] = weight[now] + Math.max(dp[now-2][0], dp[now-2][1]);
        dp[now][1] = weight[now] + dp[now-1][0];

        ans = Math.max(dp[now][0], dp[now][1]);

        solve(now+1);
    }
}
