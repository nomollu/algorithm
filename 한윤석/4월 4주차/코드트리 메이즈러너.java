public class C_메이즈러너 {

	static int N,M,K,ans = 0; //맵 크기, 참가자 수, 게임 시간
	static int map [][]; 
	static int d[][] = {{1,0}, {-1,0}, {0,1}, {0,-1}}; // 사람용 델타
	static Queue<Pos> mans = new LinkedList<>(); //남은 사람들
	static int er, ec; //탈출구 행, 열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int [N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			mans.add(new Pos(r, c));
		}
		
		st = new StringTokenizer(br.readLine());
		er = Integer.parseInt(st.nextToken())-1;
		ec = Integer.parseInt(st.nextToken())-1;
		map[er][ec] = 99; //맵에서 탈출구는 99로 표시
		
		while(K-- > 0) {
			move();
			if(mans.size()==0) break;
			rotate();
		}
		
		printAnswer();
	}
	
	// 모든 참가자 이동시키기
	static void move() {
		Queue<Pos> temp = new LinkedList<>();
		
		while(!mans.isEmpty()) {
			Pos p = mans.poll();
			Pos nextPos = null;
			int curDist = Math.abs(er - p.r) + Math.abs(ec - p.c);
			
			for(int i=0; i<4; i++) {
				int nr = p.r + d[i][0];
				int nc = p.c + d[i][1];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N || (map[nr][nc] > 0 && map[nr][nc] != 99)) continue;
				
				int nextDist = Math.abs(er - nr) + Math.abs(ec - nc);
				
				//새로 이동한 칸이 원래 칸에서의 거리보다 작아야 이동함
				if(curDist <= nextDist) continue;
				
				ans++;
				nextPos = new Pos(nr, nc);
				break;
			}
			
			if(nextPos == null) temp.add(p); //이동할 곳이 없어서 그대로 있는 것
			else if(!(nextPos.r == er && nextPos.c == ec)) temp.add(nextPos); //출구가 아니면 넣음
		}
		
		mans = new LinkedList<>(temp);
	}
	
	// 최소 정사각형 회전
	static void rotate() {
		Queue<Pos> temp = new LinkedList<>();
		int squarePos [] = getSquarePos(); //[좌상행, 좌상열, 우하행, 우하열]
		int len = squarePos[2] - squarePos[0] + 1; // 정사각형 한 변의 길이
		int tempArr[][] = new int [len][len]; //회전을 위한 임시 배열
		
		// 임시 배열에 회전할 정사각형 정보 넣음
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				tempArr[i][j] = map[len -1 - j+squarePos[0]][i+squarePos[1]];
			}
		}

		//배열 회전하면서 내구도 - 1
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				int val = tempArr[i][j];
				if(val > 0 && val != 99) map[i+squarePos[0]][j+squarePos[1]] = --val;
				else map[i+squarePos[0]][j+squarePos[1]] = val;
			}
		}
		
		//사람 회전
		for(Pos m : mans) {
			if(m.r >= squarePos[0] && m.r <= squarePos[2] && m.c >= squarePos[1] && m.c <= squarePos[3]) {
				int or = m.r - squarePos[0]; //temp배열 내에서의 사람의 행
				int oc = m.c - squarePos[1]; 
				int nr = oc + squarePos[0]; // 실제 맵에서의 사람의 행
				int nc = len - 1 - or + squarePos[1]; 
				m.r = nr;
				m.c = nc;
			}
			temp.add(m);
		}
		
		//출구 위치 갱신
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) if(map[i][j] == 99) {
				er = i;
				ec = j;
			}
		}
		
		mans = new LinkedList<>(temp);
	}
	
	static int [] getSquarePos() {
		int [] squarePos = new int [4];
		
		outer:for(int l=1; l<N; l++) { // 정사각형 한 변의 크기
			int minR = Math.max(0, er - l); //탈출구가 포함되고 길이가 l인 사각형의 최소 행
			int minC = Math.max(0, ec - l); //탈출구가 포함되고 길이가 l인 사각형의 최소 열
			
			for(int r=minR; r<=er; r++) {
				for(int c=minC; c<=ec; c++) {
					if(!isManIncluded(r, c, l)) continue;
					squarePos[0] = r;
					squarePos[1] = c;
					squarePos[2] = r + l;
					squarePos[3] = c + l;
					break outer;
				}
			}
		}
		
		return squarePos;
	}
	
	static boolean isManIncluded(int r, int c, int l) {
		Queue<Pos> tempQ = new LinkedList<>();
		int temp [][] = new int [N][N];
		
		// 사람들의 위치 정보를 temp배열에 갱신
		while(!mans.isEmpty()) {
			Pos p = mans.poll();
			temp[p.r][p.c] = 1;
			tempQ.add(p);
		}
		
		mans = new LinkedList<>(tempQ);
		
		// 정사각형 범위 안에 사람 있으면 true 리턴
		for(int i=r; i<=r+l; i++) {
			for(int j=c; j<=c+l; j++) {
				if(i >= N || j >= N) continue;
				if(temp[i][j] == 1) return true;
			}
		}
		
		return false;
	}
	
	static void printAnswer() {
		System.out.println(ans);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 99) {
					System.out.println((i+1) + " " + (j+1));
					return;
				}
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
}
