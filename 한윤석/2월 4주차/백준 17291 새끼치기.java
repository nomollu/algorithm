public class B17291_새끼치기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int dp [] = new int [21];
		dp[0] = 1;
		dp[1] = 1;
		
		for (int i = 1;i < N;++i) {
	        dp[i + 1] = dp[i] * 2;
	        
	        if(i < 3) continue;
	        if ((i - 2) % 2 == 1) dp[i + 1] -= dp[i-3];
	        
	        if(i < 4) continue;
	        if ((i - 3) % 2 == 0) dp[i + 1] -= dp[i-4];
	    }
		System.out.println(dp[N]);
	}

}
