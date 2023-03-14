import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold4_3584_가장가까운공통조상 {
	static int N;
	static ArrayList<Integer>[] list;
	static ArrayList<Integer> aList, bList;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테케

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			list = new ArrayList[N + 1];

			for (int i = 1; i <= N; i++) {
				list[i] = new ArrayList<>();
			}

			StringTokenizer st = null;
			for (int i = 0; i < N - 1; i++) { // 간선 연결
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[b].add(a); // 자식 -> 부모
			}

			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 가장 가까운 공통 조상 구할 노드
			int B = Integer.parseInt(st.nextToken());

			visited = new boolean[N + 1]; // 해당 정점 방문했는지
			int ans = 0; // 공통 조상 노드

			Queue<Integer> queue = new LinkedList<>();
			queue.add(A);
			visited[A] = true;
			while (!queue.isEmpty()) { // A노드는 모든 조상을 찾아서 방문처리
				int now = queue.poll();

				for (int next : list[now]) {
					queue.add(next);
					visited[next] = true;
				}
			}

			queue.add(B);
			while (true) {
				int now = queue.poll();
				if (visited[now]) { // 방문했다 = 공통 조상 발견
					ans = now;
					break;
				} else { // 방문안한 조상 노드는 방문처리
					visited[now] = true;
				}
				for (int next : list[now]) {
					queue.add(next);
				}
			}
			System.out.println(ans);

		}
	}
}
