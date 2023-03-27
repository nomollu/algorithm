import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_Gold5_1461_도서관 {
	static int ans, N, M, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 책 개수
		M = Integer.parseInt(st.nextToken()); // 한번에 들 수 있는 책 개수

		PriorityQueue<Integer> plus = new PriorityQueue<>((a, b) -> b - a); // 양수
		PriorityQueue<Integer> minus = new PriorityQueue<>((a, b) -> b - a); // 음수

		st = new StringTokenizer(br.readLine());
		max = 0;
		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());
			if (now < 0) {
				minus.add(now * -1);
			} else {
				plus.add(now);
			}
			max = Math.max(max, now < 0 ? now * -1 : now);
		}

		ans = 0;
		calcu(plus);
		calcu(minus);
		System.out.println(ans -= max);
	}

	static void calcu(PriorityQueue<Integer> pq) {
		while (!pq.isEmpty()) {
			int now = pq.poll(); // 큰 값 먼저 뽑기
			for (int i = 0; i < M - 1; i++) { // 들 수 있는 만큼 책 더 뽑기
				pq.poll();
				if (pq.isEmpty())
					break;
			}
			ans += now * 2; // 왕복
		}
	}

}
