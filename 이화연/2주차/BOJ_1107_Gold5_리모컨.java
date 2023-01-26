import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1107_리모컨 {
	static boolean[] numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 이동하려고 하는 채널
		numbers = new boolean[10]; // 숫자 버튼 상태 저장할 배열

		if (N == 100) { // 수빈이는 현재 100번 채널에 있음
			System.out.println(0);
			return;
		}

		int M = Integer.parseInt(br.readLine());
		if (M != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				numbers[Integer.parseInt(st.nextToken())] = true;
			}
		}

		int min = Math.abs(N - 100); // 모든 버튼이 고장나있을 경우 +,-로만 이동해야함

		// N이 500,000까지니까 6자리 중 가장 큰수 999,999까지 검사
		for (int i = 0; i <= 999999; i++) {
			String num = String.valueOf(i);

			boolean flag = true;
			for (int j = 0; j < num.length(); j++) {
				if (numbers[Integer.parseInt(num.charAt(j) + "")]) {
					flag = false; // 고장난 숫자를 포함하고 있는 경우 break;
					break;
				}
			}

			// 고장난 숫자 포함하고 있지 않고 min보다 작은 경우
			if (flag && min > Math.abs(N - i) + num.length()) {
				min = Math.abs(N - i) + num.length();
			}
		}
		System.out.println(min);
	}
}
