import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Silver1_1389_케빈베이컨의6단계법칙 {
	static int N, M;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}

		int ans = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int result = findKevinBacon(i);
			if (min > result) {
				min = result;
				ans = i;
			}
		}
		System.out.println(ans);
	}

	static int findKevinBacon(int num) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		queue.add(new int[] { num, 0 });
		visited[num] = true;
		int[] sum = new int[N + 1];
		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			for (Integer next : list[now[0]]) {
				if (!visited[next]) {
					queue.add(new int[] { next, now[1] + 1 });
					visited[next] = true;
					sum[next] = now[1] + 1;
				}
			}
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			if (num != i) {
				ans += sum[i];
			}
		}
		return ans;
	}
}
