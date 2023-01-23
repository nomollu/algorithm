package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class S1213_palindrome {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] ch = br.readLine().toCharArray();
		int[] cnt = new int[26];
		for(char c : ch) cnt[c-'A']++;
		
		int mid = -1;
		for(int i=0; i<26; i++) {
			if(cnt[i]%2 != 0) {
				if(mid != -1) {
					System.out.println("I'm Sorry Hansoo");
					return;
				}else {
					mid = i;
				}
			}
		}
		
		String result = "";
		if(mid != -1) {
			result = (char)(mid+'A')+"";
			cnt[mid]--;
		}

		for(int i=25; i>=0; i--) {
			String alpa = (char)(i+'A') + "";
			while(cnt[i] > 0) {
				result = alpa + result + alpa;
				cnt[i] -= 2;
			}
		}
		
		System.out.println(result);
	}
}
