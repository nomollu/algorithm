public class B17086_아기상어2 {

	static int R, C;
	static int map[][];
	static int safety[][];
	static int ans = 0;
	static int d[][] = {{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		List<int []> sharks = new LinkedList<>();
		map = new int [R][C];
		safety = new int [R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				safety[i][j] = Integer.MAX_VALUE;
				if(map[i][j] == 1) sharks.add(new int [] {i,j});
			}
		}
		
		for(int pos [] : sharks) bfs(pos[0], pos[1]);
		
		for(int i=0; i<R; i++) for(int j=0; j<C; j++) ans = Math.max(ans, safety[i][j]);
		
		System.out.println(ans);
	}
	
	static void bfs(int r, int c) {
		Queue<int []> q = new LinkedList<>();
		q.add(new int [] {r, c, 0});
		boolean visit [][] = new boolean [R][C];
		visit[r][c] = true;
		safety[r][c] = 0;
		
		while(!q.isEmpty()) {
			int pos [] = q.poll();
			
			for(int i=0; i<8; i++) {
				int nr = pos[0] + d[i][0];
				int nc = pos[1] + d[i][1];
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc] || map[nr][nc] == 1) continue;
				
				visit[nr][nc] = true;
				safety[nr][nc] = Math.min(safety[nr][nc], pos[2] + 1);
				q.add(new int [] {nr, nc, pos[2] + 1});
			}
		}
		
	}
}
