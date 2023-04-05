package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G12784_InhanikaRepublic {
	static int N, M;
	static List<Node>[] edges;
	static boolean[] visit;
	
	static class Node{
		int to, weight;
		Node(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int dfs(int parent, int weight) {
		int sum = 0;
		
		for(Node node : edges[parent]) {
			if(visit[node.to]) continue;
			visit[node.to] = true;
			sum += dfs(node.to, node.weight); // 자식노드의 하위에 있는 가중치들의 최소 합
		}
		
		if(weight==0) return sum; // parent가 루트노드인 경우
		else if(sum==0) return weight; // parent가 리프노드인 경우
		else return Math.min(sum, weight);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			edges = new List[N+1];
			for(int i=1; i<=N; i++) edges[i] = new ArrayList<Node>();
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				edges[from].add(new Node(to, weight));
				edges[to].add(new Node(from, weight));
			}
			
			visit = new boolean[N+1];
			visit[1] = true;
			
			System.out.println(dfs(1, 0));
		}
	}

}
