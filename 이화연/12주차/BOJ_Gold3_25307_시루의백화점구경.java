import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold3_25307_시루의백화점구경 {
	static int N, M, K, map[][];
	static Point siru;
	static Queue<Point> mannequin;
	static int di[] = { -1, 1, 0, 0 };
	static int dj[] = { 0, 0, -1, 1 };
	static boolean[][] mVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		K = Integer.parseInt(st.nextToken()); // 마네킹과 떨어져야 하는 거리
		map = new int[N][M];
		mannequin = new LinkedList<>(); // 마네킹 위치 담을 큐
		mVisited = new boolean[N][M]; // 마네킹 방문처리할 배열

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 3) { // 마네킹 위치
					mannequin.add(new Point(i, j, 0));
					mVisited[i][j] = true; // 마네킹이 위치한 곳 방문처리
				} else if (map[i][j] == 4) {
					siru = new Point(i, j, 0);
				}
			}
		}

		// 마네킹 위치에서 K이하인 곳을 찾아 못가게 만들기
		findMannequin();
		map[siru.i][siru.j] = 4; // 시루가 위치했던 곳은 다시 4로 바꾸기

		// 시루 위치에서 의자 찾기
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(siru);
		boolean[][] visited = new boolean[N][M];
		visited[siru.i][siru.j] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			if (map[now.i][now.j] == 2) {
				return now.cnt;
			}

			for (int d = 0; d < 4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];
				// 범위 벗어나거나 이미 방문한 곳, 기둥이나 마네킹인 곳은 안됨
				if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M || visited[nexti][nextj]
						|| map[nexti][nextj] == 1 || map[nexti][nextj] == 3)
					continue;
				queue.add(new Point(nexti, nextj, now.cnt + 1));
				visited[nexti][nextj] = true;
			}
		}

		return -1;
	}

	static void findMannequin() {
		while (!mannequin.isEmpty()) {
			Point now = mannequin.poll();

			for (int d = 0; d < 4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];

				// 범위 벗어나거나, 이미 방문한 곳이면 패스
				// 의자가 위치한 곳이어도 마네킹이 갈 수 있는 거리면 결국 못감
				if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M || mVisited[nexti][nextj])
					continue;

				// 현재 위치에서 +1한 값이 K 이하이면 마네킹이 위치할 수 있는곳
				if (now.cnt + 1 <= K) {
					mannequin.add(new Point(nexti, nextj, now.cnt + 1));
					map[nexti][nextj] = 3; // 마네킹 위치한 곳으로 바꾸기
					mVisited[nexti][nextj] = true;
				}
			}
		}
	}

	static class Point {
		int i, j, cnt;

		public Point(int i, int j, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}

}
