import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Silver1_1446_지름길 {
	static int N, D, dist[];
	static ArrayList<Road>[] roads;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		dist = new int[D + 1]; // 거리 최솟값 저장할 배열
		roads = new ArrayList[10001]; // 지름길 저장할 배열

		for (int i = 1; i < 10001; i++) {
			roads[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if (e - s > d) { // 지름길이 더 유리할때만
				roads[e].add(new Road(s, d));
			}
		}

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		for (int i = 1; i <= D; i++) {
			if (roads[i].size() > 0) { // 도착 지점에 지름길이 존재함
				for (Road r : roads[i]) {
					if (dist[r.start] + r.d > dist[i]) // 이미 작으면 갱신하지마
						continue;
					// 바로이전 도로에서 +1 한 값과 지름길로 지나온 값중 최소로 갱신
					dist[i] = Math.min(dist[i - 1] + 1, dist[r.start] + r.d);
				}
			} else { // 지름길 없으면 바로 이전 도로 +1
				dist[i] = dist[i - 1] + 1;
			}
		}
		System.out.println(dist[D]);

	}

	static class Road {
		int start, d;

		public Road(int start, int d) {
			super();
			this.start = start;
			this.d = d;
		}
	}
}
