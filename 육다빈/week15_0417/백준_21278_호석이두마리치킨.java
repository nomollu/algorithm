package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G21278_hoseoksTwoChickens {
	static int N, max;
	static String result;
	static List<Integer>[] nodes;
	static boolean[] visit;
	
	static void comb(int start, int cnt) {
		if(cnt==2) {
			bfs();
			return;
		}
		for(int i=start; i<=N; i++) {
			visit[i] = true;
			comb(i+1, cnt+1);
			visit[i] = false;
		}
	}
		
	static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] v = new boolean[N+1];
		String tmpResult = "";
		
		for(int i=1; i<=N; i++) {
			if(visit[i]) {
				v[i] = true;
				queue.add(i);
				tmpResult += i + " ";
			}
		}
		
		int sum=0, depth=1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int s=0; s<size; s++) {
				int now = queue.poll();
				
				for(int node : nodes[now]) {
					if(v[node]) continue;
					v[node] = true;
					queue.add(node);
					sum += depth;
					if(max!=0 && max <= sum) return; // 최소길이가 아니게 되므로 더 갈 필요가 없다
				}
			}
			depth++;
		}
		
		max = sum;
		result = tmpResult;
		return;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		nodes = new List[N+1];
		for(int i=1; i<=N; i++) nodes[i] = new ArrayList<Integer>();
		
		int M = Integer.parseInt(st.nextToken());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].add(b);
			nodes[b].add(a);
		}
		
		visit = new boolean[N+1];
		comb(1, 0);
		
		System.out.println(result + max*2);
	}

}
