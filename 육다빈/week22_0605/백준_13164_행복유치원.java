package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G13164_happinessKindergarden {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] kids = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int n=0; n<N; n++) kids[n] = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N][K+1];
		for(int n=0; n<N; n++) {
			dp[n][1] = kids[n] - kids[0];
			for(int k=2; k<=K && k<=n+1; k++) {
				dp[n][k] = dp[0][k-1] + kids[n]-kids[1];
				for(int i=1; i<n; i++) dp[n][k] = Math.min(dp[n][k], dp[i][k-1] + kids[n]-kids[i+1]);
			}
		}
		
		System.out.println(dp[N-1][K]);
	}

}
