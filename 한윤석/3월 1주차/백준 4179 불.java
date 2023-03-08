public class B4179_불 {
	
	static int R,C;
	static char map[][];
	static int ans = Integer.MAX_VALUE;
	static int d[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean visit [][];
	static Queue<int []> fires = new LinkedList<>(); //불 위치
	static Queue<int []> jh = new LinkedList<>(); //지훈이 위치

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char [R][C];
		visit = new boolean[R][C];
		
		for(int i=0; i<R; i++) map[i] = br.readLine().toCharArray();
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) 
				if(map[i][j] == 'J') {
					jh.add(new int [] {i, j, 0});
					visit[i][j] = true;
				}
				else if(map[i][j] == 'F') fires.add(new int [] {i,j}); 
		}
		
		bfs();
		
		if(ans == Integer.MAX_VALUE) System.out.println("IMPOSSIBLE");
		else System.out.println(ans);
	}

	static void bfs() {
		while(!jh.isEmpty()) {
			int size = jh.size(); //매 초마다 불 이동 후에 지훈이 이동해야 해서 사이즈 구함
			
			fireSpread(); //불 먼저 이동시키고
			
			for(int j=0; j<size; j++) {
				int pos [] = jh.poll();

				for(int i=0; i<4; i++) {
					int nr = pos[0] + d[i][0];
					int nc = pos[1] + d[i][1];
					
					//탈출 완료
					if(nr < 0 || nc < 0 || nr >= R || nc >= C) {
						ans = pos[2]+1;
						return;
					}
					
					if(map[nr][nc] == '#' || map[nr][nc] == 'F' || visit[nr][nc]) continue;
					
					visit[nr][nc] = true;
					jh.add(new int [] {nr, nc, pos[2]+1});
				}
			}
			
		}
	}
	
	static void fireSpread() {
		int size = fires.size();
		
		for(int i=0; i<size; i++) {
			int pos [] = fires.poll();
			
			for(int k=0; k<4; k++) {
				int nr = pos[0] + d[k][0];
				int nc = pos[1] + d[k][1];
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == 'F' || map[nr][nc] == '#') continue;
				
				map[nr][nc] = 'F';
				fires.add(new int [] {nr, nc});
			}
		}
	}
}
