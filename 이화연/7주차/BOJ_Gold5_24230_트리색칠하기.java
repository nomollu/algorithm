import java.util.*;
import java.io.*;

public class BOJ_Gold5_24230_트리색칠하기 {
	static int N, colors[];
	static boolean visited[];
	static ArrayList[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		colors = new int[N + 1];
		visited = new boolean[N + 1];
		list = new ArrayList[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			colors[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && colors[i] != 0) { // 색칠해야하는 정점
				visited[i] = true;
				dfs(i, colors[i]);
				ans++;
			}
		}
		System.out.println(ans);
	}

	static void dfs(int num, int color) {
		for (int i = 0; i < list[num].size(); i++) {
			int next = (int) list[num].get(i);
			if (colors[next] == color && !visited[next]) { // 자식이랑 부모 색 같을 경우에만
				visited[next] = true;
				dfs(next, color);
			}
		}
	}
}
