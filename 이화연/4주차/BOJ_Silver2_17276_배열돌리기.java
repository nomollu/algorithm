import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Silver2_17276_배열돌리기 {
	static int n, d, mid, map[][], result[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 배열 크기
			d = Integer.parseInt(st.nextToken()); // 각도
			mid = (n + 1) / 2; // 중간

			map = new int[n + 1][n + 1];
			result = new int[n + 1][n + 1];
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int cnt = (d / 45) % 8;
			if (cnt == 0) {
				print();
				continue;
			}
			for (int c = 0; c < Math.abs(cnt); c++) {
				if (cnt < 0) {
					turnLeft();
				} else {
					turnRight();
				}
				copyArr();
			}
			print();
		}
	}

	static void turnRight() {
		int col = n;
		for (int i = 1; i <= n; i++) {
			result[i][mid] = map[i][i]; // 주 대각선 -> 가운데 열
			result[i][col] = map[i][mid]; // 가운데 열 -> 부 대각선
			result[mid][col] = map[i][col]; // 부 대각선 -> 가운데 행
			result[i][i] = map[mid][i]; // 가운데 행 -> 주 대각선
			col--;
		}
	}

	static void turnLeft() {
		int col = n;
		for (int i = 1; i <= n; i++) {
			result[mid][i] = map[i][i]; // 주 대각선 -> 가운데 행
			result[col][i] = map[mid][i]; // 가운데 행 -> 부 대각선
			result[col][mid] = map[col][i]; // 부 대각선 -> 가운데 열
			result[i][i] = map[i][mid]; // 가운데 열 -> 주 대각선
			col--;
		}
	}

	static void copyArr() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (result[i][j] != 0) {
					map[i][j] = result[i][j];
				}
			}
		}
	}

	static void print() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
