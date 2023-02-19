public class B12100_2048_easy {

	static int N;
	static int map [][]; //초기 맵 정보
	static int copy [][]; //이동 후의 맵 정보
	static int d[][] = {{1,0},{-1,0},{0,1},{0,-1}};
	static int ans = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		copy = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		backtrack(0, "");
		
		System.out.println(ans);
	}
	
	//@param(cnt : 이동 횟수, seq : 5회 이동시킨 방향 정보) 
	static void backtrack(int cnt, String seq) {
		if(cnt == 5) {
			copyArr();
			
			for(int i=0; i<5; i++) {
				int dir = seq.charAt(i) - '0';
				mergeTile(dir);
				moveTile(dir);
			}
			
			findMaxNum();
			return;
		}
		
		for(int i=0; i<4; i++) {
			backtrack(cnt+1, seq+i);
		}
	}
	
	//dir 방향으로 타일 합치기
	/*
	 * 2 2 2
	 * 4 4 4
	 * 8 8 8
	 * 에서 오른쪽으로 합친다고 하면
	 * 
	 * 2 0 4
	 * 4 0 8
	 * 8 0 16
	 * */
	static void mergeTile(int dir) {
		// 오른쪽, 아래 방향으로 합칠 때
		if(dir % 2 == 0) {
			for(int i=N-1; i>=0; i--) {
				for(int j=N-1; j>=0; j--) {
					mergeTileLogic(i, j, dir);
				}
			}
		}else {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					mergeTileLogic(i, j, dir);
				}
			}
		}
	}
	
	static void mergeTileLogic(int i, int j, int dir) {
		int cur = copy[i][j];
		
		if(cur == 0) return;
		
		int sameTilePos [] = findSameTile(i, j, dir);
		
		if(sameTilePos[0] == -1 && sameTilePos[1] == -1) return;
		
		copy[sameTilePos[0]][sameTilePos[1]] = 0;
		copy[i][j] *= 2;
	}
	
	//모든 타일 dir방향으로 이동시키기
	/*
	 * 2 0 4
	 * 4 0 8
	 * 8 0 16
	 * 일 때 moveTile의 결과는
	 * 0 2 4
	 * 0 4 8
	 * 0 8 16
	 * */
	static void moveTile(int dir) {
		// 오른쪽, 아래 방향으로 합칠 때
		if(dir % 2 == 0) {
			for(int i=N-1; i>=0; i--) {
				for(int j=N-1; j>=0; j--) {
					if(copy[i][j] != 0) moveTileLogic(i, j, dir);
				}
			}
		}else {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(copy[i][j] != 0) moveTileLogic(i, j, dir);
				}
			}
		}
	}
	
	static void moveTileLogic(int i, int j, int dir) {
		int step = 1;
		int tr = i;
		int tc = j;
		
		while(true) {
			int nr = i + d[dir][0] * step;
			int nc = j + d[dir][1] * step;
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= N || copy[nr][nc] != 0) break;
			
			tr = nr;
			tc = nc;
			step++;
		}
		copy[tr][tc] = copy[i][j]; 
		if(i != tr || j != tc) copy[i][j] = 0;
	}
	
	//dir 방향으로 이동할 때 r, c타일에 합쳐지는 타일이 있는지 확인
	//있다면 합쳐질 타일의 좌표를, 없다면 {-1, -1} 리턴
	static int [] findSameTile(int r, int c, int dir) {
		int [] sameTilePos = {-1, -1};
		int step = 1;
		int oppositeDir = dir % 2 == 0 ? dir+1 : dir-1;
		
		while(true) {
			int nr = r + d[oppositeDir][0] * step;
			int nc = c + d[oppositeDir][1] * step;
			step++;
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= N) break;
			if(copy[nr][nc] != 0 && copy[nr][nc] != copy[r][c]) break;
			if(copy[nr][nc] == 0) continue;
			
			sameTilePos[0] = nr;
			sameTilePos[1] = nc;
			break;
		}
		
		return sameTilePos;
	}
	
	//copy 배열 초기화
	static void copyArr() {
		copy = new int [N][N];
		for(int i=0; i<N; i++) for(int j=0; j<N; j++) copy[i][j] = map[i][j];
	}
	
	//모든 타일중 가장 큰 수를 정답으로 갱신
	static void findMaxNum() {
		int maxNum = 0;
		for(int i=0; i<N; i++) for(int j=0; j<N; j++) maxNum = Math.max(maxNum, copy[i][j]);
		ans = Math.max(maxNum, ans);
	}
}
