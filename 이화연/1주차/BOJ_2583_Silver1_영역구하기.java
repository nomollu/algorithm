import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2583_영역구하기 {
	static int M, N, K;
	static boolean[][] visited;
	static int di[] = { -1, 1, 0, 0 };
	static int dj[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M];

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			for (int i = x; i < x1; i++) {
				for (int j = y; j < y1; j++) {
					visited[i][j] = true; // 직사각형 그린 부분 방문 처리
				}
			}
		}

		int area = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) { // 직사각형 없는 곳만
					list.add(bfs(i, j));
					area++;
				}
			}
		}
		System.out.println(area);
		Collections.sort(list);
		for (Integer i : list) {
			System.out.print(i + " ");
		}
	}

	static int bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(i, j));
		visited[i][j] = true;

		int count = 1; // 영역 넓이
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];

				if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M || visited[nexti][nextj])
					continue;

				visited[nexti][nextj] = true;
				queue.add(new Point(nexti, nextj));
				count++;
			}
		}
		return count;
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
