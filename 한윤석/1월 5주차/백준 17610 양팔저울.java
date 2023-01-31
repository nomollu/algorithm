public class B17610_양팔저울 {

	static int N, S=0;
	static int choo []; 
	static boolean check [];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		choo = new int [N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			choo[i] = Integer.parseInt(st.nextToken());
			S += choo[i];
		}
		check = new boolean [S+1];
		
		for(int i=0;i<N; i++) {
			check[choo[i]] = true;
			calc(i, choo[i]);
		}
		
		int ans = 0;
		for(int i=1; i<=S; i++) if(!check[i]) ans++; 
		
		System.out.println(ans);
	}
	
	static void calc(int idx, int sum) {
		for(int i=idx+1; i<N; i++) {
			int minus = Math.abs(choo[i] - sum);
			int plus = choo[i] + sum;
			check[minus] = true;
			calc(i, minus);
			check[plus] = true;
			calc(i, plus);
		}
	}
}
