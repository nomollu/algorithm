import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Silver1_2343_기타레슨 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] bluelay = new int[N];
		int low = 0; // 녹화길이 최소
		int high = 0; // 녹화길이 최대
		for (int i = 0; i < N; i++) {
			bluelay[i] = Integer.parseInt(st.nextToken());
			high += bluelay[i]; // 들어오는 값 모두 더해서 녹화길이 최댓값
			low = Math.max(low, bluelay[i]); // 들어오는 값 중 최댓값을 녹화길이의 최솟값으로 정한다
		}

		int mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;

			int cnt = 0; // mid보다 작은 블루레이의 개수
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (sum + bluelay[i] > mid) { // 만약 mid보다 크면 더이상 더할 수 없음
					sum = 0; // 0으로 초기화
					cnt++; // 블루레이 개수 올려주기
				}
				sum += bluelay[i];
			}
			if (sum > 0) { // 반복문 끝났는데 sum 값이 0보다 크다면 나눠진 영역이 하나더 존재하는 것
				cnt++;
			}

			if (cnt <= M) {
				high = mid - 1;
			} else if (cnt > M) {
				low = mid + 1;
			}
		}
		System.out.println(low);
	}

}
