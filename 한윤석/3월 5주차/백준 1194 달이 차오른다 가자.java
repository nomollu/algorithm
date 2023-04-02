public class B1194_달이차오른다가자 {

	static int R,C;
	static char map[][];
	static boolean visit [][][]; //[r][c][key] 방문 췤
	static int d [][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int ans = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean [R][C][1<<6];
		int sr=0, sc=0;
		
		//input
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == '0') {
					sr = i;
					sc = j;
				}
			}
		}

		bfs(sr, sc);
		
		System.out.println(ans);
	}
	
	static void bfs(int sr, int sc) {
		visit[sr][sc][0] = true;
		Queue<Me> q = new LinkedList<>();
		q.add(new Me(sr,sc,0,0));
		
		while(!q.isEmpty()) {
			Me me = q.poll();
			
			//탈출했으면
			if(map[me.r][me.c] == '1') {
				ans = me.t;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nr = me.r + d[i][0];
				int nc = me.c + d[i][1];
				int tempKey = me.key;
				
				//이미 방문했거나 벽이면 스킵
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc][tempKey] || map[nr][nc] == '#') continue;
				
				//문일 때
				if(Character.isUpperCase(map[nr][nc]) && (tempKey & (1<<map[nr][nc] - 'A')) == 0 ) continue;
				//키일 때
				else if(Character.isLowerCase(map[nr][nc])) {
					int moveIdx = map[nr][nc] - 'a';
					tempKey |= (1<<moveIdx); //내가 가진 키에 포함시켜 줌
				}
				
				visit[nr][nc][tempKey] = true;
				q.add(new Me(nr, nc, me.t + 1, tempKey));
			}
		}
	}
	
	static class Me {
		int r, c, t, key; //좌표, 시간, 키
		public Me(int r, int c, int t, int key) {
			this.r = r;
			this.c = c;
			this.t = t;
			this.key = key;
		}
	}
}
