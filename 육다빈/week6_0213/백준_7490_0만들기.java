package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G7490_makeZero {
	static StringBuilder sb = new StringBuilder();
	static int N;
	
	// n:연산할 수, sum:누적된 연산결과, pre:직전에 연산한 수, str:누적연산결과
	static void makeZero(int n, int sum, int pre, String str) { 
		if(n>N) {
			if(sum==0) sb.append(str+"\n");
			return;
		}
		
		int tmp = (Math.abs(pre)*10 + n) * (pre>0 ? 1 : -1); // 앞에 수와 합쳐 만들어진 수
		makeZero(n+1, sum-pre+tmp, tmp, str+" "+n); // 현재 수 붙이기
		
		makeZero(n+1, sum+n, n, str+"+"+n); // 현재 수 더하기
		makeZero(n+1, sum-n, -n, str+"-"+n); // 현재 수 빼기
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			if(t>0) sb.append("\n");
			makeZero(2, 1, 1, "1");
		}
		
		System.out.println(sb);
	}

}
