public class B21278_호석이두마리치킨 {

	static int N,M;
	static int route[][]; //[i]에서 [j]로 가는 거리
	static int select [] = new int [2]; //뽑은 2개의 건물
	static boolean visit []; //조합 때 방문 체크
	static int ans [] = {0, 0, Integer.MAX_VALUE}; // 건물1, 건물2, 거리
	static final int INF = 1000000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		route = new int [N+1][N+1];
		visit = new boolean [N+1];
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(route[i], INF);
			route[i][i] = 0;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			route[a][b] = 1;
			route[b][a] = 1;
		}
		
		floyd();
		
		// 모든 건물중 2개 고름
		comb(1, 0);
		System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
	}
	
	static void comb(int idx, int selCnt) {
		if(selCnt == 2) {
			
			int len = 0;
			// 선택한 위치에서 i로 가는 제일 짧은 거리를 합함
			for(int i=1; i<=N; i++) {
				if(i == select[0] || i == select[1]) continue;
				len += Math.min(route[i][select[0]], route[i][select[1]]);
			}
			
			len *= 2;
			if(len < ans[2]) {
				ans[0] = select[0];
				ans[1] = select[1];
				ans[2] = len;
			}
			
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(visit[i]) continue;
			
			visit[i] = true;
			select[selCnt] = i;
			comb(idx+1, selCnt+1);
			visit[i] = false;
		}
	}
	
	static void floyd() {
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					//i에서 j로 바로 가는 것보다 k 걸쳐서 가는게 더 빠르면 갱신
					if(route[i][j] <= route[i][k] + route[k][j]) continue;
					route[i][j] = route[i][k] + route[k][j];
				}
			}
		}
	}
}
