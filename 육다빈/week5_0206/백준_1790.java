package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1790_writeNumber2 {
	
	static int check(int N, int K) {
		int n=0, k=0;
		for(int i=1; ; i++) { // n이 i자리수인 경우 탐색
			
			int cnt = (int) (9*Math.pow(10, i-1)); // i자리수의 개수
			
			if(k + cnt*i < K) { // i자리수에서는 K번째까지 만들어지지 않으니, 하나씩 해볼필요 없음
				n += cnt;
				k += cnt*i;
				
			}else { // i자리수인 n을 이어붙일때 k번째 자리가 만들어짐
				int remains = K-k; //남은 자리수
				if(i==1) n = K;
				else n += (remains-1)/i+1; // k번째 자리를 만들기 위해 붙이는 가장 큰 n
				if(n > N) return -1; // k번째를 만들기 위해 붙여야 할 n이 주어진 N보다 크다
				else return (n+"").charAt((remains-1)%i)-'0'; 
			}
			
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(check(N, K));
	}

}
