package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G2138_bulbAndSwitch {
	static int N;
	static char[] bulb;
	
	static void turn(int n) {
		for(int i=-1; i<=1; i++) {
			int now = n+i;
			if(now<0 || now>=N) continue;
			bulb[now] = (bulb[now]=='0') ? '1' : '0';
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String origin = br.readLine();
		char[] result = br.readLine().toCharArray();
		
		int cnt = Integer.MAX_VALUE;
		for(int i=0; i<2; i++) {
			bulb = origin.toCharArray();
			int cnt_tmp = 0;
			
			if(i==1) {
				cnt_tmp++;
				turn(0);
			}
			
			for(int j=1; j<N; j++) {
				if(bulb[j-1] == result[j-1]) continue;
				turn(j);
				cnt_tmp++;
			}
			if(bulb[N-1] == result[N-1]) cnt = Math.min(cnt, cnt_tmp);
		}
		
		System.out.println(cnt==Integer.MAX_VALUE ? -1 : cnt);
	}

}
