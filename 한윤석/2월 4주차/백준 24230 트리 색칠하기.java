public class B24230_트리색칠하기 {

	static int N;
	static int targetColor[];
	static int ans = 0;
	static boolean visit [];
	static List<Integer> tree []; //연결관계
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		targetColor = new int [N+1];
		tree = new LinkedList[N+1];
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
		
		for(int i=1; i<=N; i++) {
			if(!visit[i] &&  targetColor[i] != 0) {
				visit[i] = true;
				dfs(i, targetColor[i]);
				ans++;
			}
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int node, int tc) {
		visit[node]  = true;
		for(int i : tree[node]) {
			if(targetColor[node] == targetColor[i] && !visit[i])
			dfs(i, tc);
		}
	}
}
