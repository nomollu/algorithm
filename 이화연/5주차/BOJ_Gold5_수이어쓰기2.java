import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold5_수이어쓰기2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // N까지 수 이어 쓰기
		int k = Integer.parseInt(st.nextToken()); // k번째 자리 숫자

		// k번째 자리의 숫자가 포함되는 실제 숫자의 길이를 구한다.
		int length = 1;
		while (true) {
			long sum = (long) (length * Math.pow(10, length - 1) * 9);
			if (sum < k) { // k가 length 자리 수 총 개수보다 클 경우
				length++; // 자리 수 증가
				k -= sum; // 해당하는 자리 수가 아니니까 빼줘야함
			} else {
				break;
			}
		}
//		System.out.println(length + " " + index);

		int realNum = (int) (Math.pow(10, length - 1) + (k / length)) - 1; // k번째 자리의 숫자가 포함되는 실제 숫자 구하기
		int remain = k % length; // k번째 자리 수를 length길이로 나눈 나머지
		String strRealNum = String.valueOf(realNum);

		if (N < realNum) { // k번째 숫자를 포함한 실제 숫자가 N보다 작으면
			System.out.println(-1);
			return;
		}

		if (remain == 0) { // 나머지가 0이면 마지막 숫자 출력
			System.out.println(strRealNum.charAt(strRealNum.length() - 1));
		} else {
			System.out.println(strRealNum.substring(remain - 1, remain));
		}

	}

}
