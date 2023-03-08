public class Main {

	static int N;
	static int map[][];
	static int temp[][];
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int [N+1][N+1];
		temp = new int [N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) { // pos r
			for(int j=1; j<=N; j++) { //pos c
				
				for(int k=1; k<N; k++) { //d1
					for(int l=1; l<N; l++) { //d2
						if(!isValidPos(i, j, k, l)) break;
						
						drawSection(i, j, k, l);
						findAns();
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void drawSection(int r, int c, int d1, int d2) {
		temp = new int [N+1][N+1];
		
		//5구역 경계션 그리기
		for(int i=r; i <= r+d1; i++) temp[i][c-(i-r)] = 5;
		for(int i=r+d1; i <= r+d1+d2; i++) temp[i][(c-d1)+(i - r - d1)] = 5;
		for(int i=r+d1+d2; i>=r+d2; i--) temp[i][c+(d2-d1)+(r+d1+d2 - i)] = 5;
		for(int i=r+d2; i>=r; i--) temp[i][(c+d2) - (r+d2-i)] = 5;
		
		//5 구역 내부 채우기
		for(int i=1; i<=N; i++) {
			int start = -1;
			int end = -1;
			
			for(int j=1; j <= N; j++) {
				if(temp[i][j] == 5 && start == -1) start = j;
				else if(temp[i][j] == 5 && start != -1) end = j;
			}
			
			if(end != -1) {
				for(int j=start; j<=end; j++) {
					temp[i][j] = 5;
				}
			}
		}
		
		//1구역 그리기
		for(int i=1; i<r+d1; i++) {
			for(int j=1; j<=c; j++) {
				if(temp[i][j] != 0 ) break;
				temp[i][j] = 1;
			}
		}
		
		//2구역 그리기
		for(int i=1; i<=r+d2; i++) {
			for(int j=N; j>=c+1; j--) {
				if(temp[i][j] != 0 ) break;
				temp[i][j] = 2;
			}
		}
		
		//3구역 그리기
		for(int i=r+d1; i<=N; i++) {
			for(int j=1; j<c+(d2-d1); j++) {
				if(temp[i][j] != 0 ) break;
				temp[i][j] = 3;
			}
		}
		
		//4구역 그리기
		for(int i=r+d2+1; i<=N; i++) {
			for(int j=N; j>=c+(d2-d1); j--) {
				if(temp[i][j] != 0 ) break;
				temp[i][j] = 4;
			}
		}
	}
	
	static void findAns() {
		int area1 = 0;
		int area2 = 0;
		int area3 = 0;
		int area4 = 0;
		int area5 = 0;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(temp[i][j] == 1) area1 += map[i][j];
				else if(temp[i][j] == 2) area2 += map[i][j];
				else if(temp[i][j] == 3) area3 += map[i][j];
				else if(temp[i][j] == 4) area4 += map[i][j];
				else if(temp[i][j] == 5) area5 += map[i][j];
			}
		}
		
		int max = Math.max(area1, Math.max(area2, Math.max(area3, Math.max(area4, area5))));
		int min = Math.min(area1, Math.min(area2, Math.min(area3, Math.min(area4, area5))));
		
		ans = Math.min(ans, max-min);
	}
	
	static boolean isValidPos(int r, int c, int d1, int d2) {
		boolean flag = true;
		if(!(r < r + d1 + d2 && r + d1 + d2 <= N)) flag = false;
		if(!(1 <= c - d1 && c - d1 < c && c < c + d2 && c + d2 <= N)) flag = false;
		return flag;
	}
}
