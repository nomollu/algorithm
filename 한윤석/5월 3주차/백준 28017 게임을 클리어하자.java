public class B28017_게임을클리어하자 {
	
	static int N,M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int t [][] = new int [N][M];
		int dp[][] = new int [N][M]; // 누적 시간
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				t[i][j] = Integer.parseInt(st.nextToken());
				if(i == 0) {
					dp[i][j] = t[i][j];
				}
			}
		}
		
		for(int i=1; i<N; i++) {
			int min [] = new int [4]; // {제일 작은 값, 제일 작은 인덱스, 2번째 작은 값, 2번째 작은 인덱스}
			Arrays.fill(min, Integer.MAX_VALUE);
			
			for(int j=0; j<M; j++) {
				
				//가장 작은 값 갱신
				if(min[0] > dp[i-1][j]) {
					min[2] = min[0];
					min[3] = min[1];
					min[0] = dp[i-1][j];
					min[1] = j;
				}
				// 둘째로 작은 값 갱신
				else if(min[2] > dp[i-1][j]) {
					min[2] = dp[i-1][j];
					min[3] = j;
				}
			}
			
			// 이전에 사용한 무기 외에 제일 작은 무기 선택해서 더해줌
			for(int j=0; j<M; j++) {
				if(min[1] == j) dp[i][j] = dp[i-1][min[3]] + t[i][j];
				else dp[i][j] = dp[i-1][min[1]] + t[i][j];
			}

		}
		
		int ans = Integer.MAX_VALUE;
		for(int i=0; i<M; i++) ans = Math.min(ans, dp[N-1][i]);
		
		System.out.println(ans);
	}
}
