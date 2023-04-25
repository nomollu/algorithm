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
			ans = recursion(1, 0);
			
			System.out.println(ans);
		}
	}
	
	static int recursion(int n, int d) {
		int sum = 0;
		
		for(Edge e : edges[n]) {
			if(visit[e.to]) continue;
			
			visit[e.to] = true;
			sum += recursion(e.to, e.d);
		}
		
		if(d == 0) return sum;
		else if(sum == 0) return d;
		else return Math.min(sum, d);
	}
	
	static class Edge{
		int to, d;
		public Edge(int to, int d) {
			this.to = to;
			this.d = d;
		}
	}
}
