import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1647_도시분할계획 {
	static int N, M, parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Edge[] edge = new Edge[M];
		parents = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edge[i] = new Edge(from, to, weight);
		}
		Arrays.sort(edge);
		makeParent();

		int result = 0;
		int cnt = 0;

		for (Edge e : edge) {
			if (union(e.from, e.to)) {
				result += e.weight;
				if (++cnt == N - 2) // 한개의 집만 있어도 마을이 되기 때문에
					break;
			}
		}
		System.out.println(result);

	}

	static void makeParent() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	static int findParent(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = findParent(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findParent(a);
		int bRoot = findParent(b);
		if (aRoot == bRoot) {
			return false;
		}
		parents[bRoot] = aRoot;
		return true;
	}

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

}
