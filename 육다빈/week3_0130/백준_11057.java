package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S11057_upNumber {
	
	static int comb(int n, int r) { // 조합 경우의 수 계산 (nCr)
		if(r>(n/2)) r = n-r;
		int result = 1;
		for(int i=0; i<r; i++) result *= n-i;
		for(int i=r; i>0; i--) result /= i;
		return result;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 1. 숫자가 정해져 있을 때 만들 수 있는 모든 경우의 수 계산
		int[][] dp = new int[N+1][11]; // dp[i][j] = j개의 숫자로 i자리수 오르막 수를 만드는 경우의 수
		dp[1][1] = 1;
		for(int i=2; i<=N; i++) { // 자리수 넓혀가기
			int cnt = 0;
			for(int j=2; j<=10 && j<=i; j++) {//0~9까지의 숫자 중 j개 선택
				for(int k=1; k<=i; k++) { 
					dp[i][j] = (dp[i][j]+dp[i-k][j-1]) % 10007;
				}
			}
		}
		
		// 2. 오르막 수를 구성하는 숫자 조합의 경우의 수 계산
		int result = 0;
		for(int j=1; j<=10; j++) {
			int cnt=0;
			for(int i=1; i<=N; i++) cnt += dp[i][j];
			result = (result + cnt * comb(10, j)) % 10007;
		}
		
		System.out.println(result);
	}

}
