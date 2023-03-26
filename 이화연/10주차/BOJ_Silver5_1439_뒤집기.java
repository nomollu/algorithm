import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Silver5_1439_뒤집기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int[] count = new int[2];

		boolean flag = S.charAt(0) == '1' ? true : false; // 1이면 true, 0이면 false
		for (int i = 1; i < S.length(); i++) {
			if (flag && S.charAt(i) == '0') {
				flag = false;
				count[1]++;
			} else if (!flag && S.charAt(i) == '1') {
				flag = true;
				count[0]++;
			}
		}

		if (S.charAt(S.length() - 1) == '0') {
			count[0]++;
		} else {
			count[1]++;
		}

		System.out.println(Math.min(count[0], count[1]));
	}

}
