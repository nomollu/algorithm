import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_Gold5_2138_전구와스위치 {
	static int N, min;
	static char[] nowStatus, makeStatus;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nowStatus = br.readLine().toCharArray();
		makeStatus = br.readLine().toCharArray();
		min = Integer.MAX_VALUE;

		// 0번 스위치 안 눌렀을 때
		char[] newArray = Arrays.copyOf(nowStatus, N);
		pressCount(newArray, 0);

		// 0번 스위치 눌렀을 때 : 0,1번 스위치 반전
		char[] newArray2 = Arrays.copyOf(nowStatus, N);
		newArray2[0] = switchStatus(nowStatus, 0);
		newArray2[1] = switchStatus(nowStatus, 1);
		pressCount(newArray2, 1);

		System.out.println(min == Integer.MAX_VALUE ? -1 : min);

	}

	// 스위치 누르는 횟수 세기
	static void pressCount(char[] str, int cnt) {
		for (int i = 1; i < N; i++) {
			if (str[i - 1] != makeStatus[i - 1]) { // 전 인덱스의 값이 다를 경우 스위치 누르기
				cnt++; // 스위치 횟수 증가
				if (i - 1 >= 0) // i-1
					str[i - 1] = switchStatus(str, i - 1);
				str[i] = switchStatus(str, i); // i
				if (i + 1 < N) // i+1
					str[i + 1] = switchStatus(str, i + 1);
			}
		}
		if (String.valueOf(str).equals(String.valueOf(makeStatus))) { // 만들고자 하는 상태와 같을 경우
			min = Math.min(min, cnt);
		}
	}

	// 전구 상태 반전시키기
	static char switchStatus(char[] str, int index) {
		if (str[index] == '0') {
			return '1';
		}
		return '0';
	}

}
