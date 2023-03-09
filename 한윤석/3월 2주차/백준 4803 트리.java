public class B4803_트리 {
	
	static int n,m; //정점의 수, 간선의 수
	static int ans;
	static List<Integer> edges [];
	static boolean visit []; //방문여부
	static int parent []; //i의 부모

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 1;
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			ans = 0;
			
			if(n == 0 || m == 0) break;
			edges = new LinkedList [n+1];
			visit = new boolean[n+1];
			parent = new int[n+1];
			
			for(int i=1; i<=n; i++) {
				edges[i] = new LinkedList<>();
				parent[i] = i;
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				edges[a].add(b);
				edges[b].add(a);
				union(a, b);
			}
			
			//union을 통해 집합의 루트들만 parentSet에 담음
			Set<Integer> parentSet = new LinkedHashSet<>();
			for(int i=1; i<=n; i++) parentSet.add(parent[i]);
			
			//집합별로 dfs 돌려봄
			for(int i : parentSet) {
				visit[i] = true;
				if(dfs(i, -1)) ans++;
			}
			
			if(ans == 0) System.out.println("Case " + T + ": No trees.");
			else if(ans == 1) System.out.println("Case "+ T +": There is one tree.");
			else System.out.println("Case "+ T + ": A forest of " + ans + " trees.");
			T++;
		}
	}
	
	static int getParent(int n) {
		if(n == parent[n]) return n;
		else return parent[n] = getParent(parent[n]);
	}
	
	static void union(int a, int b) {
		int aRoot = getParent(a);
		int bRoot = getParent(b);
		if(aRoot == bRoot) return;
		parent[bRoot] = aRoot;
	}
	
	static boolean dfs(int n, int prev) {
		for(int i : edges[n]) {
			if(i == prev || i == n) continue; //n에서 갈 수 있는 간선과 바로 직전 노드가 같을 때, 혹은 1 1과 같은 간선 정보가 주어졌을 때
			if(visit[i]) return false; //이미 방문했다는건 사이클 존재한다는 의미
			visit[i] = true;
			if(!dfs(i, n)) return false;
		}
		return true;
	}
}
