public class B1189_컴백홈 {
	
	static char map [][]; //맵정보
	static boolean check[][]; //방문체크
	static int R,C,K;
	static int d [][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		check = new boolean[R][C];
		
		for(int i=0; i<R; i++) map[i] = br.readLine().toCharArray();
		
		check[R-1][0] = true;
		backtrack(R-1, 0, 1);
		
		
		System.out.println(ans);
	}
	
	static void backtrack(int r, int c, int step) {
		if(r == 0 && c == C-1 && step == K) {
			ans++;
			return;
		}
		
		if(step > K) return; // K번 넘게 이동한 정보는 필요 없어!
		
		for(int i=0; i<4; i++) {
			int nr = r + d[i][0];
			int nc = c + d[i][1];
			int cal = (Math.abs(nr - 0) + Math.abs(nc - (C-1))) - (K - step); //현재 위치에서 남은 step으로 목표지점을 방문할 수 있는가
			
			if(nr < 0 || nc < 0 || nr >= R || nc >= C || check[nr][nc] || map[nr][nc] == 'T' || cal > 0) continue;
			
			//한 번 다녀온 좌표는 이동하면 안되므로 visit체크가 필요함
			check[nr][nc] = true;
			backtrack(nr, nc, step+1);
			check[nr][nc] = false;
		}
		
	}
}
