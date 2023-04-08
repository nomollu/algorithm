import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold3_1039_교환 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String N = st.nextToken();// 숫자
		int K = Integer.parseInt(st.nextToken());// 연산 수
		boolean[][] visited = new boolean[K + 1][1000001];

		Queue<String> queue = new LinkedList<>();
		queue.add(N);

		while (!queue.isEmpty() && K > 0) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				String now = queue.poll();

				for (int i = 0; i < N.length() - 1; i++) {
					for (int j = i + 1; j < N.length(); j++) {
						int next = swap(now, i, j);
						// 맨 앞자리가 0이 아니고 현재 수가 지금 turn에서 방문한 적이 없을때
						if (next != -1 && !visited[K][next]) {
							queue.add(String.valueOf(next));
							visited[K][next] = true;
						}
					}
				}

			}
			K--;
		}

		int max = -1;
		for (String s : queue) { // 큐에 있는 것 중 최대값 뽑기
			max = Math.max(max, Integer.parseInt(s));
		}
		System.out.println(max);

	}

	static int swap(String now, int i, int j) {
		char[] arr = now.toCharArray();
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;

		if (arr[0] == '0') { // 맨 앞이 0이면 안됨
			return -1;
		}

		String newStr = "";
		for (char c : arr) {
			newStr += c + "";
		}
		return Integer.parseInt(newStr);
	}
}
