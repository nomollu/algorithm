import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Silver1_11057_오르막수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 수의 길이
		int[][] dp = new int[N + 1][10];
		int[] result = new int[N + 1]; // N길이의 오르막 수 개수 총합을 담을 배열

		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1; // 수의 길이가 1일 경우에는 모두 경우의 수가 1임
			result[1] += dp[1][i];
		}

		// n : 수의 길이, j : 끝의 자리 수
		for (int n = 2; n <= N; n++) {
			dp[n][0] = 1;
			result[n] += dp[n][0];
			for (int j = 1; j < 10; j++) {
				dp[n][j] = (dp[n][j - 1] + dp[n - 1][j]) % 10007;
				result[n] += dp[n][j]; // 계산할때마다 n길이의 오르막 수 개수 더해주기
			}
		}

		System.out.println(result[N] % 10007);
	}

}
