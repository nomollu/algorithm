public class B2234_성곽 {
	
	static int R,C;
	static int map[][];
	static boolean check[][]; //탐색을 시작할 때 사용할 방문 배열
	static boolean visit [][][]; //벽 뚫은 여부 따른 방문 체크할 배열
	static int d[][] = {{0,-1},{-1,0},{0,1},{1,0}};
	static int ans [] = new int [3]; //[0] 방의 개수, [1] 가장 넓은 방의 넓이, [2] 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int [R][C];
		check = new boolean [R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(!check[i][j]) { //방 개수 파악 위한 체크 안되어있는 방이면
					ans[0]++;
					bfs(i,j);
				}
			}
		}
		
		System.out.println(ans[0] + "\n"+ans[1]+"\n"+ans[2]);
	}
	
	static void bfs(int sr, int sc) {
		Queue<Pair> q = new LinkedList<>(); // 벽 제거 없는 너비 구하기 용 큐
		Queue<Pair> roomPos = new LinkedList<>(); // [sr, sc]에서 벽 제거없이 갈 수 있는 방 전부 담길 예정
		check[sr][sc] = true;
		visit = new boolean [R][C][2];
		visit[sr][sc][0] = true;
		q.add(new Pair(sr, sc, 1, 0));
		roomPos.add(new Pair(sr, sc, 0, 0));
		
		//벽 제거 없이 방문할 수 있는 곳 다 가봄
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = p.r + d[i][0];
				int nc = p.c + d[i][1];
				int wallDir = (i+2)%4; //i방향으로 이동하려는데 그쪽 방향으로 벽 있는지
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc][0] || (map[nr][nc] & (1<<wallDir)) != 0) continue;

				visit[nr][nc][0] = true;
				check[nr][nc] = true;
				roomPos.add(new Pair(nr, nc, 0, 0));
				q.add(new Pair(nr, nc, p.t + 1, 0));
			}
		}
		
		int roomSize = roomPos.size();
		ans[1] = Math.max(ans[1], roomSize); //가장 넓은 방 갱신
		
		int wideRoomSize = 0; //벽 한번 뚫었을 때 그 방의 넓이
		for(Pair p : roomPos) { //벽 안뚫고 갈 수 있는 방들에서 전부 탐색
			for(int i=0; i<4; i++) {
				int nr = p.r + d[i][0];
				int nc = p.c + d[i][1];
				int wallDir = (i+2)%4; //i방향으로 이동하려는데 그쪽 방향으로 벽 있는지
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc][0] || visit[nr][nc][1]) continue;

				//다음에 벽일 때
				if((map[nr][nc] & (1<<wallDir)) != 0) {
					int newSize = getCurArea(nr, nc);
					
					wideRoomSize = Math.max(wideRoomSize, newSize);
				}
			}
		}
		ans[2] = Math.max(ans[2], roomSize + wideRoomSize); //벽 안뚫고 갈 수 있는 방 수 + 벽 뚫었을 때 그 방의 수
		
//		System.out.println(sr + " " + sc + " " + roomSize + " " + wideRoomSize);
	}
	
	static int getCurArea(int r, int c) {
		int size = 1;
		Queue<int []> q = new LinkedList<>();
		q.add(new int [] {r, c, 0});
		visit[r][c][1] = true;
		
		while(!q.isEmpty()) {
			int [] p = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = p[0] + d[i][0];
				int nc = p[1] + d[i][1];
				int wallDir = (i+2)%4;
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc][0] || visit[nr][nc][1] || (map[nr][nc] & (1<<wallDir)) != 0) continue;
				
				visit[nr][nc][1] = true;
				size++;
				q.add(new int [] {nr, nc, p[2] + 1});
			}
		}
		
		return size;
	}
	
	static class Pair{
		int r, c, t, flag;
		public Pair(int r, int c, int t, int flag) {
			this.r = r;
			this.c = c;
			this.t = t;
			this.flag = flag;
		}
	}
}

//방향이 1로 갈 때 다음 칸이 8이 포함되어 있는지를 봐야함
