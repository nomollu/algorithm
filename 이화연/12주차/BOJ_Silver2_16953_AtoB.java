import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Silver2_16953_AtoB {
	static long A, B, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		min = Long.MAX_VALUE;

		dfs(A, 0);
		System.out.println(min == Long.MAX_VALUE ? -1 : min);
	}

	static void dfs(long num, long cnt) {
		if (num > B || cnt + 1 > min) {
			return;
		}

		if (num == B) {
			min = Math.min(min, cnt + 1);
		}

		dfs(num * 2, cnt + 1);
		String s = String.valueOf(num) + "1";
		dfs(Long.parseLong(s.trim()), cnt + 1);
	}
}
