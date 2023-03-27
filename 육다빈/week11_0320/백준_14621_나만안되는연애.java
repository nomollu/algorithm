package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G14621_noMoreLove {
	
	static int[] parent;

	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static int findParent(int a) {
		if(parent[a]!=a) parent[a] = findParent(parent[a]);
		return parent[a];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[] gender = new char[N+1];
		parent = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			gender[i] = (char) st.nextToken().charAt(0);
			parent[i] = i;
		}
		
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()); 
			int to = Integer.parseInt(st.nextToken()); 
			int weight = Integer.parseInt(st.nextToken()); 
			
			if(gender[from] != gender[to]) queue.add(new Edge(from, to, weight));
		}
		
		
		int cnt=0, sum=0;
		boolean[] visit = new boolean[N+1];
		
		while(cnt<N-1 && !queue.isEmpty()) {
			Edge now = queue.poll();
			int parent_from = findParent(now.from);
			int parent_to = findParent(now.to);
			if(parent_from == parent_to) continue;
			
			parent[parent_to] = parent_from;
			sum += now.weight;
			cnt++;
		}
		System.out.println((cnt==N-1) ? sum : -1);
	}

}
