import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_Gold5_14699_관악산등산 {
	static int N, M, height[], ans[];
	static ArrayList<Integer>[] list;
	static PriorityQueue<Shelter> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		height = new int[N + 1];
		ans = new int[N + 1];
		list = new ArrayList[N + 1];
		queue = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
			queue.add(new Shelter(height[i], i));
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (height[a] < height[b]) { // 작은 곳에서 높은 곳으로만 갈 수 있도록 단방향 연결
				list[a].add(b);
			} else {
				list[b].add(a);
			}
		}

		// 높은 곳에서 내려오면서 갈 수 있는 곳 탐색
		while (!queue.isEmpty()) {
			Shelter now = queue.poll();
			int cnt = 0;

			// 연결된 곳 탐색하면서 최댓값 갱신
			for (int next : list[now.index]) {
				cnt = Math.max(cnt, ans[next]);
			}
			ans[now.index] = cnt + 1;
		}

		for (int i = 1; i <= N; i++) {
			System.out.println(ans[i]);
		}
	}

	static class Shelter implements Comparable<Shelter> {
		int height, index;

		public Shelter(int height, int index) {
			super();
			this.height = height;
			this.index = index;
		}

		@Override
		public int compareTo(Shelter o) {
			return o.height - this.height;
		}
	}

}
