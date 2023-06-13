package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G9519_sleepy {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int len = str.length();
		
		for(int t=0; t<T; t++) {
			String left="", right="";
			for(int i=0; i<len; i++) {
				if(i%2 == 0) left += str.charAt(i);
				else right = str.charAt(i) + right;
			}
			str = left + right;
		}
		
		System.out.println(str);
	}

}
