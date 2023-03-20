package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G27210_shrineToDeity {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] stones = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int total_l=0, total_r=0;
		for(int i=0; i<N; i++) {
			stones[i] = Integer.parseInt(st.nextToken());
			if(stones[i]==1) total_l++;
			else total_r++;
		}
		
		int result = Math.abs(total_l-total_r);
		int sum = result;
		for(int i=0; i<N; i++) {
			sum -= stones[i];
			
			if(N-i <= result) break;
			int left=0, right=0;
			for(int j=i; j<N; j++) {
				if(stones[j]==1) left++;
				else right++;
				int now = Math.abs(left-right);
				result = Math.max(result, now);
				if(now + N-1-j <= result) break;
			}
		}
		System.out.println(result);
	}

}
