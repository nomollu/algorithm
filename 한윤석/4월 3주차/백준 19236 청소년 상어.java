public class B19236_청소년상어 {
	
	static int d [][] = {{0,0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Fish[] fishes = new Fish [17];
		int map [][] = new int [4][4];
		int startN = 0;
		int startD = 0;
		
		for(int i=0; i<4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(i == 0 && j == 0) {
					startN = a;
					startD = b;
					map[i][j] = 0;
					continue;
				}
				map[i][j] = a;
				fishes[a] = new Fish(i, j, b);
			}
		}
		
		dfs(map, fishes, new Fish(0, 0, startD), startN);
		
		System.out.println(ans);
	}
	
	static void dfs(int [][] map, Fish [] fishes, Fish shark, int sum) {
		int copy [][] = new int [4][4];
		Fish copyFishes [] = new Fish[17];
		
		for(int i=1; i<=16; i++) {
			if(fishes[i] != null)
			copyFishes[i] = new Fish(fishes[i].r, fishes[i].c, fishes[i].d);
		}
		
		copyArr(map, copy);
		moveFishes(copyFishes, copy, shark);

		int step = 1;
		//상어가 갈 수 있는 방향으로 다 가봄
		while(true) {
			int nr = shark.r + d[shark.d][0] * step;
			int nc = shark.c + d[shark.d][1] * step;
			
			if(nr < 0 || nc < 0 || nr >= 4 || nc >= 4) break;
			
			if(copy[nr][nc] != 0) {
				int bait = copy[nr][nc];
				Fish baitFish = copyFishes[bait];
				
				copy[shark.r][shark.c] = 0;
				copy[nr][nc] = 0;
				copyFishes[bait] = null;

				dfs(copy, copyFishes, new Fish(nr, nc, baitFish.d), sum + bait);
				
				copy[nr][nc] = bait;
				copyFishes[bait] = baitFish;
			}
			
			step++;
		}
		ans = Math.max(ans, sum);
	}
	
	static void moveFishes(Fish [] fishes, int[][] copy, Fish shark) {
		for(int i=1; i<=16; i++) {
			if(fishes[i] == null) continue;
			
			Fish cur = fishes[i]; //i번 물고기
			
			for(int j=0; j<8; j++) {
				int nd = cur.d + j <= 8 ? cur.d + j : (cur.d + j) % 8;
				int nr = cur.r + d[nd][0];
				int nc = cur.c + d[nd][1];
				
				if(nr < 0 || nc < 0 || nr >= 4 || nc >= 4 ||(shark.r == nr && shark.c == nc)) continue;
				
				if(copy[nr][nc] == 0) { //물고기 빈칸으로 이동
					copy[nr][nc] = i;
					copy[cur.r][cur.c] = 0;
					fishes[i] = new Fish(nr, nc, nd);
				}else { // 두 물고기 위치 바꾸기
					int changed = copy[nr][nc];
					copy[nr][nc] = i;
					copy[cur.r][cur.c] = changed;
					fishes[i] = new Fish(nr, nc, nd);
					fishes[changed].r = cur.r;
					fishes[changed].c = cur.c;
				}
				break;
			}
		}
	}
	
	static void copyArr(int origin[][], int copy[][]) {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				copy[i][j] = origin[i][j];
			}
		}
	}
	
	static class Fish{
		int r, c, d;
		public Fish(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		@Override
		public String toString() {
			return "["+r+", "+c+"]"+ " " + d;
		}
	}
}
