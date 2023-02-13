package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S2805_cutTree {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] tree = new int[N];
		for(int i=0; i<N; i++) tree[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(tree);
		
		int h=tree[N-1], now=N-1, cnt=1;
		long sum=0;
		while(true) {
			if(now-1>=0 && tree[now-1]==h) {
				now--;
				cnt++;
			}
			else {
				sum += cnt;
				h--;
				if(sum>=M) break;
			}
		}
		System.out.println(h);
	}

}
