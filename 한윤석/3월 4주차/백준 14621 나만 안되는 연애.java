public class B14621_나만안되는연애 {

	static int N,M;
	static char MW[]; //남학교 여학교
	static List<Edge> edges = new LinkedList<>();;
	static int parent [];
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MW = new char [N+1];
		st = new StringTokenizer(br.readLine());
		parent = new int [N+1];
		
		for(int i=1; i<=N; i++) {
			MW[i] = st.nextToken().charAt(0);
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); 
			int v = Integer.parseInt(st.nextToken()); 
			int d = Integer.parseInt(st.nextToken()); 
			
			//같은 성별 학교끼리 붙어있거나, 나 자신인 경우 패스
			if(MW[u] == MW[v] || u == v) continue;
			
			edges.add(new Edge(u, v, d));
		}
		
		// MST를 위한 거리에 따른 오름차순 정렬
		Collections.sort(edges);

		boolean visit [] = new boolean[N+1];
		
		for(Edge e : edges) {
			if(findParent(e.from) == findParent(e.to)) continue;
			union(e.from, e.to);
			visit[e.to] = true; 
			visit[e.from] = true;
			ans += e.d;
		}
		
		//만약 간선 다 봤는데, 방문 안한 노드 있다면 그래프 분리된거임
		boolean flag = true;
		for(int i=1; i<=N; i++) if(!visit[i]) flag = false;
		
		System.out.println(flag ? ans : -1);
	}
	
	static int findParent(int n) {
		if(n == parent[n]) return n;
		else return parent[n] = findParent(parent[n]);
	}
	
	static void union(int a, int b) {
		int aRoot = findParent(a);
		int bRoot = findParent(b);
		
		if(aRoot == bRoot) return;
		
		parent[bRoot] = aRoot;
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to, d;
		public Edge(int from ,int to, int d) {
			this.from = from;
			this.to = to;
			this.d = d;
		}
		@Override
		public int compareTo(Edge o) {
			return this.d - o.d;
		}
	}
}
