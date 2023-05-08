package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G22862_largestSubsequence {

	static class Seq{
		int length, cnt;
		Seq(int length, int cnt){
			this.length = length;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] seq = new int[N];
		for(int i=0; i<N; i++) seq[i] = Integer.parseInt(st.nextToken());
		
		Queue<Seq> queue = new LinkedList<Seq>();
		int max=0, len=0, cnt=0; // max=부분수열 최대길이, len=현재 큐에 들어간 부분수열 길이, cnt=현재 큐에 들어간 삭제횟수
		for(int i=-1; i<N; ) {
			int oddStart = i;
			
			while(++i<N) { // 홀수구간 탐색
				if(seq[i]%2 == 0) {
					int evenStart = i;
					
					while(true) { // 짝수구간 탐색
						if(++i>=N || seq[i]%2!=0) { // 탐색 완료
							int nowCnt = evenStart - oddStart;
							int nowLen = i - evenStart;
							
							if(!queue.isEmpty() && cnt+nowCnt > K) {
								Seq s = queue.poll();
								len -= s.length;
								if(!queue.isEmpty()) cnt -= queue.peek().cnt;
							}
							
							if(!queue.isEmpty()) cnt += nowCnt;
							len += nowLen;
							max = Math.max(max, len);
							
							queue.add(new Seq(nowLen, nowCnt));
							break;
						}
					}
					break;
				}
			}
		}
		System.out.println(max);
	}

}
