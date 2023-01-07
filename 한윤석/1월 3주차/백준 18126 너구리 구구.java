public class B18126_너구리구구 {

	static int N;
	static long ans = 0;
	static List<Node> edges [];
	static boolean visit [];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		edges = new LinkedList [N+1];
		visit = new boolean[N+1];
		
		for(int i=1; i<=N; i++) edges[i] = new LinkedList<>();
		
		for(int i=0; i<N-1; i++) {
			String [] inputs = br.readLine().split(" ");
			int A = Integer.parseInt(inputs[0]);
			int B = Integer.parseInt(inputs[1]);
			int C = Integer.parseInt(inputs[2]);
			
			edges[A].add(new Node(B, C));
			edges[B].add(new Node(A, C));
		}
		
		dfs(1, 0);
		
		System.out.println(ans);
	}
	
	static void dfs(int to, long w) {
		visit[to] = true;
		ans = Math.max(w, ans);
		
		for(Node next : edges[to]) {
			if(visit[next.to]) continue;
			
			dfs(next.to, next.w + w);
		}
	}
	
	static class Node{
		int to, w;
		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}
}
