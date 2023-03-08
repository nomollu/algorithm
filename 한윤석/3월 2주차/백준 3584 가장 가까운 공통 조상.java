public class B3584_가장가까운공통조상 {
	
	static int N;
	static int parent []; // idx의 부모가 parent[i]
	static boolean check []; //[i]번 노드가 부모로 체크된 적 있는지
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			parent = new int [N+1];
			check = new boolean [N+1];
			ans = 0;
			StringTokenizer st; 
			
			for(int i=1; i<=N; i++) parent[i] = i;
			
			for(int i=0; i<N-1; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				parent[B] = A;
			}
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			findParent(a, false); //a노드에서 찾을땐 체크만
			findParent(b, true); //b노드에서 찾을땐 체크되어 있는 부모 있으면 해당 노드가 정답
			
			System.out.println(ans);
		}
	}

	static void findParent(int n, boolean flag ) {
		if(flag && check[n]) { //b노드로 탐색하면서 이미 체크되어있는 노드면
			ans = n;
			return;
		}
		check[n] = true; //부모로 쓴 적 있다
		//루트노드가 아니라면 재귀
		if(parent[n] != n) findParent(parent[n], flag);
	}
}
