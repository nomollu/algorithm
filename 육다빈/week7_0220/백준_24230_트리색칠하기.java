package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G24230_paintingTree {
	static int N, cnt=0;
	static int[] answer;
	static List<Integer>[] nodes;
	static boolean[] visit;
	
	static void dfs(int node, int color) {
		visit[node] = true;
		int paint = color;
		if(answer[node] != color) {
			paint = answer[node];
			cnt++;
		}
		for(int n : nodes[node]) {
			if(!visit[n]) dfs(n, paint);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = new int[N+1];
		nodes = new List[N+1];
		visit = new boolean[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
			nodes[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].add(b);
			nodes[b].add(a);
		}
		
		dfs(1, 0);
		
		System.out.println(cnt);
		
	}

}
