import java.util.Scanner;
import java.util.Stack;

public class B1662_압축 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Node> st = new Stack<>();
		String s = sc.next();
		
		
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			
			if(c == '(') st.add(new Node(0, 0));
			else if(c == ')') {
				Node cur = st.pop();
				Node next = st.pop();
				next.cnt = next.cnt - 1 + (next.last * cur.cnt);
				next.last = cur.last;
				st.add(next);
			}else {
				int ci = c - '0';
				
				if(st.isEmpty()) st.add(new Node(ci, 1));
				else {
					Node n = st.pop();
					n.cnt++;
					n.last = ci;
					st.add(n);
				}
			}
		}
		
		System.out.println(st.peek().cnt);
	}
	
	static class Node{
		int cnt, last; //마지막 수와 몇 개인지만 기억하면 됨 
		public Node(int last, int cnt) {
			this.cnt = cnt;
			this.last = last;
		}
	}
}
