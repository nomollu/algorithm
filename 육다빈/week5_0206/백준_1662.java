package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G1662_compression {

	static int decompress(String s) { // 압축을 풀었을 때 길이를 반환
		int len = s.length();
		int cnt = 0;
		
		for(int i=0; i<len; i++) {
			if(s.charAt(i)=='(') { // 내부의 압축문자열 발견
				int start = i+1;
				int cnt_left = 1;  // '('의 개수
				int cnt_right = 0; // ')'의 개수
				while(cnt_left != cnt_right) { // 압축된 부분이 어디까지인지 인덱스 찾기 (가장 외부 괄호 기준)
					if(s.charAt(++i)=='(') cnt_left++;
					else if(s.charAt(i)==')') cnt_right++;
				}
				int K = s.charAt(start-2)-'0';
				cnt += K * decompress(s.substring(start, i)) - 1; // 앞전에 cnt올려줬던거 하나 빼주기 
			}else {
				cnt++;
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println(decompress(br.readLine()));
		
	}

}
