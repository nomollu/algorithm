import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Gold5_7490_0만들기 {
	static int N;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			dfs(2, 1, 1, -2, String.valueOf(1));
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	// num : 현재 수, sum : 현재까지 더한값, before : 바로 전 수, sign : 바로 전에 삽입한 부호
	// sign은 -1(-), 1(+), 0(공백)
	static void dfs(int num, int sum, int before, int sign, String s) {
		if (num == N + 1) { // 마지막 수까지 다 고려했을 경우
			if (sum == 0) {
				sb.append(s + "\n");
			}
			return;
		}

		String str = String.valueOf(num);
		// 공백을 삽입할 경우
		int newNum = Integer.parseInt(String.valueOf(before) + str); // 바로 전수와 현재수를 이어붙인다
		int newSum = sum; // 새로운 sum값
		if (sign == 1) { // 바로 전에 삽입한 부호가 +일경우 전 수를 빼준다음 newNum을 더해줌
			newSum = sum - before + newNum;
		} else if (sign == -1) { // 바로 전에 삽입한 부호가 -일경우 전 수를 더해준다음 newNum 빼줌
			newSum = sum + before - newNum;
		} else { // 바로 전에 공백을 삽입했을 경우 newSum을 newNum으로 갱신
			newSum = newNum;
		}
		// ASCII 순서는 공백 -> + -> - 임
		dfs(num + 1, newSum, newNum, 0, s + " " + str); // 공백
		dfs(num + 1, sum + num, num, 1, s + "+" + str); // +
		dfs(num + 1, sum - num, num, -1, s + "-" + str); // -
	}

}
