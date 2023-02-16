public class B18429_근손실 {

	static int N,K, ans =0;
	static int A [];
	static boolean check [];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int [N];
		check = new boolean[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());
		
		backtrack(0, 500);
		
		System.out.println(ans);
	}
	
	static void backtrack(int cnt, int sum) {
		if(cnt == N) {
			ans++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(check[i]) continue;
			
			int nextSum = sum + A[i] - K;
			
			if(nextSum < 500) continue;
			
			check[i] = true;
			backtrack(cnt+1, nextSum);
			check[i] = false;
		}
	}
}
