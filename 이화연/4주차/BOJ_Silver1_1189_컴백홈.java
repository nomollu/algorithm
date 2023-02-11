import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1189_컴백홈 {
	static int R, C, K, ans = 0;
	static char[][] map;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static boolean[][] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		K = Integer.parseInt(st.nextToken()); // 거리

		map = new char[R][C];
		v = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		v[R - 1][0] = true;
		dfs(R - 1, 0, 1);
		System.out.println(ans);

	}

	static void dfs(int i, int j, int distance) {
		if (i == 0 && j == C - 1 && distance == K) {
			ans++;
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nexti = i + di[d];
			int nextj = j + dj[d];

			if (nexti < 0 || nexti >= R || nextj < 0 || nextj >= C || v[nexti][nextj] || map[nexti][nextj] == 'T')
				continue;

			v[nexti][nextj] = true;
			dfs(nexti, nextj, distance + 1);
			v[nexti][nextj] = false;
		}
	}
}
