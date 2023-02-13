import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Silver3_1003_피보나치함수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[][] dp = new int[41][2];
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			if (N < 2) {
				System.out.println(dp[N][0] + " " + dp[N][1]);
				continue;
			}

			for (int i = 2; i <= N; i++) {
				if (dp[i][0] == 0 && dp[i][1] == 0) {
					dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
					dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
				}
			}
			System.out.println(dp[N][0] + " " + dp[N][1]);
		}
	}
}
