import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold5_16234_인구이동 {
	static int N, L, R, map[][], visited[][];
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		// 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		while (true) {
			visited = new int[N][N];
			int cnt = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j] == 0) {
						bfs(i, j, cnt);
						cnt++;
					}
				}
			}
			// cnt가 전체 넓이+1이랑 같으면 국경선 공유하는 나라 더이상 없는 것
			if (cnt == N * N + 1) {
				break;
			} else {
				ans++;
			}
		}
		System.out.println(ans);

	}

	// 국경선 공유하는 나라 찾기
	static void bfs(int i, int j, int index) {

		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(i, j));
		visited[i][j] = index;

		int cnt = 1; // 연합 나라 수
		int sum = map[i][j]; // 연합 인구 수
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];

				if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= N || visited[nexti][nextj] != 0)
					continue;
				int diff = Math.abs(map[now.i][now.j] - map[nexti][nextj]); // 두 나라 인구 차이
				// 인구 차이가 L명 이상 R명 이하
				if (diff >= L && diff <= R) {
					queue.add(new Point(nexti, nextj));
					visited[nexti][nextj] = index;
					cnt++;
					sum += map[nexti][nextj];
				}
			}
		}

		// 연합 나라 인구수 갱신
		int peopleNum = sum / cnt;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (visited[x][y] == index) {
					map[x][y] = peopleNum;
				}
			}
		}
	}

	static class Point {
		int i, j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

}
