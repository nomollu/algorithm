public class B12784_인하니카공화국 {

	static int N,M;
	static List<Edge> edges [];
	static boolean visit [];
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			edges = new LinkedList [N+1];
			ans = 0;
			visit = new boolean[N+1];
			
			for(int i=1; i<=N; i++) edges[i] = new LinkedList<>();
			
			while(M-- > 0) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				edges[from].add(new Edge(to, d));
				edges[to].add(new Edge(from , d));
			}

			visit[1] = true;
			recursion(1, Integer.MAX_VALUE);
			
			System.out.println(ans);
		}
	}
	
	static void recursion(int n, int min) {
		boolean flag = false;
		int sum = 0;
		
		// 다음 노드들의 다리 수 파악
		for(Edge e : edges[n]) {
			if(edges[e.to].size() == 1) flag = true; 
			if(!visit[e.to])
				sum += e.d;
		}
		
		// 다음 노드들 중 다리가 1개인 노드가 하나라도 있으면
		if(flag) {
			//현재까지의 노드에서 자르는게 나으면
			if(min <= sum) {
				ans += min;
				return;
			}else { //리프 노드들을 자르는게 나으면
				for(Edge e : edges[n]) {
					if(visit[e.to]) continue;
					if(edges[e.to].size() == 1) {
						ans += e.d;
					}
					else if(!visit[e.to]) { //리프노드가 아니라면
						visit[e.to] = true; 
						recursion(e.to, Math.min(e.d, min));
					}
				}
				return;
			}
		}else { // 리프노드가 하나도 없으면
			for(Edge e : edges[n]) {
				if(visit[e.to]) continue;
				recursion(e.to, Math.min(e.d, min));
			}
		}
	}
	
	static class Edge{
		int to, d;
		public Edge(int to, int d) {
			this.to = to;
			this.d = d;
		}
	}
}
