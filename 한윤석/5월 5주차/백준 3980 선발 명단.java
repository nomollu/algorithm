public class B3980_선발명단 {

	static int info [][]; // input
	static boolean used []; // [i]번째 포지션 사용 여부
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			info = new int [11][11];
			used = new boolean [11];
			ans = 0;
			
			for(int i=0; i<11; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<11; j++) info[i][j] = Integer.parseInt(st.nextToken());
			}
			
			backtrack(0, 0);
			System.out.println(ans);
		}
	}
	
	/*
	 * @param(idx : 선수 인덱스, sum : 퍼포먼스 합)
	 * */
	static boolean backtrack(int idx, int sum) {
		// 모든 포지션 다 할당됐으면
		if(idx == 11) {
			ans = Math.max(ans, sum);
			return true;
		}
		
		// idx번 선수를 i번째 포지션에 할당
		for(int i=0; i<11; i++) {
			// 이미 할당된 포지션이거나, 퍼포먼스가 0이면 건너뜀
			if(info [idx][i] == 0 || used[i]) continue;
			
			used[i] = true; //i번째 포지션 사용
			if(backtrack(idx+1, sum + info[idx][i])) {
				used[i] = false;
				return false;
			}
			used[i] = false;
		}
		
		return false;
	}

}
