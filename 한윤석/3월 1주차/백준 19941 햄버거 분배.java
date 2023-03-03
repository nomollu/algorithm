public class B19941_햄버거분배 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int ans = 0;
		boolean check [] = new boolean[N];
		String input = br.readLine();
		
		for(int i=0; i<N; i++) {
			char cur = input.charAt(i);
			
			if(cur == 'H') {
				for(int j=i-K; j<=i+K; j++) {
					if(j < 0 || j >= N) continue;
					
					if(input.charAt(j) == 'P' && !check[j]) {
						check[j] = true;
						ans++;
						break;
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}
