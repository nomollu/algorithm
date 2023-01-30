package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S16194_buyingCard {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int[] cards = new int[N+1];
		for(int i=1; i<=N; i++) cards[i] = Integer.parseInt(str[i-1]);
		
		int[] dp = new int[N+1];
		for(int i=1; i<=N; i++) {
			dp[i] = cards[i];
			for(int j=1; j<i; j++) {
				dp[i] = Math.min(dp[i], dp[i-j]+dp[j]);
			}
		}
		
		System.out.println(dp[N]);
	}

}
