public class B11057_오르막수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int dp [][] = new int [10][N+1];
		int MOD = 10007;
		int ans = 0;
		
		for(int i=1; i<10; i++) dp[i][1] = 1;
		dp[0][1] = 1;
		
		for(int i=1; i<N; i++) { //자릿수
			for(int j=0; j<10; j++) { //j로 끝나는 
				for(int k=j; k<10; k++) { //j보다 큰 뒷자리로 끝나는 수들에 + 
					dp[k][i+1] = (dp[k][i+1] + dp[j][i]) % MOD;
				}
			}
		}
		
		for(int i=0; i<10; i++) {
			ans = (ans + dp[i][N]) % MOD;
		}
		
		System.out.println(ans);
	}
}
