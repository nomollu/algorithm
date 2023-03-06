import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold4_4179_불 {
	static int R, C, ans;
	static char[][] map;
	static boolean[][] visited;
	static Queue<Miro> queue;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		ans = -1; // 탈출할 수 없는 경우
		visited = new boolean[R][C];

		Miro start = null;
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'J') { // 초기 위치
					start = new Miro(i, j, 1);
					visited[i][j] = true;
				}
			}
		}

		queue = new LinkedList<>();
		// 지훈 -> 불 순서
		queue.add(start);
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'F') {
					queue.add(new Miro(i, j, 0));
				}
			}
		}
		bfs();
		System.out.println(ans == -1 ? "IMPOSSIBLE" : ans);
	}

	static void bfs() {
		while (!queue.isEmpty()) {
			Miro now = queue.poll();
			char status = map[now.x][now.y];
			if (status == 'J') {
				if (escape(now.x, now.y)) { // 탈출할 수 있음
					ans = now.time;
					break;
				}

				// 지훈이일 경우에만 지훈이가 있던 자리 지나갈 수 있는 공간으로 갱신
				map[now.x][now.y] = '.';
			}
			for (int d = 0; d < 4; d++) {
				int nexti = now.x + di[d];
				int nextj = now.y + dj[d];
				if (check(nexti, nextj, status)) {
					// 불인경우 다음 좌표가 불이 아닌 경우만
					// 지훈이는 방문 안한 경우만 이동 가능
					if ((status == 'J' && !visited[nexti][nextj]) || (status == 'F' && map[nexti][nextj] != 'F')) {
						queue.add(new Miro(nexti, nextj, now.time + 1));
						map[nexti][nextj] = status;
						visited[nexti][nextj] = true;
					}
				}
			}
		}
	}

	// 지금 위치가 미로의 가장자리에 접한 공간인지
	static boolean escape(int i, int j) {
		if (i == 0 || j == 0 || i == R - 1 || j == C - 1) {
			return true;
		}
		return false;
	}

	// 유효한 인덱스인지
	static boolean check(int i, int j, char status) {
		boolean flag = true;
		if (i < 0 || i >= R || j < 0 || j >= C || map[i][j] == '#') {
			flag = false;
		}
		if (status == 'J' && map[i][j] == 'F') { // 지훈이는 불이 위치한 곳으로 갈 수 없음
			flag = false;
		}
		return flag;
	}

	static class Miro {
		int x, y, time;

		public Miro(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}
