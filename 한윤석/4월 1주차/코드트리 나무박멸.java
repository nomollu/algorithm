public class C_나무박멸 {

	static int n, m, k, c; //격자 크기, 박멸 진행 년 수, 제초제 확산 범위, 제초제 남아있는 년 수
	static int map [][];
	static long ans = 0;
	static int d[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int diagonal [][] = {{1,1},{1,-1},{-1,-1},{-1,1}};
	static int herbicide [][]; //제초제 남은 년
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int [n][n];
		herbicide = new int [n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		while(m-- > 0) {
			growTree(); //나무 성장 
			breedTree(); //나무 번식 
			spreadHerbicide(); //제초제 뿌리기 
			renewHerbicide(); //제초제 남은 년수 갱
		}
		
		System.out.println(ans);
	}
	
	static void growTree() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] <= 0) continue; 
				
				int nearTree = 0;
				
				for(int k=0; k<4; k++) {
					int nr = i + d[k][0];
					int nc = j + d[k][1];
					
					if(!isBoundary(nr, nc) || map[nr][nc] <= 0) continue;
					
					nearTree++;
				}
				map[i][j] += nearTree;
			}
		}
	}
	
	static void breedTree() {
		int copy [][] = new int [n][n];
		copyMap(copy, map); //copy배열에 map정보를 복사
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] <= 0) continue;
				
				int canBreedCnt = 0; //번식 가능한 칸 
				
				for(int k=0; k<4; k++) {
					int nr = i + d[k][0];
					int nc = j + d[k][1];
					
					//범위 벗어나거나, 나무이거나, 제초제 뿌려져 있으면 안
					if(!isBoundary(nr, nc) || map[nr][nc] != 0 || herbicide[nr][nc] > 0) continue;
					
					canBreedCnt++;
				}
				
				for(int k=0; k<4; k++) {
					int nr = i + d[k][0];
					int nc = j + d[k][1];
					
					if(!isBoundary(nr, nc) || map[nr][nc] != 0 || herbicide[nr][nc] > 0) continue;
					
					//번식시키
					copy[nr][nc] += map[i][j] / canBreedCnt;
				}
			}
		}
		
		copyMap(map, copy); //갱신된 copy배열을 map에 복사함
	}
	
	static void spreadHerbicide() {
		int herbicideStartInfo [] = {0, 0, 0}; //r, c, [r,c]에 뿌렸을 때 박멸되는 나무의 최대 값
		
		// 제초제를 뿌릴 위치 파악 
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] <= 0) continue;
				
				int removeTreeCnt = map[i][j]; //[i,j]에서 제초제 뿌렸을 때 제거되는 나무 
				
				for(int dir=0; dir<4; dir++) {
					for(int step = 1; step <= k; step++) {
						int nr = i + diagonal[dir][0] * step;
						int nc = j + diagonal[dir][1] * step;
						
						//범위 벗어낫거나, 벽이거나, 빈 칸인경우 제초제 못 뿌려 
						if(!isBoundary(nr, nc) || map[nr][nc] <= 0) break;
						
						removeTreeCnt += map[nr][nc];
					}
				}
				
				if(herbicideStartInfo[2] < removeTreeCnt) herbicideStartInfo = new int [] {i, j, removeTreeCnt};
			}
		}
		
		//해당 위치에 제초제 뿌리기
		map[herbicideStartInfo[0]][herbicideStartInfo[1]] = 0;
		herbicide[herbicideStartInfo[0]][herbicideStartInfo[1]] = c+1;
		for(int i=0; i<4; i++) {
			for(int step = 1; step<=k; step++) {
				int nr = herbicideStartInfo[0] + diagonal[i][0] * step;
				int nc = herbicideStartInfo[1] + diagonal[i][1] * step;
				
				if(!isBoundary(nr, nc)) break;
				
				int originTree = map[nr][nc]; //다음 칸의 나무 or 벽
				
				// 벽이 아닌 경우에만 나무를 다 제거하
				if(originTree != -1) map[nr][nc] = 0;
				herbicide[nr][nc] = c+1;
				
				if(originTree <= 0) break; //다음 칸이 빈 나무였던가 벽이었으면 제초제까지는 뿌리고 종료
			}
		}
		
		ans += herbicideStartInfo[2];
	}
	
	static void renewHerbicide() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(herbicide[i][j] <= 0) continue;
				
				herbicide[i][j]--;
			}
		}
	}
	
	static boolean isBoundary(int nr, int nc) {
		if(nr < 0 || nc < 0 || nr >= n || nc >= n) return false;
		return true;
	}
	
	static void copyMap(int [][] copy, int [][] temp) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) copy[i][j] = temp[i][j];
		}
	}
	
	static void print(int temp[][]) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) System.out.print(temp[i][j] + " ");
			System.out.println();
		}System.out.println();
	}
}
