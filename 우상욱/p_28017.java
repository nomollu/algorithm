package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G5 게임을 클리어하자
 */
public class p_28017 {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dp = new int[N][M];
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < M; j++)
            dp[0][j] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                int tmp = Integer.MAX_VALUE;
                for (int k = 0; k < M; k++) {
                    if (k == j)
                        continue;
                    tmp = Math.min(tmp, dp[i - 1][k]);
                }
                dp[i][j] = tmp + Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++)
            ans = Math.min(ans, dp[N - 1][i]);
        System.out.println(ans);
    }
}
