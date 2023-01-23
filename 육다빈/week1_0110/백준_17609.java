package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G17609_palindrome {

	static int isPallindrome(String s, boolean chance) {
		char[] str = s.toCharArray();
		int first=0, last=str.length-1;
		
		while(true) {
			if(first>=last) {
				if (chance) return 0;
				else return 1;
			}
			else if(str[first]==str[last]) {
				first += 1;
				last -= 1;
			}else {
				if(chance) {
					return Math.min(isPallindrome(s.substring(first+1, last+1), false)
							, isPallindrome(s.substring(first, last), false));
				}else return 2;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			System.out.println(isPallindrome(br.readLine(), true));
		}
	}

}
