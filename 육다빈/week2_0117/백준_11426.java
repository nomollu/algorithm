package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S11426_findPrefix {

	static boolean isPrefix(String s, char[] now) {
		int size = now.length;
		for(int j=0; j<size; j++) {
			if(s.charAt(j)!=now[j]) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<String>[] str = new List[26];
		for(int i=0; i<26; i++) str[i] = new ArrayList<String>();
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			str[s.charAt(0)-'a'].add(s);
		}
		
		int cnt = 0;
		for(int i=0; i<M; i++) {
			char[] now = br.readLine().toCharArray();
			int idx = now[0]-'a';
			for(String s : str[idx]) {
				if(isPrefix(s, now)) {
					cnt++;
					break;
				}
			}
		}
		
		System.out.println(cnt);
		
	}

}
