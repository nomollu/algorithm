public class B7490_0만들기 {

	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		while(T-- > 0) {
			N = sc.nextInt();
			
			backtrack("1", 2);
			System.out.println();
		}
		
	}

	static void backtrack(String s, int n) {
		if(n > N) {
			int sum = 0; //연산의 합
			int num = -1; //피연산자인 숫자
			char op = '+';
			String trimedStr = s.replaceAll(" ", ""); //공백을 제거한 식

			for(int i=0; i<trimedStr.length(); i++) {
				char c = trimedStr.charAt(i);
				int ci = c - '0';
				
				if(ci >= 1) { //숫자이면
					if(num == -1) num = ci;
					else { //
						num *= 10;
						num += ci;
					}
					
					//마지막 수거나 다음 칸이 연산자이면
					if(i == trimedStr.length()-1 || trimedStr.charAt(i+1) - '0' < 1) {
						if(op == '+') sum += num;
						else sum -= num;
						num = -1;
					}
				}else op = c;
				
			}
			if(sum == 0) System.out.println(s);
			
			return;
		}
		
		backtrack(s+" "+n, n+1);
		backtrack(s+"+"+n, n+1);
		backtrack(s+"-"+n, n+1);
	}
}
