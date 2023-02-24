public class B24230_트리색칠하기 {

	static int N;
	static int color [];
	static int targetColor[];
	static int ans = 0;
	static boolean visit [];
	static List<Integer> tree []; // i에서 갈 수 있는 다른 노드들
	static List<Integer> seq = new LinkedList<>(); //루트에서 자식순으로 저장된 노드들
	static int parent []; // i 노드의 부모
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		color = new int [N+1];
		targetColor = new int [N+1];
		tree = new LinkedList[N+1];
		parent = new int [N+1];
		visit = new boolean[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i=1; i<=N; i++) {
			targetColor[i] = Integer.parseInt(st.nextToken());
			tree[i] = new LinkedList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		
		seq.add(1);
		makeSeq(1, 1);
		
		dfs(1, targetColor[1]);
		
		System.out.println(ans);
	}
	
	static void makeSeq(int node, int prev) {
		parent[node] = prev;
		visit[node] = true;
		
		for(int i : tree[node]) {
			if(visit[i]) continue;
			seq.add(i);
			makeSeq(i, node);
		}
	}
	
	static void dfs(int node, int tc) {
		color[node] = tc;
		for(int i : tree[node]) {
			if(parent[i] != node) continue;
			dfs(i, tc);
		}
	}
	
	static boolean isFinished() {
		boolean flag = true; 
		for(int i=1; i<=N; i++) if(color[i] != targetColor[i]) flag=false;
		return flag;
	}
}
