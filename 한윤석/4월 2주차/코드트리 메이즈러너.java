public class C_메이즈러너 {

	static int N,M,K,ans = 0;
	static int map [][]; 
	static boolean visit[][]; //사람들 최단거리로 이동시키기 위함
	static int d[][] = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static PriorityQueue<Man> mans = new PriorityQueue<>();
	
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
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			mans.add(new Man(r, c));
		}
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		map[r][c] = 99; //출구
		
		while(K-- > 0) {
			System.out.println("========"+K+"============");
			System.out.println(ans);
			System.out.println("before");
			print();
			move();
			if(mans.size()==0) break;
			System.out.println("after move");
			print();
			rotate();
			System.out.println("After rotate");
			print();
		}
		
		printAnswer();
	}
	
	static void move() {
		PriorityQueue<Man> temp = new PriorityQueue<>();
		
		System.out.println("mans size " + mans.size());
		while(!mans.isEmpty()) {
			Man man = mans.poll();
			Man nextMan = null;
			int exitPos [] = getExitPos();
			int dist = Math.abs(exitPos[0] - man.r) + Math.abs(exitPos[1] - man.c);
			
			for(int i=0; i<4; i++) {
				int nr = man.r + d[i][0];
				int nc = man.c + d[i][1];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N || (map[nr][nc] > 0 && map[nr][nc] != 99)) continue;
				
				int nextDist = Math.abs(exitPos[0] - nr) + Math.abs(exitPos[1] - nc);
				
				if(dist <= nextDist) continue;
				
				nextMan = new Man(nr, nc);
				ans++;
				break;
			}
			
			if(nextMan == null) temp.add(man); //이동할 곳이 없어서 그대로 있는 것
			else if(map[nextMan.r][nextMan.c] == 0)temp.add(nextMan); //다음칸이 빈 칸이면 움직이고 넣음
		}
		
		mans = new PriorityQueue<>(temp);
	}
	
	/* pr 새로 만들어서 하나씩 뽑아보면서 최단거리에 있는 사용자 뽑음
	 * 거리만큼의 크기로 만들 수 있는 정사각형 만들고 회전
	 */
	static void rotate() {
		PriorityQueue<Man> temp = new PriorityQueue<>();
		int exitPos [] = getExitPos();
		Man nearest = null;
		int nearestDist = Integer.MAX_VALUE;
		
		for(Man m : mans) {
			int dist = Math.abs(m.r - exitPos[0]) + Math.abs(m.c - exitPos[1]);
			if(nearestDist > dist) {
				nearestDist = dist;
				nearest = m;
			}
		}

		int squarePos [] = getSquarePos(nearest, exitPos); //[좌상행, 좌상열, 우하행, 우하열]
		
		int len = squarePos[2] - squarePos[0] + 1;
		int tempArr[][] = new int [len][len];
		
		//배열 회전하면서 내구도 - 1
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				tempArr[i][j] = map[len -1 - j+squarePos[0]][i+squarePos[1]];
			}
		}
		
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				int val = tempArr[i][j];
				if(val > 0 && val != 99) map[i+squarePos[0]][j+squarePos[1]] = --val;
				else map[i+squarePos[0]][j+squarePos[1]] = val;
			}
		}
		
		//사람 회전
		for(Man m : mans) {
			if(m.r >= squarePos[0] && m.r <= squarePos[2] && m.c >= squarePos[1] && m.c <= squarePos[3]) {
				int or = m.r - squarePos[0]; //0
				int oc = m.c - squarePos[1]; //0
				int nr = oc + squarePos[0]; // 1
				int nc = len - 1 - or + squarePos[1]; // 0
				m.r = nr;
				m.c = nc;
			}
			temp.add(m);
		}
		
		mans = new PriorityQueue<>(temp);
	}
	
	static int [] getSquarePos(Man nearest, int [] exitPos) {
		int [] squarePos = new int [4];
		int size = Math.max(Math.abs(nearest.r - exitPos[0]), Math.abs(nearest.c - exitPos[1]));
		
		
		outer:for(int i=0; i<N-size; i++) {
			for(int j=0; j<N-size; j++) {
				boolean hasManAndExit [] = new boolean [2];
				
				for(int k=i; k<=i+size; k++) {
					for(int l=j; l<=j+size; l++) {
						if(k == nearest.r && l == nearest.c) hasManAndExit[0] = true;
						if(k == exitPos[0] && l == exitPos[1]) hasManAndExit[1] = true;
					}//end l
				} //end k
				
				if(hasManAndExit[0] && hasManAndExit[1]) {
					squarePos = new int [] {i, j, i+size, j+size};
					break outer;
				}
				
			} //end j
		}//end i
		
		return squarePos;
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
	
	static int [] getExitPos() {
		int exitPos [] = new int [2];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 99) {
					exitPos[0] = i;
					exitPos[1] = j;
				}
			}
		}
		return exitPos;
	}
	
	
	static void print() {
		int temp [][] = new int [N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) temp[i][j] = map[i][j];
		}
		for(Man m : mans) temp[m.r][m.c] = 55;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(temp[i][j] + " ");
			}System.out.println();
		}System.out.println();
		int [] exitPos = getExitPos();
		map[exitPos[0]][exitPos[1]] = 99;
	}
	
	static class Man implements Comparable<Man>{
		int r, c;
		public Man(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(Man o) {
			if(this.r == o.r) return this.c - o.c;
			else return this.r - o.r;
		}
	}
}
