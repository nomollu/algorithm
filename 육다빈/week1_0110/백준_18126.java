package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S18126_raccoonGugu {
	static int N;
	static List<Node>[] nodes;
	
	static class Node{
		int to, dis;
		Node(int to, int dis){
			this.to = to;
			this.dis = dis;
		}
	}
	
	static long dfs(int from, int to, long sum) {
		long max = sum;
		for(Node now : nodes[to]) {
			if(now.to == from) continue;
			max = Math.max(max, dfs(to, now.to, sum+now.dis));
		}
		return max;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nodes = new List[N+1];
		for(int i=1; i<=N; i++) nodes[i] = new ArrayList<Node>();
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			nodes[a].add(new Node(b, d));
			nodes[b].add(new Node(a, d));
		}
		
		System.out.println(dfs(-1, 1, 0));
	}

}
