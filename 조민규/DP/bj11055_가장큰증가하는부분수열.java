package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj11055_가장큰증가하는부분수열 {
    static int N;
    static int[] A;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 1차원 배열의 DP
        dp[0] = A[0]; // 0~0의 최대 길이 설정
        for(int i = 1 ; i < N ; i++){
            // 1. 일단 i의 dp 초기값은 i의 A값으로 해놓음
            dp[i] = A[i];
            // 2. 0 ~ i-1 까지 A의 값을 탐색함
            for(int j = 0 ; j < i ; j++){
                // 3. 현재 증가하는 수열일 경우
                if(A[i] > A[j]){
                    // 4. (현재 idx의 값)과 (이전 idx의 dp 값 + 현재 idx의 A값) 중 큰 값을 dp에 저장
                    dp[i] = Math.max(dp[j] + A[i], dp[i]);
                }
            }
        }

        // DP 중 가장 큰 값을 출력
        Arrays.sort(dp);
        System.out.println(dp[N-1]);
    }
}
