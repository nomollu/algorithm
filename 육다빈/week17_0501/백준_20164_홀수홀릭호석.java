package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G20164_oddHolicHS {
	static int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
	
	static void dfs(String number, int cnt) {
		int len = number.length();
		if(len==1) {
			min = Math.min(min, cnt);
			max = Math.max(max, cnt);
		}else if(len==2) {
			String n = (number.charAt(0)-'0') + (number.charAt(1)-'0') + "";
			dfs(n, cnt + countOdd(n));
		}else {
			comb(number, cnt, 0, 0); // 세가지로 나눌 수 있는 모든 경우에 대해 탐색
		}
	}
	
	static void comb(String number, int oddCnt, int combCnt, int sum) {
		int len = number.length();
		if(combCnt==2) { // 분리 완료
			String n = sum+Integer.parseInt(number)+"";
			dfs(n, oddCnt+countOdd(n));
			return;
		}
		if(len<2) return; // 나눠질 수 없는 조합
		for(int i=0; i<len-1; i++) {
			comb(number.substring(i+1), oddCnt, combCnt+1, sum + Integer.parseInt(number.substring(0, i+1)));
		}
	}
	
	static int countOdd(String number) {
		int len=number.length(), cnt=0;
		for(int i=0; i<len; i++) {
			cnt += (number.charAt(i)-'0') % 2;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String number = br.readLine();
		dfs(number, countOdd(number));
		
		System.out.println(min + " " + max);
	}

}
