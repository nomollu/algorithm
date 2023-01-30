package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S20413_MVPDiamond {
	
	static int gradeToIdx(char c) { // 등급 기준액이 저장된 배열(limit)의 인덱스를 반환
		switch(c) {
			case 'B' : return 0;
			case 'S' : return 1;
			case 'G' : return 2;
			case 'P' : return 3;
			case 'D' : return 4;
			default : return -1;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] limit = new int[5]; // 등급 기준액
		for(int i=1; i<5; i++) limit[i] = Integer.parseInt(st.nextToken()); 
		
		char[] grade = br.readLine().toCharArray(); // 배정받은 등급
		Queue<Integer> money = new LinkedList<Integer>(); // 과금액
		int sum = 0; // 2개월간 과금액 합계
		int result = 0; // 총 과금액
		
		for(int i=0; i<N; i++) {
			int idx = gradeToIdx(grade[i])+1; 
			int max = (idx > 4) ? -1 : limit[idx]; // 배정받은 등급 최대 누적 과금액 (최고등급은 -1)
			
			int tmp = max-1-sum; //이번달 과금액
			if(tmp > limit[4] || tmp < 0) tmp = limit[4];

			money.add(tmp);
			result += tmp;
			
			if(money.size()<2) sum += tmp; 
			else sum += tmp - money.poll();
		}
		System.out.println(result);
	}

}
