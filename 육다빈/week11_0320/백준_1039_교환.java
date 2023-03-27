package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G1039_change {

// 문제 푸는 방법을 아직 끝까지 떠올리지 못함...
  
//	static class Number implements Comparable<Number>{
//		int value, idx;
//		Number(int value, int idx){
//			this.value = value;
//			this.idx = idx;
//		}
//		
//		@Override
//		public int compareTo(Number o) {
//			if(this.value == o.value) return o.idx-this.idx;
//			else return o.value-this.value;
//		}
//	}
	
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
		
		number = new int[N.length];
		for(int i=0; i<N.length; i++) number[i] = N[i]-'0';
		
		int k=0;
		for(int i=0; i<N.length; i++) {
			int idx_max = findMax(i);
			if(idx_max==i) continue;
			
			swap(i, idx_max);

			if(++k == K) {
				while(++i<N.length) {
					sb.append(number[i]);
				}
			}
			System.out.println(Arrays.toString(number));
		}

		
		System.out.println((k==K) ? sb : -1);
	}
