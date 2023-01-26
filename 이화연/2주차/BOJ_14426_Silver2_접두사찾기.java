import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14426_접두사찾기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 비교 문자열 개수 N
		int M = Integer.parseInt(st.nextToken()); // 검사할 문자열 개수 M

		ArrayList<String>[] list = new ArrayList[28];
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<String>();
		}
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			list[s.charAt(0) - 'a'].add(s);
		}

		int cnt = 0;
		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			for (String str : list[s.charAt(0) - 'a']) {
				if (str.contains(s)) {
					cnt++;
					break; // 카운트 중복 방지를 위해 
				}
			}
		}
		System.out.println(cnt);
	}

}
