import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_Gold4_14267_회사문화1 {
	static int n, m, count[];
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 직원 수
		m = Integer.parseInt(st.nextToken()); // 칭찬 횟수

		count = new int[n + 1];
		list = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<Integer>();
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (num != -1) { // 사장 제외하고
				list[num].add(i);
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 칭찬 받을 직원 번호
			int w = Integer.parseInt(st.nextToken()); // 칭찬 수치
			count[num] += w;
		}

		// 1번은 상사가 없으니 2번부터
		for (int i = 2; i <= n; i++) {
			for (int next : list[i]) { // 연결된 곳 찾아서
				count[next] += count[i]; // 내 칭찬을 더해준다
			}
		}

		for (int i = 1; i <= n; i++) {
			System.out.print(count[i] + " ");
		}
	}
}
