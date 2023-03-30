public class B25307_시루의백화점구경 {
	
	static int R,C,K,sr,sc, ans;
	static int m[][]; //0빈칸, 1벽, 2의자, 3마네킹, 4초기위치
	static boolean visit [][]; //시루의 방문 여부
	static boolean marking[][]; //마네킹으로 인해 가지 못하는 칸 
	static int d[][] = {{1,0},{-1,0},{0,1},{0,-1}};
	static Queue<int []> mannequins = new LinkedList<>(); //마네킹 위치들

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		m = new int[R][C];
		visit = new boolean[R][C];
		marking = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
				if(m[i][j] == 4) {
					sr = i;
					sc = j;
				}else if(m[i][j] == 3) mannequins.add(new int [] {i, j, 0});
			}
		}
		
		//마네킹으로 인해 접근 불가한 칸을 마킹하는 작업
		markRange();
		
		if(bfs()) System.out.println(ans);
		else System.out.println(-1);
	}
	
	static boolean bfs() {
		Queue<int []> q = new LinkedList<>();
		q.add(new int [] {sr, sc, 0});
		visit[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int [] p = q.poll();
			
			if(m[p[0]][p[1]] == 2) {
				ans = p[2];
				return true;
			}
			
			for(int i=0; i<4; i++) {
				int nr = p[0] + d[i][0];
				int nc = p[1] + d[i][1];
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc] || m[nr][nc] == 1 || m[nr][nc] == 3 || marking[nr][nc]) continue;
				
				visit[nr][nc] = true;
				q.add(new int [] {nr, nc, p[2]+1});
			}
		}
		
		return false;
	}
	
	static void markRange() {
		while(!mannequins.isEmpty()) {
			int size = mannequins.size();
			
			/*4 4 2
			 *0 0 0 4
			 *2 0 0 0
			 *0 0 0 3
			 *0 3 0 0
			 *과 같은 케이스 통과 위해서 size를 재서 bfs를 돌려야 함!
			 * */
			while(size-- > 0) {
				int [] p = mannequins.poll();
				marking[p[0]][p[1]] = true;
				
				if(p[2] >= K) continue;
				
				for(int i=0; i<4; i++) {
					int nr = p[0] + d[i][0];
					int nc = p[1] + d[i][1];
					
					if(nr < 0 || nc < 0 || nr >= R || nc >= C || marking[nr][nc]) continue;
					
					marking[nr][nc] = true;
					m[nr][nc] = 1;
					mannequins.add(new int [] {nr, nc, p[2]+1});
				}
			}
		}
	}
}
