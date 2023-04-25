package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj17626_FourSquares {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // DP
        int[] dp = new int[N+1];

        // min 비교 시 0 방지를 위한 초기화
        Arrays.fill(dp, 5);
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2 ; i <= N ; i++){
            for(int j = 1 ; j*j <= i ; j++){
                dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
        }

        // 출력
        System.out.println(dp[N]);
    }
}
