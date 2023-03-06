package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G14699_mountainClimbing {
	static int[] height, result;
	static List<Integer>[] edges;
	
	static class Node implements Comparable<Node>{
		int idx, height; // idx:쉼터번호, height:높이, shelter:오를 수 있는 최대 쉼터 수
		
		Node(int idx, int height){
			this.idx = idx;
			this.height = height;
		}
		@Override
		public int compareTo(Node o) { // 오름차순 정렬
			return o.height - this.height;
		}
		
	}
	
	static void bfs(int start) {
		result[start] = 1;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		
		int cnt = 1;
		while(!queue.isEmpty()) {
			cnt++;
			int size = queue.size();
			
			for(int s=0; s<size; s++) {
				int now = queue.poll();
				
				for(int n : edges[now]) {
					if(height[n]<height[now] && result[n]<cnt) {
						result[n] = cnt;
						queue.add(n);
					}
				}
				
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Node[] nodes = new Node[N];
		height = new int[N+1];
		edges = new List[N+1];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int h = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(i+1, h);
			height[i+1] = h;
			edges[i+1] = new ArrayList<Integer>();
		}
		
		Arrays.sort(nodes);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}
		
		result = new int[N+1];
		for(int i=0; i<N; i++) {
			if(result[nodes[i].idx]==0) bfs(nodes[i].idx);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) sb.append(result[i]+"\n");
		System.out.println(sb);
	}

}
