import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17503_맥주축제 {
	static int N, M, K;
	static Beer[] beer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		beer = new Beer[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			beer[i] = new Beer(v, c);
		}
		Arrays.sort(beer);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int sum = 0;
		int ans = -1;
		for (int i = 0; i < K; i++) {
			pq.add(beer[i].v); // 선호도 더하기
			sum += beer[i].v;
			if (pq.size() > N) { // 사이즈 초과하면 가장 작은 선호도 뺌
				sum -= pq.poll();
			}

			if (pq.size() == N && sum >= M) {
				ans = beer[i].c;
				break;
			}

		}
		System.out.println(ans);
	}

	static class Beer implements Comparable<Beer> {
		int v, c;

		public Beer(int v, int c) {
			super();
			this.v = v;
			this.c = c;
		}

		@Override
		public int compareTo(Beer o) {
			if (this.c == o.c) {
				return o.v - this.v;
			}
			return this.c - o.c;
		}
	}

}
