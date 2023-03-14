import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_Gold3_11437_LCA {
	static int N, parent[], depth[], M;
	static ArrayList<Integer>[] list;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		parent = new int[N + 1]; // 각 노드의 부모
		depth = new int[N + 1]; // 각 노드의 depth

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		StringTokenizer st = null;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}

		visited[1] = true;
		dfs(1, 1, 1); // 루트노드인 1부터 시작

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(findCommonParent(a, b));
		}

	}

	static void dfs(int num, int p, int d) { // 각 노드의 깊이, 부모 찾기
		depth[num] = d;
		parent[num] = p;

		if (list[num].size() == 1 && num != 1) { // 사이즈가 1이고 루트노드가 아니면 리프노드임
			return;
		}

		for (int next : list[num]) {
			if (!visited[next]) {
				visited[next] = true;
				dfs(next, num, d + 1);
			}
		}
	}

	static int findCommonParent(int a, int b) {
		int depthA = depth[a];
		int depthB = depth[b];

		while (depthA != depthB) { // 깊이를 같게 만들기
			if (depthA > depthB) {
				a = parent[a]; // a의 깊이를 줄였으니 그 위 부모로 갱신
				depthA--;
			} else {
				b = parent[b];
				depthB--;
			}
		}

		while (a != b) { // 깊이는 같으니 공통 부모 찾기
			a = parent[a];
			b = parent[b];
		}

		return a;
	}
}
