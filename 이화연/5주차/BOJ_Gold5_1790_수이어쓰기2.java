import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold5_1790_수이어쓰기2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // N까지 수 이어 쓰기
		int k = Integer.parseInt(st.nextToken()); // k번째 자리 숫자

		int length = 1; // 자릿수
		int realNum = 0; // k가 포함된 실제숫자
		while (true) {
			int cnt = (int) (length * Math.pow(10, length - 1) * 9);
			if (k - cnt < 0) {
				break;
			}
			realNum += Math.pow(10, length - 1) * 9; // 현재길이의 가장 끝자리수를 만들기
			k -= cnt; // 해당하는 자리 수가 아니니까 빼줘야함
			length++; // 자리 수 증가
		}

		int remain = k % length;

		if (remain == 0) {
			realNum += k / length;
		} else {
			realNum += k / length + 1; // 나머지가 있는건 현재수의 그 다음수에 k가 있다는 것
		}

		if (N < realNum) { // k번째 숫자를 포함한 실제 숫자가 N보다 작으면
			System.out.println(-1);
			return;
		}

		String strRealNum = String.valueOf(realNum);
		if (remain == 0) { // 나머지가 0이면 마지막 숫자 출력
			System.out.println(strRealNum.charAt(strRealNum.length() - 1));
		} else {
			System.out.println(strRealNum.charAt(remain - 1));
		}

	}

}
