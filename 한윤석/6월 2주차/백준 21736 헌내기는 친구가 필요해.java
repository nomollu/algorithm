public class B21736_헌내기는친구가필요해 {

	static int R,C;
	static char map [][];
	static boolean visit[][];
	static int ans = 0;
	static int d[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean [R][C];
		int sr=0, sc=0;
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) if(map[i][j] == 'I') {
				sr = i;
				sc = j;
			}
		}
		
		dfs(sr, sc);
		
		if(ans == 0) System.out.println("TT");
		else System.out.println(ans);
	}
	
	static void dfs(int r, int c) {
		visit[r][c] = true;
		
		for(int i=0; i<4; i++) {
			int nr = r + d[i][0];
			int nc = c + d[i][1];
			
			if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc] || map[nr][nc] == 'X') continue;
			
			if(map[nr][nc] == 'P') ans++;
			dfs(nr, nc);
		}
	}
}
