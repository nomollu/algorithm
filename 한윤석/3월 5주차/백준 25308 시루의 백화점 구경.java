public class B25307_시루의백화점구경 {
	
	static int R,C,K,sr,sc, ans;
	static int m[][]; //0빈칸, 1벽, 2의자, 3마네킹, 4초기위치
	static boolean visit [][];
	static boolean marking[][];
	static int d[][] = {{1,0},{-1,0},{0,1},{0,-1}};

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
				}
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(m[i][j] == 3) markRange(i,j);
			}
		}
		
//		for(int i=0; i<R; i++) {
//			for(int j=0; j<C; j++) System.out.print(m[i][j] + " ");
//			System.out.println();
//		}
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
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc] || m[nr][nc] == 1 || m[nr][nc] == 3) continue;
				
				visit[nr][nc] = true;
				q.add(new int [] {nr, nc, p[2]+1});
			}
		}
		
		return false;
	}
	
	static void markRange(int r, int c) {
		marking[r][c] = true;
		Queue<int []> q = new LinkedList<>();
		q.add(new int [] {r, c, 0});
//System.out.println(r + " " + c);
		while(!q.isEmpty()) {
			int [] p = q.poll();
			if(p[2] >= K) continue;
			
			for(int i=0; i<4; i++) {
				int nr = p[0] + d[i][0];
				int nc = p[1] + d[i][1];
				
//				System.out.println("next" + nr + " " + nc );
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || m[nr][nc] == 4) continue;
				
				marking[nr][nc] = true;
				m[nr][nc] = 1;
				q.add(new int [] {nr, nc, p[2]+1});
			}
		}
	}
}
