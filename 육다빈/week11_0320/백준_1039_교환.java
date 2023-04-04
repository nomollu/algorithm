package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1039_change {

	static int change(String number, int i, int j) {
		char a = number.charAt(i);
		char b = number.charAt(j);
		String result = number.substring(0, i) + b + number.substring(i+1, j) + a + number.substring(j+1);
		
		if(result.charAt(0)=='0') return -1;
		else return Integer.parseInt(result);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		
		int cnt=0, len = (start+"").length();
		while(!queue.isEmpty() && ++cnt<=K) {
			int size = queue.size();
			boolean[] visit = new boolean[(int) Math.pow(10, len)];

			for(int s=0; s<size; s++) {
				int now = queue.poll();
				
				for(int i=0; i<len; i++) {
					for(int j=i+1; j<len; j++) {
						int chg = change(now+"", i, j);

						if(chg<0 || visit[chg]) continue;
					
						visit[chg] = true;
						queue.add(chg);
					}
				}
			}
		}
		
		if(queue.isEmpty()) System.out.println(-1);
		else {
			Object[] tmp = queue.toArray();
			Arrays.sort(tmp, Collections.reverseOrder());
			System.out.println(tmp[0]);
		}
	}
}
