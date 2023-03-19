import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold4_1707_이분그래프 {
	static int V, E, colors[];
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			colors = new int[V + 1];

			list = new ArrayList[V + 1];
			for (int i = 1; i < list.length; i++) {
				list[i] = new ArrayList<Integer>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				list[b].add(a);
			}

			for (int i = 1; i < list.length; i++) { // 완전연결 그래프가 아닐 수 있기 때문에 모든 정점 수행
				if (colors[i] == 0) {
					paintColor(i);
				}
			}
			if (checkColor()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	static boolean checkColor() {
		for (int i = 1; i < list.length; i++) {
			for (Integer next : list[i]) {
				if (colors[i] == colors[next]) { // 나랑 연결된 정점이 색깔이 같으면 이분 그래프 아님
					return false;
				}
			}
		}
		return true;
	}

	static void paintColor(int num) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(num);
		colors[num] = 1;

		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (Integer next : list[now]) {
				if (colors[next] == 0) {
					colors[next] = colors[now] * -1; // 나랑 연결된 정점 나랑 반대로 칠하기
					queue.add(next);
				}
			}
		}
	}
}
