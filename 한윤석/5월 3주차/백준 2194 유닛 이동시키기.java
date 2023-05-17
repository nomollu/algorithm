public class B2194_유닛이동시키기 {

	static int R,C,A,B,K, er, ec; //행, 열, 유닛 크기r, 유닛크기c, 장애물 수, 도착 행, 도착 열
	static int d [][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean visit [][];
	static int obstacles [][]; // 장애물 좌표
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visit = new boolean[R+1][C+1];
		obstacles = new int [K][2];
		int ans = -1;
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			obstacles[i] = new int [] {r, c};
		}
		
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		er = Integer.parseInt(st.nextToken());
		ec = Integer.parseInt(st.nextToken());
		// init end
		
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(sr, sc, 0));
		visit[sr][sc] = true;
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			
			if(p.r == er && p.c == ec) {
				ans = p.s;
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nr = p.r + d[i][0];
				int nc = p.c + d[i][1];
				
				if(!isValid(nr, nc)) continue;
				
				visit[nr][nc] = true;
				q.add(new Pos(nr, nc, p.s + 1));
			}
		}
		
		System.out.println(ans);
		
	}
	
	static boolean isValid(int r, int c) {
    // 범위 체크
		if(r <= 0 || c <= 0 || r > R || c > C || visit[r][c]) return false;
		if(r + A - 1 > R || c + B - 1 > C) return false;
		
    // 장애물 겹침 여부 
		for(int obs [] : obstacles) {
			if(obs[0] >= r && obs[0] <= r + A - 1 && obs[1] >= c && obs[1] <= c + B - 1) return false;
		}
		
		return true;
	}
	
	static class Pos{
		int r, c, s;
		public Pos(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}
