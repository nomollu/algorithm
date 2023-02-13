import java.util.*;
import java.io.*;

public class BOJ_Gold5_1662_압축 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();

		Stack<Integer> stack = new Stack<Integer>(); // 길이를 저장할 스택
		for (int i = 0; i < S.length(); i++) {
			String now = S.charAt(i) + "";
			if (now.equals("(")) { // 여는괄호는 -1로 넣어주자
				stack.push(-1);
			} else if (now.equals(")")) {
				// 닫는괄호를 만났으니 여는괄호를 찾아보자
				int index = 0; // 여는괄호 만나기 전까지 길이
				while (true) {
					int before = stack.pop();
					if (before != -1) { // 여는괄호 아닐경우 길이 증가
						index += before;
					} else {
						break;
					}
				}
				int k = stack.pop(); // 반복할 숫자 k
				stack.push(k * index); // k랑 여는괄호 만나기 전까지 길이 곱해서 스택에 넣기
			} else if (i < S.length() - 1 && S.charAt(i + 1) == '(') { // 숫자 + (
				stack.push(Integer.parseInt(now)); // 다음이 여는 괄호면 K번 반복이니 그 숫자 그대로 넣어줌
			} else { // 숫자 + 숫자
				stack.push(1);
			}

		}

		int ans = 0;
		while (!stack.isEmpty()) {
			ans += stack.pop(); // 길이 더하기
		}
		System.out.println(ans);
	}
}
