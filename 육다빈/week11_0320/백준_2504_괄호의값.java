package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class S2504_bracketsValue {
	
	static int calc(char[] str) {
		Stack<Integer> stack = new Stack<Integer>();
		
		for(char c : str) {
			
			if(c=='(' || c=='[') { // '('=-2, '['=-3
				stack.add((c=='(') ? -2 : -3);
				
			}else {
				if(stack.isEmpty()) return 0;
				int top = stack.pop();
				if((top==-2 && c==')') || (top==-3 && c==']')) {
					int nextTop = 0;
					if(!stack.isEmpty() && stack.peek()>0) nextTop = stack.pop(); // 앞에 더할 수가 있는 경우
					stack.add(nextTop + (-1)*top);
					
				}else if(top>0){
					if(stack.isEmpty() || // 이번 괄호의 짝이 없거나, 알맞은 괄호짝이 아닐 경우
							((c==')')&&(stack.peek()!=-2)) || ((c==']')&&(stack.peek()!=-3))) return 0;
					stack.pop();
					int res = top*((c==')') ? 2 : 3); // 앞 계산결과에 곱셈
					if(!stack.isEmpty() && stack.peek()>0) res += stack.pop();
					stack.add(res);
					
				}else{
					return 0;
				}
			}
		}
		
		if(stack.size()>1 || stack.peek()<0) return 0;
		else return stack.pop();
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println(calc(br.readLine().toCharArray()));
	}

}
