import java.io.*;
import java.util.*;

public class BOJ_18126_너구리구구 {
	static long answer = 0;
	static int N;
	static ArrayList<Edge>[] list;
	static boolean[] visited;

	static class Edge {
		int to, c;

		public Edge(int to, int c) {
			this.to = to;
			this.c = c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Edge(b, c));
			list[b].add(new Edge(a, c));
		}

		visited[1] = true;
		dfs(1, 0);
		System.out.println(answer);
	}

	static void dfs(int n, long sum) {
		answer = Math.max(sum, answer);

		for (Edge next : list[n]) {
			if (!visited[next.to]) {
				visited[next.to] = true;
				dfs(next.to, sum + next.c);
			}
		}
	}
}
