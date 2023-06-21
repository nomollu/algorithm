public class P퍼즐조각채우기 {

	static int answer=0, psize, esize; // 정답, pieces사이즈, empties사이
	static List<Piece> pieces = new LinkedList<>(); // table에서 유효한 조각들 정보 
	static List<Piece> empties = new LinkedList<>(); // game_board에서 유효한 빈칸 정보 
	static boolean pieceCheck [], emptyCheck[]; // 사용 여부 
	static int d[][] = {{1,0},{0,1},{-1,0},{0,-1}}; 
	
	public static void main(String[] args) {
		int board [][] = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
		int table [][] = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};
		System.out.println(solution(board, table));
	}

	static public int solution(int[][] game_board, int[][] table) {
		setBlocks(table, 1); // pieces 정보 채우기 
		setBlocks(game_board, 0); // empties 정보 채우기 
		psize = pieces.size();
		esize = empties.size();
		pieceCheck = new boolean [psize];
		emptyCheck = new boolean [esize];
		
		dfs(0);
        return answer;
    }
	
	static void dfs(int sum) {
		answer = Math.max(answer, sum);
		
		for(int i=0; i<esize; i++) {
			if(emptyCheck[i]) continue;
			Piece empty = empties.get(i);
			
			for(int j=0; j<psize; j++) {
				if(pieceCheck[j]) continue;
				
				Piece piece = pieces.get(j);
				Piece copy = new Piece(piece.C, piece.R, null); // struct 회전을 위해 복사본 만들어두
				copy.struct = getTurnedStruct(piece);
				
				// 회전했을 때나, 현재 그 사각형 크기가 같은 경우에만 살피기 
				if((empty.R == piece.R && empty.C == piece.C) || (empty.C == piece.R && empty.R == piece.C)) {
					
					// 90도로 회전 4번 해봄 
					stopTurn:for(int t=0; t<4; t++) {
						int [][] curStruct = getTurnedStruct(copy);
						copy.struct = curStruct;
						copy.R = curStruct.length;
						copy.C = curStruct[0].length;
						boolean allMatch = true; // empty.struct와 curstruct가 모두 같은지 여
						int areaSize = 0;
						
						// 회전했을 때 행열 크기 같은 경우만 검사해야함 
						if(empty.R == curStruct.length && empty.C == curStruct[0].length) {
							// 2중포문 돌아보면서 같은 형태 띄고 있는지 검사 
							outer:for(int cr = 0; cr<empty.R; cr++) {
								for(int cc = 0; cc<empty.C; cc++) {
									if(empty.struct[cr][cc] != curStruct[cr][cc]) {
										allMatch = false;
										break outer;
									}else if(empty.struct[cr][cc] == curStruct[cr][cc] && curStruct[cr][cc] == 1)areaSize++;
								}
							}
							
							//빈칸에 딱 들어맞는 조각이라면 
							if(allMatch) {
								emptyCheck[i] = true;
								pieceCheck[j] = true;
								
								// 이미 갓던 길 점수가 더 작으면 굳이 안가도 됨 
								if(sum + areaSize > answer) {
									dfs(sum + areaSize);
								}
								pieceCheck[j] = false;
								emptyCheck[i] = false;
								break stopTurn;
							}
						}
					}
				}
			}
		}
	}
	
	// table과 game_board에서 유효한 정보들만 리스트에 담기 
	static void setBlocks(int [][] table, int checkNum) {
		int R = table.length;
		int C = table[0].length;
		int passNum = checkNum == 1 ? 0 : 1;
		boolean visit [][] = new boolean [R][C];
		
		// table의 모든 좌표 돌면서 만들 수 있는 도형을 pieces에 저장 
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(table[i][j] == checkNum && !visit[i][j]) {
					int r[] = new int [] {i, i}; //[0] 최소 행, [1] 최대 행 
					int c[] = new int [] {j, j}; //[0] 최소 열, [1] 최대 열 
					List<int []> list = new LinkedList<>(); // 도형 피스들의 좌표 저장할 리스트 
					Queue<int []> q= new LinkedList<>();
					q.add(new int [] {i,j});
					list.add(new int [] {i, j});
					visit[i][j] = true;
					
					while(!q.isEmpty()) {
						int [] p = q.poll();
						for(int k=0; k<4; k++) {
							int nr = p[0] + d[k][0];
							int nc = p[1] + d[k][1];
							
							if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc] || table[nr][nc] == passNum) continue;
							
							visit[nr][nc] = true;
							q.add(new int [] {nr, nc});
							list.add(new int [] {nr, nc});
							
							// 최대, 최소 행 열 갱신 
							r[0] = Math.min(r[0], nr);
							r[1] = Math.max(r[1], nr);
							c[0] = Math.min(c[0], nc);
							c[1] = Math.max(c[1], nc);
						}
					}
					
					// 도형의 형태를 2차원 배열 형태로 만들고 이를 pieces 에 저장 
					int [][] struct = getStruct(r, c, list);
					if(checkNum == 1) pieces.add(new Piece(r[1] - r[0] + 1, c[1] - c[0] + 1, struct));
					else empties.add(new Piece(r[1] - r[0] + 1, c[1] - c[0] + 1, struct));
				}
			}
		}
	}
	
	// 행, 열 크기 주어지고 좌표 위치 주어지면 이를 2차원 배열 형태의 도형으로 만들어 리턴 
	static int [][] getStruct(int r[], int c[], List<int []> list) {
//		System.out.println(Arrays.toString(r) + " " + Arrays.toString(c));
		int sr = r[1] - r[0] + 1;
		int sc = c[1] - c[0] + 1;
		int struct [][] = new int [sr][sc];
		
		for(int [] p: list) {
			int pr = p[0] - r[0];
			int pc = p[1] - c[0];
			struct[pr][pc] = 1;
		}
		return struct;
	}
	
	// 주어진 piece 정보를 가지고 오른쪽으로 90도 회전한 struct를 리턴 
	static int [][] getTurnedStruct(Piece p){
		int struct [][] = new int [p.C][p.R];
		for(int i=0; i<p.R; i++) {
			for(int j=0; j<p.C; j++) {
				struct[j][p.R-i-1] = p.struct[i][j];
			}
		}
		return struct;
	}
	
	static class Piece{
		int R,C;
		int struct [][];
		public Piece(int r, int c, int[][] struct) {
			R = r;
			C = c;
			this.struct = struct;
		}
	}
}
