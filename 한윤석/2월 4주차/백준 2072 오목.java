public class B2072_오목 {

	static int map[][];
	static int d[][] = {{1,0},{0,1},{1,1},{-1,1},{-1,0},{0,-1},{-1,-1},{1,-1}}; //방향.
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int ans = -1;
		map = new int [20][20];
		boolean flag = false;
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[r][c] = i % 2 == 0 ? 1 : -1;
			
			if(isFinished(r, c, i % 2 == 0 ? 1 : -1)) {
				flag = true;
				ans = i+1;
				break;
			}
		}
		
		System.out.println(ans);
	}

	static boolean isFinished(int r, int c, int color) {
		boolean flag = false;
		
		for(int i=0; i<4; i++) {
			int step = 1;
			int cnt = 1;
			
      //i번째 방향과 i+4번째 방향(반대방향) 다 확인해보고 5개인지 확인
			while(true) {
				int nr = r + d[i][0] * step;
				int nc = c + d[i][1] * step;
				
				if(nr <= 0 || nc <= 0 || nr > 19 || nc > 19 || map[nr][nc] != color) break;
				
				cnt++;
				step++;
			}
			
			step = 1;
			while(true) {
				int nr = r + d[i+4][0] * step;
				int nc = c + d[i+4][1] * step;
				
				if(nr <= 0 || nc <= 0 || nr > 19 || nc > 19 || map[nr][nc] != color) break;
				
				cnt++;
				step++;
			}
			
			if(cnt == 5) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}
}
