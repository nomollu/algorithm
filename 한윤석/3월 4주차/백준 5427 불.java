public class B5427_불 {
	
	static int R,C; //행, 
	static char map [][]; //맵 정
	static Queue<SG> sg; //상근이 위
	static Queue<Pos> fires; //불 위
	static int ans;
	static boolean visit [][]; // 상근이 방문 여
	static int d [][] = {{1,0},{0,1},{-1,0},{0,-1}};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			map = new char [R][C];
			visit = new boolean[R][C];
			sg = new LinkedList<>();
			fires = new LinkedList<>();
			ans = Integer.MAX_VALUE;
			
			//input
			for(int i=0; i<R; i++) {
				String input = br.readLine();
				for(int j=0; j<C; j++) {
					char c = input.charAt(j);
					if(c == '*') fires.add(new Pos(i,j));
					else if(c == '@') sg.add(new SG(i,j,0));
					map[i][j] = c;
				}
			}
			visit[sg.peek().r][sg.peek().c] = true;
			
			bfs();
			
			if(ans == Integer.MAX_VALUE) System.out.println("IMPOSSIBLE");
			else System.out.println(ans);
		}
	}
	
	static void bfs() {
		while(!sg.isEmpty()) {
			int size = sg.size();
			
			fireSpread(); //불 먼저 이
			
			//한 번에 한 타임씩 움직여야 하기 때문에 사이즈 재서 실행해줘야 
			while(size-- > 0) {
				SG cur = sg.poll();
				
				for(int i=0; i<4; i++) {
					int nr = cur.r + d[i][0];
					int nc = cur.c + d[i][1];
					
					//맵 넘어가면 탈출한
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
						ans = cur.t+1;
						return;
					}
					
					if(map[nr][nc] == '#' || map[nr][nc] == '*' || visit[nr][nc]) continue;
					
					visit[nr][nc] = true;
					sg.add(new SG(nr, nc, cur.t + 1));
				}
			}
		}
	}
	
	//불 이동하면서 맵에 반영해
	static void fireSpread() {
		int size = fires.size();
		
		while(size-- > 0) {
			Pos p = fires.poll();
			
			for(int i=0; i<4; i++) {
				int nr = p.r + d[i][0];
				int nc = p.c + d[i][1];
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == '*' || map[nr][nc] == '#') continue;
				
				map[nr][nc] = '*';
				fires.add(new Pos(nr, nc));
			}
		}
	}

	static class Pos{
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class SG{
		int r, c, t;
		public SG(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
}
