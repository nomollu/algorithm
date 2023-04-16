public class B13565_침투 {

	static int R,C;
	static char map [][];
	static boolean visit [][];
	static int d[][] = {{1,0},{-1,0},{0,1},{0,-1}};
	static boolean ans = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char [R][C];
		visit = new boolean [R][C];
		
		for(int i=0; i<R; i++) map[i] = br.readLine().toCharArray();
		
		for(int i=0; i<C; i++) {
			if(visit[0][i] || map[0][i] == '1' || ans) continue;
			visit[0][i] = true;
			dfs(0, i);
		}
		
		System.out.println(ans ? "YES" : "NO");
	}
	
	static void dfs(int r, int c) {
		if(r == R-1) {
			ans = true;
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nr = r + d[i][0];
			int nc = c + d[i][1];
			
			if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc] || map[nr][nc] == '1') continue;
			
			visit[nr][nc] = true;
			dfs(nr, nc);
		}
	}
}
