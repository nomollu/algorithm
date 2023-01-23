package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S1931_meetingRoom {

	static class Room implements Comparable<Room>{
		long start, end;
		Room(long start, long end){
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Room o) {
			if(this.end == o.end) return (int) (this.start-o.start);
			return (int) (this.end - o.end);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Room> rooms = new PriorityQueue<Room>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			rooms.add(new Room(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
		}
		
		long now = 0, result = 0;
		while(!rooms.isEmpty()) {
			Room r = rooms.poll();
			if(now <= r.start) {
				now = r.end;
				result++;
			}
		}
		
		System.out.println(result);
	}

}
