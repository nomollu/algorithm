import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold5_14921_용액합성하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int end = N - 1;
		int min = 100000000; //두 용액의 합의 절댓값
		int ans = 0; //두 용액 섞은 합
		while (start != end) {
			int sum = A[start] + A[end];
			if (sum < 0) { //만약 음수가 나올경우 start + 1
				start++;
			} else { // 양수일경우 end -1
				end--;
			}
			if (min > Math.abs(sum)) { //합의 절댓값이 min보다 작을때, min과 ans 갱신
				min = Math.abs(sum);
				ans = sum;
			}
		}
		System.out.println(ans);
	}

}
