public class C_2개의사탕 {
	
	static int N,M;
	static char map[][];
	static int d[][] = {{1,0}, {0,1}, {-1, 0}, {0, -1}};
	static int memo [][];
	static int ans = Integer.MAX_VALUE;
	static int goal [] = new int[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int red [] = new int [2];
		int blue [] = new int [2];
		memo = new int [N][M];
		map = new char[N][M];
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				memo[i][j] = Integer.MAX_VALUE;
				if(map[i][j] == 'R') {
					red[0] = i;
					red[1] = j;
					memo[i][j] = 0;
				}else if(map[i][j] == 'B') {
					blue[0] = i;
					blue[1] = j;
				}else if(map[i][j] == 'O') {
					goal[0] = i;
					goal[1] = j;
				}
			}
		}
		
		backtrack(0, red, blue, -1);
		
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	
	static boolean backtrack(int cnt, int [] red, int [] blue, int prev) {
		if(cnt > 10) return false;
		
		if(blue[0] == goal[0] && blue[1] == goal[1]) return false;

		
		for(int i=0; i<4; i++) {
			if(i != -1 && i % 2 == prev % 2) continue;
			char copy [][] = new char [N][M];
			
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(map[j][k] == '#') copy[j][k] = '#';
					else copy[j][k] = '.';
				}
			}

			int nextPos [] = moveForwardsDir(i, red, blue, copy);
			

			if(nextPos[2] == goal[0] && nextPos[3] == goal[1]) continue;
			if(nextPos[0] == goal[0] && nextPos[1] == goal[1]) {
				if(cnt != 10) ans = Math.min(ans, cnt+1);
				return true;
			}
			
			if((red[0] != nextPos[0] && red[1] != nextPos[1]) && memo[red[0]][red[1]] + 1 >= memo[nextPos[0]][nextPos[1]]) continue;
			memo[nextPos[0]][nextPos[1]] = memo[red[0]][red[1]] + 1;
			
			if(backtrack(cnt+1, new int [] {nextPos[0], nextPos[1]}, new int [] {nextPos[2], nextPos[3]}, i)) continue;
		}
		
		return false;
	}
	
	static int [] moveForwardsDir(int dir, int [] red, int [] blue, char copy[][]) {
		int nextPos [] = {red[0], red[1], blue[0], blue[1]};
		boolean isRedDown = red[0] >= blue[0]; //빨강이 더 아래에 있는지
		boolean isRedRight = red[1] >= blue[1]; //빨강이 더 오른쪽에 있는지
		
		if(dir % 2 == 0) {
			for(int i=0; i<2; i++) {
				int step = 1;
				
				boolean isRed = true;
				if(isRedDown && dir == 0 && i ==0) isRed = true;
				else if(isRedDown && dir == 0 && i == 1) isRed = false;
				else if(isRedDown && dir == 2 && i == 0) isRed = false;
				else if(isRedDown && dir == 2 && i == 1) isRed = true;
				else if(!isRedDown && dir == 0 && i == 0) isRed = false;
				else if(!isRedDown && dir == 0 && i == 1) isRed = true;
				else if(!isRedDown && dir == 2 && i == 0) isRed = true;
				else if(!isRedDown && dir == 2 && i == 1) isRed = false;
				
				int color [];
				color = isRed ? red : blue;
				
				while(true) {
					int nr = dir == 0 ? color[0] + step++ : color[0] - step++;
					
					if(nr >= N || nr < 0 || copy[nr][color[1]] == '#' || copy[nr][color[1]] == 'R' || copy[nr][color[1]] == 'B') break;
					
					if(isRed) {
						nextPos[0] = nr; 
						if(nextPos[0] == goal[0] && nextPos[1] == goal[1]) break;
					}
					else {
						nextPos[2] = nr;
						if(nextPos[2] == goal[0] && nextPos[3] == goal[1]) break;
					}
				}
				
				if(isRed) {
					copy[nextPos[0]][nextPos[1]] = 'R';
					if(nextPos[0] == goal[0] && nextPos[1] == goal[1]) copy[nextPos[0]][nextPos[1]] = ' ';
				}else {
					copy[nextPos[2]][nextPos[3]] = 'B';
					if(nextPos[2] == goal[0] && nextPos[3] == goal[1]) copy[nextPos[2]][nextPos[3]] = ' ';
				}
			}
		}else if(dir % 2 == 1) {
			for(int i=0; i<2; i++) {
				int step = 1;
				
				boolean isRed = true;
				if(isRedRight && dir == 1 && i ==0) isRed = true;
				else if(isRedRight && dir == 1 && i == 1) isRed = false;
				else if(isRedRight && dir == 3 && i == 0) isRed = false;
				else if(isRedRight && dir == 3 && i == 1) isRed = true;
				else if(!isRedRight && dir == 1 && i == 0) isRed = false;
				else if(!isRedRight && dir == 1 && i == 1) isRed = true;
				else if(!isRedRight && dir == 3 && i == 0) isRed = true;
				else if(!isRedRight && dir == 3 && i == 1) isRed = false;
				
				int color [];
				
				color = isRed ? red : blue;
				
				while(true) {
					int nc = dir == 1 ? color[1] + step++ : color[1] - step++;
					
					if(nc >= M || nc < 0 || copy[color[0]][nc] == '#' || copy[color[0]][nc] == 'R' || copy[color[0]][nc] == 'B') break;
					
					if(isRed) {
						if(nextPos[0] == goal[0] && nextPos[1] == goal[1]) break;
						nextPos[1] = nc; 
					}
					else {
						if(nextPos[2] == goal[0] && nextPos[3] == goal[1]) break;
						nextPos[3] = nc;
					}
				}
				
				if(isRed) {
					copy[nextPos[0]][nextPos[1]] = 'R';
					if(nextPos[0] == goal[0] && nextPos[1] == goal[1]) copy[nextPos[0]][nextPos[1]] = ' ';
				}else {
					copy[nextPos[2]][nextPos[3]] = 'B';
					if(nextPos[2] == goal[0] && nextPos[3] == goal[1]) copy[nextPos[2]][nextPos[3]] = ' ';
				}
			}
		}
	
		return nextPos;
	}
}
