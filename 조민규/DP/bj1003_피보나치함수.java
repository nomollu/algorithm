package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1003_피보나치함수 {
    static int N;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            N = Integer.parseInt(br.readLine());
            dp = new int[41][2];

            dp[0][0] = 1;
            dp[0][1] = 0;
            dp[1][0] = 0;
            dp[1][1] = 1;

            fibonacci(N);
            System.out.println(dp[N][0] + " " + dp[N][1]);
        }
    }

    public static int[] fibonacci(int n){

        if(dp[n][0] == 0 && n >= 2){
            int[] f1 = fibonacci(n-1);
            int[] f2 = fibonacci(n-2);
            dp[n][0] = f1[0] + f2[0];
            dp[n][1] = f1[1] + f2[1];
        }

        return dp[n];
    }
}
