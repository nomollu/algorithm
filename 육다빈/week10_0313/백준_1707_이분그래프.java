package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class S14888_addOperator {
	
	static String bfs(int V, List<Integer>[] nodes) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] visit = new int[V+1];
		int color = 0;
		
		for(int i=1; i<=V; i++) {
			if(visit[i] != 0) continue;

			visit[i] = ++color;
			queue.add(i);

			while(!queue.isEmpty()) {
				color++;
				int size = queue.size();
	
				for(int s=0; s<size; s++) {
					int now = queue.poll();
					for(int node : nodes[now]) {
						if(visit[node] == 0) {
							visit[node] = color;
							queue.add(node);
						}else if(visit[node]!=color && visit[node]%2!=color%2) {
							return "NO";
						}
					}
				}
			}
			
		}
		return "YES";
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
		
			List<Integer>[] nodes = new List[V+1];
			for(int i=1; i<=V; i++) nodes[i] = new ArrayList<Integer>();
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				nodes[a].add(b);
				nodes[b].add(a);
			}
			
			System.out.println(bfs(V, nodes));
		}
	}

}
