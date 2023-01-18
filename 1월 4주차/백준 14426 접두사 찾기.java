public class B14426_접두사찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //집합 문자열 수
		int M = Integer.parseInt(st.nextToken()); //탐색 문자열 수
		String S [] = new String [N]; //집합 문자열
		int ans = 0;
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			S[i] = s;
		}
		
		for(int i=0; i<M; i++) {
			String s = br.readLine(); //탐색 문자열 입력
			
			//모든 집합 문자열들 조회
			for(int j=0; j<N; j++) {
				String cur = S[j];
				
				//접두사에 오려면 indexOf가 무조건 0이어야 함
				if(cur.indexOf(s) == 0) {
					ans++;
					break;
				}
			}
		}
		
		System.out.println(ans);
	}
}
