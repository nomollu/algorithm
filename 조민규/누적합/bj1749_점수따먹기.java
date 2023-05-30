package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1749_점수따먹기 {
    static int N,M;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];
        dp = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < N ; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + matrix[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + matrix[i][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i][j];
                }
            }
        }

        long max = Long.MIN_VALUE;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                for(int k = i ; k < N ; k++){
                    for(int l = j ; l < M ; l++){
                        if (i == 0 && j == 0) {
                            max = Math.max(max, dp[k][l]);
                        } else if (i == 0) {
                            max = Math.max(max, dp[k][l] - dp[k][j-1]);
                        } else if (j == 0) {
                            max = Math.max(max, dp[k][l] - dp[i-1][l]);
                        } else {
                            max = Math.max(max, dp[k][l] - dp[k][j-1] - dp[i-1][l] + dp[i-1][j-1]);
                        }
                    }
                }
            }
        }

        System.out.println(max);
    }
}
