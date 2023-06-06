public class B10986_나머지합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long S [] = new long [N+1]; // 누적합을 M으로 나눈 나머지
		long remain [] = new long [M]; // 나머지가 [i]인 수들의 수
		long ans = 0;
		st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++) {
			S[i] = (S[i-1] + Integer.parseInt(st.nextToken())) % M;
			if(S[i] == 0) ans++;
			remain[(int)S[i]]++;
		}
		
		for(int i=0; i<M; i++) {
			if(remain[i] > 1) ans += (remain[i] * (remain[i] - 1) / 2);
		}
		
		System.out.println(ans);
	}
}
