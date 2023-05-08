package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G20168_streetFighterHS {
	static int N, end, money, result=Integer.MAX_VALUE;
	static int[][] adj;
	static boolean[] visit;
	
	static void dfs(int now, int sum, int max) {
		if(now==end) {
			result = Math.min(result, max);
			return;
		}
		for(int i=1; i<=N; i++) {
			if(adj[now][i]>0 && !visit[i] && sum+adj[now][i]<=money) {
				visit[i] = true;
				dfs(i, sum+adj[now][i], Math.max(max, adj[now][i]));
				visit[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		money = Integer.parseInt(st.nextToken());
		
		adj = new int[N+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a][b] = adj[b][a] = Integer.parseInt(st.nextToken());
		}
		
		visit = new boolean[N+1];
		visit[start] = true;
		dfs(start, 0, 0);
		
		System.out.println(result==Integer.MAX_VALUE ? -1 : result);
	}

}
