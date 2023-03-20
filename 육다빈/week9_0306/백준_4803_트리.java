package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4803_tree {
	static List<Integer>[] nodes;
	static int[] visit;
	
	static boolean checkTree(int to, int from, int idx) { // tree인지 아닌지 판별
		
		for(int node : nodes[to]) {
			if(visit[node]==idx && node!=from) { // 사이클 발생
				checkCycle(node);
				return false;
			}
			else if(visit[node]==0) { // 첫방문인 노드라면
				visit[node] = idx;
				if(!checkTree(node, to, idx)) return false;
			}
				
		}
		
		return true;
	}
	
	static void checkCycle(int start) { // 그래프 부분 -1로 visit 체크
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		visit[start] = -1;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int node : nodes[now]) {
				if(visit[node]==-1) continue;
				else {
					visit[node] = -1;
					queue.add(node);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1; ; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			if(N==0) break;
			
			nodes = new List[N+1];
			
			for(int i=1; i<=N; i++) nodes[i] = new ArrayList<Integer>();
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				nodes[a].add(b);
				nodes[b].add(a);
			}
			
			int idx=0, cnt=0;
			visit = new int[N+1];
			for(int i=1; i<=N; i++) {
				if(visit[i]==0) {
					visit[i] = ++idx;
					if(checkTree(i, i, idx)) cnt++; 
				}
			}
			
			if(cnt>1) System.out.println("Case " + t + ": A forest of " + cnt + " trees.");
			else if(cnt==1) System.out.println("Case " + t + ": There is one tree.");
			else System.out.println("Case " + t + ": No trees.");
		}
	}

}
