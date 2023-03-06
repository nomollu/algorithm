import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Silver4_11047_동전0 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 동전 종류
		int K = Integer.parseInt(st.nextToken()); // 만드려는 가치의 합

		int[] coins = new int[N];
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}

		int ans = 0; // 동전 개수
		// 동전 금액 큰 것부터 계산
		for (int i = N - 1; i >= 0; i--) {
			if (K >= coins[i]) {
				ans += K / coins[i];
				K %= coins[i];
			}
		}
		System.out.println(ans);
	}
}
