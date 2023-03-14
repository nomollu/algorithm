public class B3986_좋은단어 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int ans = 0;
		
		while(N-- > 0) {
			String s = sc.next();
			Stack<Character> st = new Stack<>();
			
			for(int i=0; i<s.length(); i++) {
				char c = s.charAt(i);
				
				if(st.isEmpty()) st.add(c);
				else if(st.peek() == c) st.pop();
				else st.add(c);
			}
			
			if(st.isEmpty()) ans++;
		}
		
		System.out.println(ans);
	}
}
