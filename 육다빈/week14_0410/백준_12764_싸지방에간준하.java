package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G12764_JunhaWentToTheRoom {
	static class People implements Comparable<People>{
		int start, end;
		People(int start, int end){
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(People o) {
			return this.start - o.start;
		}
	}
	
	static class Room implements Comparable<Room>{
		int idx, cnt, time;
		Room(int idx, int time){
			this.idx = idx;
			this.time = time;
			this.cnt = 1;
		}
		@Override
		public int compareTo(Room o) {
			if(this.time==o.time) return this.idx - o.idx;
			else return this.time - o.time;
		}
	}
	
	static PriorityQueue<Room> passTime(PriorityQueue<Room> queue, int diff) { // 시간의 흐름 적용
		PriorityQueue<Room> result = new PriorityQueue<Room>();
		
		while(!queue.isEmpty()) {
			Room r = queue.poll();
			r.time -= diff;
			if(r.time<0) r.time = 0;
			result.add(r);
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<People> waiting = new PriorityQueue<People>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			waiting.add(new People(start, end));
		}
		
		int time=0, diff=0, roomIdx=0;
		PriorityQueue<Room> queue = new PriorityQueue<Room>();
		
		while(!waiting.isEmpty()) { // 시작 순서대로 사람 투입
			People p = waiting.poll();
			diff = p.start - time;
			time = p.start;
			
			PriorityQueue<Room> tmp = new PriorityQueue<Room>();
			while(!queue.isEmpty()) {
				Room tmp_r = queue.poll();
				tmp_r.time -= diff;
				if(tmp_r.time<0) tmp_r.time = 0;
				tmp.add(tmp_r);
			}
			queue = tmp;
			
			
			if(!queue.isEmpty() && queue.peek().time == 0) { // 빈방 있음
				Room r = queue.poll();
				r.cnt++;
				r.time = p.end - p.start;
				queue.add(r);
			}else {
				queue.add(new Room(roomIdx++, p.end-p.start));
			}
		}
		
		int[] result = new int[roomIdx];
		while(!queue.isEmpty()) {
			Room r = queue.poll();
			result[r.idx] = r.cnt;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(roomIdx + "\n");
		for(int cnt : result) sb.append(cnt + " ");
		
		System.out.println(sb);
	}

}
