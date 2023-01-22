import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11000_강의실배정 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Class[] c = new Class[N]; // 강의 담을 배열
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			c[i] = new Class(s, t);
		}
		Arrays.sort(c);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(c[0].t); // 첫번째 강의의 종료 시간
		for (int i = 1; i < N; i++) { // 첫번째 강의 이미 넣었으니 1부터 시작
			if (pq.peek() <= c[i].s) { // 현재 큐에 들어가 있는 종료시간이 다음 강의 시작시간보다 작거나 같을경우
				pq.poll();
			}
			pq.add(c[i].t);
		}
		System.out.println(pq.size());
	}

	static class Class implements Comparable<Class> {
		int s, t;

		public Class(int s, int t) {
			super();
			this.s = s;
			this.t = t;
		}

		@Override
		public int compareTo(Class o) {
			if (this.s - o.s == 0) {
				return this.t - o.t;
			}
			return this.s - o.s;
		}
	}
}
