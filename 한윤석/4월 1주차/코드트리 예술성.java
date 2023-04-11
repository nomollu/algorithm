public class C_예술성 {

	static int N;
	static int map[][];
	static boolean visit[][]; //각 그룹 방문여부 체크 위함
	static boolean groupCheck[][]; //한 그룹에서 방문가능한 그룹들 체크할 때 위함
	static int d[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static long ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		getArtScore();
		for(int i=0; i<3; i++) {
			rotate();
			getArtScore();
		}
		
		System.out.println(ans);
	}
	
	//예술점수 계산
	static void getArtScore() {
		visit = new boolean[N][N];
		long curAns = 0; //이번 회차의 예술점수
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visit[i][j]) {
					Group curGroup = bfs(i, j); //[i,j]에서 만들어지는 그룹 정보
					List<Group> touchedGroup = getTouchedGroup(curGroup); //curGroup과 인접한 그룹들 정보 리스트
					
					//모두 돌면서 예술점수 계산
					for(Group g  : touchedGroup) {
						long calc =(curGroup.pos.size() + g.pos.size()) * curGroup.n * g.n * g.touch; 
						curAns += calc;
					}
				}
			}
		}
		
		ans += curAns;
	}
	
	// [sr, sc]에서 만들어지는 그룹 정보 리턴
	static Group bfs(int sr, int sc) {
		Group curGroup = new Group(map[sr][sc], 0, new LinkedList<>());
		Queue<int []> q = new LinkedList<>();
		q.add(new int [] {sr, sc});
		visit[sr][sc] = true;
		curGroup.pos.add(new Pos(sr, sc));
		
		while(!q.isEmpty()) {
			int [] p = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = p[0] + d[i][0];
				int nc = p[1] + d[i][1];
				
				if(!isValidPos(nr, nc) || visit[nr][nc] || map[nr][nc] != curGroup.n) continue;
				
				visit[nr][nc] = true;
				q.add(new int [] {nr, nc});
				curGroup.pos.add(new Pos(nr, nc));
			}
		}
		
		return curGroup;
	}
	
	//g그룹과 인접한 그룹들의 정보를 리스트로 반환
	static List<Group> getTouchedGroup(Group g){
		List<Group> touchedGroup = new LinkedList<>();
		groupCheck = new boolean[N][N];
		
		for(Pos p : g.pos) {
			for(int i=0; i<4; i++) {
				int nr = p.r + d[i][0];
				int nc = p.c + d[i][1];
				
				if(!isValidPos(nr ,nc) || map[nr][nc] == g.n || visit[nr][nc] || groupCheck[nr][nc]) continue;
				
				Group tGroup = touchGroupBFS(nr, nc, g);
				touchedGroup.add(tGroup);
			}
		}
		
		return touchedGroup;
	}
	
	
	//인접 그룹 정보 리턴
	static Group touchGroupBFS(int r, int c, Group g) {
		Group touchGroup = new Group(map[r][c], 0, new LinkedList<>());
		groupCheck[r][c] = true;
		Queue<Pos> q = new LinkedList<>();
		touchGroup.pos.add(new Pos(r, c));
		q.add(new Pos(r, c));
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = p.r + d[i][0];
				int nc = p.c + d[i][1];
				
				if(!isValidPos(nr, nc) || groupCheck[nr][nc] || (map[nr][nc] != g.n && map[nr][nc] != touchGroup.n)) continue;
				
				for(Pos gpos : g.pos) {
					if(nr == gpos.r && nc == gpos.c) {
						touchGroup.touch++;
						continue;
					}
				}
				if(map[nr][nc] == touchGroup.n) {
					groupCheck[nr][nc] = true;
					q.add(new Pos(nr, nc));
					touchGroup.pos.add(new Pos(nr, nc));
				}
			}
		}
		
		return touchGroup;
	}
	
	//맵 정보 회전
	static void rotate() {
		int cIdx = N/2;
		rotateCross(cIdx);
		rotateSquare(0,0,cIdx-1,cIdx-1);
		rotateSquare(0, cIdx+1, cIdx-1, N-1);
		rotateSquare(cIdx+1, 0, N-1, cIdx-1);
		rotateSquare(cIdx+1, cIdx+1, N-1, N-1);
	}
	
	// 중앙의 십자가 회전
	static void rotateCross(int cIdx) {
		int temp [][] = new int [4][cIdx];
		
		for(int i=0; i<cIdx; i++) {
			temp[0][i] = map[i][cIdx];
			temp[1][i] = map[cIdx][i];
			temp[2][i] = map[N-1-i][cIdx];
			temp[3][i] = map[cIdx][N-1-i];
		}
		for(int i=0; i<cIdx; i++) {
			map[i][cIdx] = temp[3][i];
			map[cIdx][i] = temp[0][i];
			map[N-1-i][cIdx] = temp[1][i];
			map[cIdx][N-1-i] = temp[2][i];
		}
	}
	
	// [sr, sc]부터 [er, sc]까지의 사각형을 회전
	static void rotateSquare(int sr, int sc, int er, int ec) {
		// [0, 3] [1, 4]
		int row = er - sr + 1;
		int col = ec - sc + 1;
		int tempMap [][] = new int [row][col];
		int temp [][] = new int [row][col];
		
		for(int i=sr; i<=er; i++) {
			for(int j=sc; j<=ec; j++) {
				tempMap[i-sr][j-sc] = map[i][j];
			}
		}
		
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) temp[i][j] = tempMap[row - 1 - j][i];
		}
		
		for(int i=sr; i<=er; i++) {
			for(int j=sc; j<=ec; j++) {
				map[i][j] = temp[i-sr][j-sc];
			}
		}
	}
	
	static boolean isValidPos(int nr, int nc) {
		if(nr < 0 || nc < 0 || nr >= N || nc >= N) return false;
		return true;
	}
	
	static class Group{
		int n, touch;
		List<Pos> pos;
		public Group(int n, int touch, List<Pos> pos) {
			this.n = n;
			this.touch = touch;
			this.pos = pos;
		}
	}
	
	static class Pos{
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
