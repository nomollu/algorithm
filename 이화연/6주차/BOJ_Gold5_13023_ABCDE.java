import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_Gold5_13023_ABCDE {
	static int N, M, max = 0;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}

		// 모든 정점을 돌면서 A-B-C-D-E인 경우를 찾는다
		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			dfs(i, 0, visited);
			if (max >= 4) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
	}

	static void dfs(int num, int depth, boolean[] v) {
		if (depth > 3) { // A-B-C-D-E인 경우
			max = Math.max(max, depth);
			return;
		}

		for (Integer i : list[num]) {
			if (!v[i]) {
				v[num] = true;
				dfs(i, depth + 1, v);
				v[num] = false; // 만약 현재 리스트에 정점이 한개 이상이라면 다른 경로도 탐색해야하기 때문에
			}
		}
	}

}
