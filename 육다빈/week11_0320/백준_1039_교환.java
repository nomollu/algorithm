package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G1039_change {

	static int[] number;
	
	static int findMax(int start) { //최고값이 있는 자리수 반환
		int max = start;
		for(int i=number.length-1; i>start; i--) {
			if(number[max] < number[i]) max = i;
		}
		return max;
	}
	
	static void swap(int a, int b) { //자리 교환
		int tmp = number[a];
		number[a] = number[b];
		number[b] = tmp;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		char[] N = st.nextToken().toCharArray();
		int K = Integer.parseInt(st.nextToken());
		
		int len = N.length;
		number = new int[len];
		for(int i=0; i<len; i++) number[i] = N[i]-'0';
		
		int k=0;
		for(int i=0; i<N.length; i++) {
			int idx_max = findMax(i);
			if(idx_max==i) continue;
			swap(i, idx_max);
			
			if(++k==K) break;
		}
		
		if(k<K && ((len==2 && number[1]==0) || len==1) ) System.out.println(-1);
		else {
			while(++k<=K) {
				swap(len-2, len-1);
			}
			for(int n : number) System.out.print(n);
		}
	}
}
