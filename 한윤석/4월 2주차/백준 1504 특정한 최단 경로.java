public class B1504_특정한최단경로 {

	static int N, E;
	static List<Edge> edges [];
	static final int INF = 1000000000; //Integer.maxvalue 하면 오버플로우 남
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edges = new LinkedList[N+1];
		
		for(int i=1; i<=N; i++) edges[i] = new LinkedList<>();
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from  = Integer.parseInt(st.nextToken());
			int to  = Integer.parseInt(st.nextToken());
			int d  = Integer.parseInt(st.nextToken());
			edges[from].add(new Edge(to, d));
			edges[to].add(new Edge(from, d));
		}
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		int ans = Math.min(dijkstra(v1, v2), dijkstra(v2, v1));
		
		if(ans == INF) System.out.println(-1);
		else System.out.println(ans);
	}
	
	static int dijkstra(int v1, int v2) {
		int v1d[] = new int [N+1]; //1에서 v1으로 가는 최단거리
		int v2d[] = new int [N+1]; //v1에서 v2로 가는 최단거리
		int nd[] = new int [N+1]; //v2에서 n으로 가는 최단거리
		int ans = INF;
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		Arrays.fill(v1d, INF);
		Arrays.fill(v2d, INF);
		Arrays.fill(nd, INF);
		v1d[1] = 0; //0으로 시작점 초기화 안 해주면 v1 = 1일 때 
		v2d[v1] = 0;
		nd[v2] = 0;

		//1 -> v1
		pq.add(new Edge(1, 0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if(v1d[e.to] < e.d) continue;
			
			for(Edge ne : edges[e.to]) {
				if(v1d[ne.to] <= ne.d + e.d) continue;
				
				v1d[ne.to] = ne.d + e.d;
				pq.add(new Edge(ne.to, v1d[ne.to]));
			}
		}
		
		if(v1d[v1] == INF) return INF;
		
		//v1 -> v2
		pq.add(new Edge(v1, 0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if(v2d[e.to] < e.d) continue;
			
			for(Edge ne : edges[e.to]) {
				if(v2d[ne.to] <= ne.d + e.d) continue;
				
				v2d[ne.to] = ne.d + e.d;
				pq.add(new Edge(ne.to, v2d[ne.to]));
			}
		}
		
		if(v2d[v2] == INF) return INF;
		
		//v2 -> N
		pq.add(new Edge(v2, 0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(nd[e.to] < e.d) continue;
			
			for(Edge ne : edges[e.to]) {
				if(nd[ne.to] <= ne.d + e.d) continue;
				
				nd[ne.to] = ne.d + e.d;
				pq.add(new Edge(ne.to, nd[ne.to]));
			}
		}
		
		if(nd[N] == INF) return INF;
		
		ans = v1d[v1] + v2d[v2] + nd[N];
		
		return ans;
	}
	
	static class Edge implements Comparable<Edge>{
		int to, d;
		public Edge(int to, int d) {
			this.to = to;
			this.d = d;
		}
		@Override
		public int compareTo(Edge o) {
			return this.d - o.d;
		}
	}
}
