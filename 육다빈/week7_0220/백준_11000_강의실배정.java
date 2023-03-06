package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G11000_classroomAssignment {
	
	static class Class implements Comparable<Class>{
		int start, end;
		Class(int start, int end){
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Class o) {
			return this.start - o.start;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Class[] classList = new Class[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			classList[i] = new Class(start, end);
		}
		
		Arrays.sort(classList);
		
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		queue.add(0);
		int cnt = 1;
		for(int i=0; i<N; i++) {
			if(queue.peek() <= classList[i].start) {
				queue.poll();
			}else {
				cnt++;
			}
			queue.add(classList[i].end);
		}
		
		System.out.println(cnt);
	}

}
