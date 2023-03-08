public class B16637_괄호추가하기 {
	
	static int N, ans = Integer.MIN_VALUE; //음수가 정답일 수도 있으므로 minvalue로 초기
	static String input;
	static Stack<String> st = new Stack<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = sc.next();
		
		backtrack(0, false, "");
		
		System.out.println(ans);
	}

	static void backtrack(int idx, boolean isBracketOn, String expression) {
		if(idx == N) {
			// 괄호 미리 연산하는 작업
			for(int i=0; i<expression.length(); i++) {
				char cur = expression.charAt(i);
				
				if(cur == ')') {
					int b = Integer.parseInt(st.pop());
					char op = st.pop().charAt(0);
					int a = Integer.parseInt(st.pop());
					
					int result = calc(a, b, op);
					st.add(Integer.toString(result));
				}else if(cur == '(') continue;
				else st.add(Character.toString(cur));
			}
			
			Stack<String> rawExpression = new Stack<>(); //괄호 연산 끝낸 수식 
			while(!st.isEmpty()) rawExpression.add(st.pop());
			
			int result = Integer.parseInt(rawExpression.pop());
			char op = ' ';
			
			//괄호 연산 끝난 수식들을 모두 계산하여 result에 계산 
			while(!rawExpression.isEmpty()) {
				String cur = rawExpression.pop();
				
				if(cur.equals("+") || cur.equals("-") || cur.equals("*")) op = cur.charAt(0);
				else result = calc(result, Integer.parseInt(cur), op);
			}
			
			ans = Math.max(ans, result);
			
			return;
		}
		
		char cur = input.charAt(idx);
		int icur = cur - '0';
		
		if(icur >= 0 && icur <= 9) { //숫자일 때 
			if(isBracketOn) backtrack(idx+1, false, expression + cur + ')');
			else {
				if(idx != N-1) backtrack(idx+1, true, expression + '(' + cur);
				backtrack(idx+1, false, expression + cur);
			}
		}else backtrack(idx+1, isBracketOn, expression+cur);
	}
	
	static int calc(int a, int b, char op) {
		if(op == '+') return a + b;
		else if(op == '*') return a * b;
		else if(op == '-') return a - b;
		return 0;
	}
}
