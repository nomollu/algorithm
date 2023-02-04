import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1916_최소비용구하기 {
	static int N, M, dist[];
	static ArrayList[] list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 도시 개수
		M = Integer.parseInt(br.readLine()); // 버스 개수
		StringTokenizer st = null;

		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Edge>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[from].add(new Edge(to, weight));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		System.out.println(dijkstra(start, end));
	}

	static int dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		pq.add(new Edge(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (!visited[now.to]) {
				visited[now.to] = true;

				for (int i = 0; i < list[now.to].size(); i++) {
					Edge next = (Edge) list[now.to].get(i);
					if (!visited[next.to] && dist[next.to] > dist[now.to] + next.weight) {
						dist[next.to] = dist[now.to] + next.weight;
						pq.add(new Edge(next.to, dist[next.to]));
					}
				}
			}
		}
		return dist[end];
	}

	static class Edge implements Comparable<Edge> {
		int to, weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
