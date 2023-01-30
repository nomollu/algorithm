import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17610_양팔저울 {
	static int k, g[], sum;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine()); // 추 개수
		g = new int[k];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			g[i] = Integer.parseInt(st.nextToken());
			sum += g[i];
		}

		visited = new boolean[sum + 1];
		dfs(0, 0);
		int cnt = 0;
		for (int i = 1; i <= sum; i++) {
			if (!visited[i])
				cnt++;
		}
		System.out.println(cnt);
	}

	static void dfs(int cnt, int sum) {
		if (cnt == k) {
			if (sum > 0)
				visited[sum] = true;
			return;
		}

		dfs(cnt + 1, sum);
		dfs(cnt + 1, sum + g[cnt]);
		dfs(cnt + 1, sum - g[cnt]);
	}

}
