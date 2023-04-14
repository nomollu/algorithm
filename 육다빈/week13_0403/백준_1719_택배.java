package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1719_package {

	static class Node{
		int to, weight;
		Node(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
	
	
	// 다익스트라로 풀어야 하는건 알겠는데 코드를 어떻게 짜야할지 모르겠다ㅜ
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Node>[] edges = new List[N+1];
		for(int i=1; i<=N; i++) edges[i] = new ArrayList<Node>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges[from].add(new Node(to, weight));
			edges[to].add(new Node(from, weight));
		}
		
		int[][] dijkstra = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) { // i에서 시작
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(i);
			
			while(!queue.isEmpty()) {
				int size = queue.size();
				for(int s=0; s<size; s++) {
					int now = queue.poll();
					for(Node node : edges[now]) {
//						if().....
					}
				}
			}
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
			}
		}
	}

}
