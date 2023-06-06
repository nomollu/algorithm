public class B9519_졸려 {
	static String s, init; // 바뀔 문자열, 초기 문자열
	static int len, range; //문자열 길이, 탐색범위

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		init = sc.next();
		s = init;
		len = s.length();
		int time = 0;
		range = len % 2 == 0 ? len / 2 : len / 2 + 1; 

		// 몇 회 주기로 제자리로 돌아오는지 time에 저장
		while(true) {
			time++;
			calc();
			if(init.equals(s)) break; //일치하는 순간에 스탑
		}
		
		// 모듈러 연산으로 필요한 연산 횟수만 도출
		time = X % time;
		while(time -- > 0) calc();
		
		System.out.println(s);
	}
	
	// 홀수번째 문자를 맨뒤로 보내기
	static void calc() {
		String left = "";
		String right = "";
		
		for(int i=0; i<range; i++) {
			left += s.charAt(i * 2);
			if(len % 2 == 0 || (len % 2 != 0 && i != range - 1))
			right = s.charAt(i * 2 + 1) + right;
		}
		s = left + right;
	}
}
