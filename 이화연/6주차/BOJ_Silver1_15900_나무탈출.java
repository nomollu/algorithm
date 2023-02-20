import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_Silver1_15900_나무탈출 {
	static ArrayList[] tree;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		tree = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		StringTokenizer st = null;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}

		dfs(1, 0);
		System.out.println(ans % 2 == 0 ? "No" : "Yes");
	}

	static void dfs(int num, int depth) {
		visited[num] = true;
		if (tree[num].size() == 1 && num != 1) { // 사이즈가 1이고 현재 num이 루트노드가 아니면 리프노드임
			ans += depth; //지금까지 깊이 더해주기
			return;
		}

		for (int i = 0; i < tree[num].size(); i++) {
			int now = (int) tree[num].get(i);
			if (!visited[now]) {
				dfs(now, depth + 1);
			}
		}
	}
}
