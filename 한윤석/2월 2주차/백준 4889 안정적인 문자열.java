public class B4889_안정적인문자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 0;
		
		while(true) {
			String s =  br.readLine();
			int ans = 0;
			Stack<Character> st = new Stack<>();
			T++;
			
			if(s.indexOf('-') >= 0) break;
			
			for(int i=0; i<s.length(); i++) {
				char c = s.charAt(i);
				
				if(c == '{') st.add(c);
				else {
					if(st.isEmpty()) { //빈 스택에 닫는 괄호 넣으면 무조건 바꿔야 하고 이를 {로 넣음
						ans ++;
						st.add('{');
					}
					else st.pop(); //스택 꼭대기가 여는 괄호라면 그냥 빼줌
				}
			}
			
			int remain = 0;
			while(!st.isEmpty()) { //여는 괄호의 개수 세서 짝지으려면 /2만큼 변환 필요
				st.pop();
				remain++;
			}
			
			ans += remain / 2;
			
			System.out.println(T + ". " +ans);
		}
	}

}
