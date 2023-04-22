public class C자율주행자동차 {

	static int R,C,cr,cc,cd;
	static int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};
	static int map[][];
	static boolean visit[][];
	static int ans = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int [R][C];
		visit = new boolean[R][C];
		st = new StringTokenizer(br.readLine());
		cr = Integer.parseInt(st.nextToken());
		cc = Integer.parseInt(st.nextToken());
		cd = Integer.parseInt(st.nextToken());
		visit[cr][cc] = true;
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
//			System.out.println(cr + " " + cc + " " + cd + " " + ans);
			if(!turnLeft()) {
				if(!backUp()) {
					break;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean turnLeft() {
		for(int i=1; i<=4; i++) {
			int calc = cd - i;
			int nd = calc < 0 ? 4+calc : calc;
			int nr = cr + d[nd][0];
			int nc = cc + d[nd][1];
			
			if(map[nr][nc] == 1 || visit[nr][nc]) continue;
			
			cd = nd;
			cr = nr;
			cc = nc;
			visit[nr][nc] = true;
			ans++;
			return true;
		}
		
		return false;
	}
	
	static boolean backUp() {
		int nd = (cd + 2) % 4;
		int nr = cr + d[nd][0];
		int nc = cc + d[nd][1];
		
		if(map[nr][nc] == 1) return false;
		
		if(!visit[nr][nc]) {
			visit[nr][nc] = true;
			ans++;
		}
		cr = nr;
		cc = nc;
		
		return true;
	}
}
