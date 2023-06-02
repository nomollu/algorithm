public class C_방화벽설치하기 {

	static int R,C;
	static int map [][]; // 맵 정보 
	static boolean visit [][]; // 불이 퍼졌는지 여
	static int ans = 0; 
	static int d[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int [R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		backtrack(0, 0, 0);
		
		System.out.println(ans);
	}

	static void backtrack(int sr, int sc, int cnt) {
		// 추가 방화벽 3개 다 놨다
		if(cnt == 3) {
			visit = new boolean [R][C];
			spreadFire(); // 불 퍼지고 
			getSafeArea(); // 불 안 퍼진 영역 확인 
			return;
		}
		
		for(int i=sr; i<R; i++) {
			for(int j= i == sr ? sc : 0; j<C; j++) {
				// 이미 방화벽이거나, 불인 공간일 땐 패
				if(map[i][j] == 2 || map[i][j] == 1) continue;
				
				map[i][j] = 1;
				backtrack(i, j, cnt+1);
				map[i][j] = 0;
			}
		}
	}
	
	static void spreadFire() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(visit[i][j] || map[i][j] != 2) continue;
				bfs(i, j);
			}
		}
	}
	
	// [r, c]불이 퍼짐
	static void bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(r, c));
		visit[r][c] = true;
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = p.r + d[i][0];
				int nc = p.c + d[i][1];
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc] || map[nr][nc] == 1) continue;
				
				visit[nr][nc] = true;
				q.add(new Pos(nr, nc));
			}
		}
	}
	
	static void getSafeArea() {
		int cnt = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) if(map[i][j] == 0 && !visit[i][j]) cnt++;
		}
		ans = Math.max(ans, cnt);
	}
	
	static class Pos{
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
