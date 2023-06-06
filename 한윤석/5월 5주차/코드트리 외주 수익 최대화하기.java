public class C_외주수익최대화하기 {

	static int n, ans = 0;
	static int cost [][]; // [i]일자로부터 [0]일 소요, [1] 수익
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		cost = new int [n][2];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			cost[i][0] = t;
			cost[i][1] = p;
		}
		
		outsourcing(0, 0);
		
		System.out.println(ans);
	}

	/*
	 * @param (idx : 일자, sum : 현재까지 수익  총 합)
	 * */
	static void outsourcing(int idx, int sum) {
		ans = Math.max(ans, sum);
		
		for(int i=idx; i<n; i++) {
			if(i + cost[i][0] <= n) { // 외주 받아도 시간 초과 안하면
				outsourcing(i+cost[i][0], sum + cost[i][1]);
			}
		}
	}
}
