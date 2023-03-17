import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Silver2_1541_잃어버린괄호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split("-"); // 뺄셈으로 분리

		int ans = 0;
		for (int i = 0; i < str.length; i++) {
			String[] divide = str[i].split("\\+"); // 덧셈으로 분리
			int sum = 0;
			for (int j = 0; j < divide.length; j++) {
				sum += Integer.parseInt(divide[j]);
			}

			if (i == 0) {
				ans = sum;
			} else {
				ans -= sum;
			}
		}
		System.out.println(ans);
	}
}
