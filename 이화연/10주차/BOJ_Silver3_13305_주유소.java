import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Silver3_13305_주유소 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 도시 개수
		long[] road = new long[N - 1];
		long[] oil = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < road.length; i++) {
			road[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < oil.length; i++) {
			oil[i] = Integer.parseInt(st.nextToken());
		}

		long ans = 0;
		long minOil = oil[0];
		for (int i = 0; i < oil.length - 1; i++) {
			minOil = Math.min(minOil, oil[i]); // 현재 주유소와 이전 주유소 비교
			ans += minOil * road[i];
		}
		System.out.println(ans);
	}
}
