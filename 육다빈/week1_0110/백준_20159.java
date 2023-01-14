package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G20159_stopit {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] card = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int remains=0;
		for(int i=0; i<N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
			if(i%2!=0) remains += card[i];
		}
		
		int max=remains;
		for(int j=0; j<2; j++) {
			int sum = 0;
			int tmp = remains - (j==1 ? card[N-1] : 0);
			for(int i=0; i<N; i+=2) {
				sum += card[i];
				if(j==0) tmp -= card[i+1];
				max = Math.max(max, sum+tmp);
				System.out.println(sum+tmp);
				if(j==1) tmp -= card[i+1];
			}

		}
		
		System.out.println(max);
		
	}
}
