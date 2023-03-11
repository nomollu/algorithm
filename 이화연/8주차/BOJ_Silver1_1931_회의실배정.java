import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Silver1_1931_회의실배정 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Room[] rooms = new Room[N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			rooms[i] = new Room(start, end);
		}
		Arrays.sort(rooms); // 정렬

		int prevEndTime = 0; // 직전 회의 종료 시간
		int ans = 0;
		for (int i = 0; i < N; i++) {
			if (prevEndTime <= rooms[i].start) {
				prevEndTime = rooms[i].end;
				ans++;
			}
		}
		System.out.println(ans);
	}

	static class Room implements Comparable<Room> {
		int start, end;

		public Room(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Room o) {
			if (this.end == o.end) {
				return this.start - o.start;
			}
			return this.end - o.end;
		}
	}
}
