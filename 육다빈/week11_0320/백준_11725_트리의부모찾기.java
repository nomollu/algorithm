package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class S11725_findParents {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer>[] childs = new List[N+1];
		for(int i=1; i<=N; i++) childs[i] = new ArrayList<Integer>();
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			childs[a].add(b);
			childs[b].add(a);
		}
		
		int[] parent = new int[N+1];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i=0; i<size; i++) {
				int now = queue.poll();
				for(int child : childs[now]) {
					if(parent[child]!=0) continue;
					parent[child] = now;
					queue.add(child);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=2; i<=N; i++) sb.append(parent[i]+"\n");
		
		System.out.println(sb);
	}

}
