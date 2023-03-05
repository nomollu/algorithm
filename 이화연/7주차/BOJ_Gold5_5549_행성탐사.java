import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold5_5549_행성탐사 {
	static int M, N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		// 입력
		Planet[][] map = new Planet[M + 1][N + 1];
		String s = "";
		for (int i = 0; i <= M; i++) {
			if (i != 0) { // 0이 아닐경우에만 입력받기
				s = br.readLine();
			}
			for (int j = 0; j <= N; j++) {
				if (i == 0 || j == 0) { // 인덱스 0인경우
					map[i][j] = new Planet(0, 0, 0);
					continue;
				}
				if (s.charAt(j - 1) == 'J') { // 정글
					map[i][j] = new Planet(1, 0, 0);
				} else if (s.charAt(j - 1) == 'O') { // 바다
					map[i][j] = new Planet(0, 1, 0);
				} else { // 얼음
					map[i][j] = new Planet(0, 0, 1);
				}
			}
		}

		Planet[][] prefixSum = new Planet[M + 1][N + 1];
		// 인덱스 0인 곳 초기화
		for (int j = 0; j <= N; j++) {
			prefixSum[0][j] = new Planet(0, 0, 0);
		}
		for (int i = 0; i <= M; i++) {
			prefixSum[i][0] = new Planet(0, 0, 0);
		}
		// 정글, 바다, 얼음 개수 누적합 구하기
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				Planet up = prefixSum[i - 1][j];
				Planet left = prefixSum[i][j - 1];
				Planet diagonal = prefixSum[i - 1][j - 1];
				Planet origin = map[i][j];
				// 바로위와 바로 옆의 값을 더하고 겹치는 부분인 대각선의 값을 빼준다음 원래값까지 더해줘야함
				int jj = up.j + left.j - diagonal.j + origin.j;
				int oo = up.o + left.o - diagonal.o + origin.o;
				int ii = up.i + left.i - diagonal.i + origin.i;
				prefixSum[i][j] = new Planet(jj, oo, ii);
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int starti = Integer.parseInt(st.nextToken());
			int startj = Integer.parseInt(st.nextToken());
			int endi = Integer.parseInt(st.nextToken());
			int endj = Integer.parseInt(st.nextToken());
			Planet origin = prefixSum[endi][endj];
			Planet up = prefixSum[starti - 1][endj];
			Planet left = prefixSum[endi][startj - 1];
			Planet diagonal = prefixSum[starti - 1][startj - 1];
			int jj = origin.j - up.j - left.j + diagonal.j;
			int oo = origin.o - up.o - left.o + diagonal.o;
			int ii = origin.i - up.i - left.i + diagonal.i;
			System.out.println(jj + " " + oo + " " + ii);
		}

	}

	static class Planet {
		int j, o, i;

		public Planet(int j, int o, int i) {
			super();
			this.j = j;
			this.o = o;
			this.i = i;
		}
	}
}
