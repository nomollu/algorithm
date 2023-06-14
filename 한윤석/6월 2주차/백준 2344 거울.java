public class B2344_거울 {

	static int R,C;
	static int map [][];
	static int d[][] = {{0,1},{0,-1},{-1,0},{1,0}};
	static int rd [] = {2, 3, 0, 1}; // [i]방향으로 들어왔을 때 반사되는 방향
	static Queue<Start> q = new LinkedList<>(); // 빛 쏘는 지점과 방향들
	static StringBuilder sb = new StringBuilder(); //정답 담을거
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int [R+2][C+2];
		
		for(int i=1; i<=R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=C; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		setEntrance();
		
		while(!q.isEmpty()) {
			Start s = q.poll(); // 모든 시작지점에서 다 쏴봄
			shootLight(s.r, s.c, s.d);
		}
		
		System.out.println(sb);
	}
	
	static void shootLight(int r, int c, int dir) {
		int nd = (map[r][c] == 1 && isBoundary(r, c)) ? rd[dir] : dir; // 현재 위치 왜곡 거울 만났으면 반사시킴
		int nr = r + d[nd][0];
		int nc = c + d[nd][1];
		
		//범위 벗어났으면 빛이 나간 것
		if(!isBoundary(nr, nc)) {
			sb.append(map[nr][nc]).append(" ");
			return;
		}
		
		shootLight(nr, nc, nd);
	}
	
	// map에 탈출구 번호 매기고, 빛 시작 위치 큐에 담음
	static void setEntrance() {
		int num = 1;
		
		for(int i=1; i<=R; i++) {
			map[i][0] = num++;
			q.add(new Start(i, 0, 0));
		}
		for(int i=1; i<=C; i++) {
			map[R+1][i] = num++;
			q.add(new Start(R+1, i, 2));
		}
		for(int i=R; i>=1; i--) {
			map[i][C+1] = num++;
			q.add(new Start(i, C+1, 1));
		}
		for(int i=C; i>=1; i--) {
			map[0][i] = num++;
			q.add(new Start(0, i, 3));
		}
	}
	
	static boolean isBoundary(int r, int c) {
		if(r < 1 || r > R || c < 1 || c > C) return false;
		return true;
	}
	
	static class Start{
		int r, c, d;
		public Start(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
