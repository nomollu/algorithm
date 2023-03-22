public class B1522_문자열교환 {
	
	static int ans = Integer.MAX_VALUE;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		
		int rc = 0; //빨간공 수
		int bc = 0; //파란공 수
		
		for(int i=0; i<N; i++) {
			if(s.charAt(i) == 'R') rc++;
			if(s.charAt(i) == 'B') bc++;
		}
		
		solve('R', s, rc);
		solve('B', s, bc);
		
		System.out.println(ans);
	}
	
	//param:{color : 공 색, s : 입력문자열, cnt : 해당 공의 개수}
	static void solve(char color ,String s, int cnt) {
		int temp = 0;
		//왼쪽으로 넘기기
		for(int i=0; i<N; i++) {
			if(s.charAt(i) == color) temp++;
			else break;
		}
		ans = Math.min(ans, cnt - temp);
		
		temp = 0;
		//오른쪽으로 넘기기
		for(int i=N-1; i>=0; i--) {
			if(s.charAt(i) == color) temp++;
			else break;
		}
		ans = Math.min(ans, cnt - temp);
	}
}
