package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class S1935_postfixNotation {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		double[] num = new double[N];
		for(int i=0; i<N; i++) num[i] = Integer.parseInt(br.readLine());
		
		Stack<Double> stack = new Stack<Double>();
		int len = str.length();
		for(int i=0; i<len; i++) {
			char c = str.charAt(i);
			switch(c){
				case '+' :
					stack.add(stack.pop() + stack.pop());
					break;
				case '-' :
					double a = stack.pop();
					double b = stack.pop();
					stack.add(b-a);
					break;
				case '*' :
					stack.add(stack.pop() * stack.pop());
					break;
				case '/' :
					a = stack.pop();
					b = stack.pop();
					stack.add(b/a);
					break;
				default :
					stack.add(num[c-'A']);
			}
		}
		
		System.out.printf("%.2f", stack.pop());
	}

}
