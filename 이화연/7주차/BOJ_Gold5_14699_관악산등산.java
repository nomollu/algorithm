import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold5_14699_관악산등산 {
	static int N, M, height[], ans[];
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		height = new int[N + 1];
		ans = new int[N + 1];
		list = new ArrayList[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
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

		for (int i = 1; i <= N; i++) {
			if (list[i].size() == 0) { // 만약 연결된게 없으면 현재 쉼터보다 높은 쉼터가 없음
				ans[i] = 1;
			} else {
				bfs(i);
			}
		}

		for (int i = 1; i <= N; i++) {
			System.out.println(ans[i]);
		}
	}

	static void bfs(int num) {
		Queue<Shelter> queue = new LinkedList<>();
		queue.add(new Shelter(1, num));

		int cnt = 0;
		while (!queue.isEmpty()) {
			Shelter now = queue.poll();
//			System.out.println(now);

			for (int next : list[now.index]) {
				if (ans[next] != 0) { // 이전에 탐색했다면 +1한 값이 최대
					ans[num] = ans[next] + 1;
				} else {
					queue.add(new Shelter(now.cnt + 1, next));
					cnt = now.cnt + 1;
				}
//				System.out.println("next : " + next);

			}
		}
		ans[num] = ans[num] < cnt ? cnt : ans[num];
	}

	static class Shelter {
		int cnt, index;

		public Shelter(int cnt, int index) {
			super();
			this.cnt = cnt;
			this.index = index;
		}
	}

}
