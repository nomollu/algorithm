public class B20168_골목대장호석 {
	
	static int N,M,A,B,C; // 노드 수, 엣지 수, 시작, 끝, 가진 돈
	static List<Edge> edges [];
	static boolean visit[];
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		edges = new LinkedList[N+1];
		visit = new boolean [N+1];
		
		for(int i=1; i<=N; i++) edges[i] = new LinkedList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			edges[start].add(new Edge(to, dist));
			edges[to].add(new Edge(start, dist));
		}
		
		visit[A] = true;
		dfs(A, 0, 0);
		
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}

	static void dfs(int n, int max, int sum) {
		if(n == B) { //목적지 도착했을 때 정답 갱신
			ans = Integer.min(ans, max);
			return;
		}
		
		for(Edge e : edges[n]) {
			int maxVal = Math.max(max, e.d);
			// 이미 방문했거나, 가진 돈 초과하거나, 정답보다 큰 최대값 가지면
			if(visit[e.to] || sum + e.d > C || maxVal > ans) continue;
			
			visit[e.to] = true;
			dfs(e.to, maxVal, sum + e.d);
			visit[e.to] = false; 
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
