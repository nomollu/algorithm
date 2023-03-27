import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_Gold3_1039_교환 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String N = st.nextToken();// 숫자
		int K = Integer.parseInt(st.nextToken());// 연산 수
		int[] arr = new int[N.length()];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(N.charAt(i) + "");
		}

		PriorityQueue<Number> max = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {
			max.add(new Number(arr[i], i));
		}

		boolean flag = false; // 0으로 시작하는지 확인할 변수
		if (arr.length < 2) { // 길이가 1이면 바꿀게 없으니 안됨
			flag = true;
		}

		int idx = 0;
		while (!max.isEmpty() && !flag) {
			Number now = max.poll();
			int num = arr[idx];

			// max 큐에서 꺼낸 값이 현재 값보다 크고
			// max 큐에서 꺼낸 값의 인덱스가 현재 인덱스보다 클때
			if (num < now.value && idx < now.idx && idx < arr.length) {
				arr[now.idx] = num; // 위치를 바꿔준다
				arr[idx++] = now.value;
				K--; // 연산 횟수 감소
			}
			if (arr[0] == 0) { // 바꿨는데 맨 앞자리가 0인경우 안됨
				flag = true;
				break;
			}

			if (K == 0) {
				break;
			}
		}

		idx = arr.length - 1;
		while (K > 0 && !flag) { // 연산횟수가 남아있다면 맨 뒤 2자리를 계속 연산해준다
			int temp = arr[idx];
			arr[idx] = arr[idx - 1];
			arr[idx - 1] = temp;
			if (arr[0] == 0) { // 맨 앞자리가 0인 경우 안됨
				flag = true;
				break;
			}
			K--;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		System.out.println(flag ? -1 : sb.toString());

	}

	static class Number implements Comparable<Number> {
		int value, idx;

		public Number(int value, int idx) {
			super();
			this.value = value;
			this.idx = idx;
		}

		@Override
		public int compareTo(Number o) {
			if (this.value == o.value) {
				return o.idx - this.idx;
			}
			return o.value - this.value;
		}
	}
}
