public class B1749_점수따먹기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long dp[][] = new long [N][M]; // [0][0] 부터 [i][j]까지의 누적합
		long ans = Long.MIN_VALUE; // 음수일 수도 있어서 long 의 최소값으로 초기화
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(i == 0 && j == 0) dp[i][j] = num;
				else if(i == 0) dp[i][j] = dp[i][j-1] + num;
				else if(j == 0) dp[i][j] = dp[i-1][j] + num;
				else dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + num;
				ans = Math.max(ans, num); // 아무것도 안 더하는게 제일 클 수도 있어서 갱신해놓기
			}
		}
		
		for(int i=0; i<N; i++) { // 탐색 시작 행
			for(int j=0; j<M; j++) { //탐색 시작 열
				
				// i ~ N, j ~ M 까지의 부분 행렬 최대값 구하기
				for(int ii=i; ii<N; ii++) {
					for(int jj=j; jj<M; jj++) {
						long num = dp[ii][jj];
						
						if(i > 0) num -= dp[i-1][jj];
						if(j > 0) num -= dp[ii][j-1];
						if(i > 0 && j > 0) num += dp[i-1][j-1];
						
						ans = Math.max(ans, num);
					}
				}
			}
		}
		
		System.out.println(ans);
	}

}
