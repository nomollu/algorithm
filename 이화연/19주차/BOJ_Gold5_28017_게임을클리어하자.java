import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold5_28017_게임을클리어하자 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 게임 회차
        int M = Integer.parseInt(st.nextToken()); // 무기 종류
        int[][] times = new int[N][M]; //입력값 저장할 배열

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                times[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N + 1][M]; //각 회차에서 무기 선택했을 때 최소 클리어 시간
        dp[1] = times[0]; // 초기 값 설정

        for (int i = 2; i <= N; i++) { // 두번째 회차부터 구할 것
            for (int j = 0; j < M; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < M; k++) {
                    if (k != j) { //이전 회차의 무기는 제외하고
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + times[i - 1][j]);
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < M; j++) {
            ans = Math.min(ans, dp[N][j]); //N번째 회차에서 j번째 무기 선택했을 때 최소시간
        }
        System.out.println(ans);
    }
}
