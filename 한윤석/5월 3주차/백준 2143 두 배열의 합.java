public class B2143_두배열의합 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int A[] = new int [N];
		int AM[] = new int [N];
		int ans = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			if(i == 0) AM[i] = A[i];
			else AM[i] = AM[i-1] + A[i];
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int B [] = new int [M];
		int BM [] = new int [M];
		
		for(int i=0; i<M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
			if(i == 0) BM[i] = B[i];
			else BM[i] = BM[i-1] + B[i];
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(AM[i] + BM[j] > T) continue;
				else if(AM[i] + BM[j] == T) ans++;
			}
		}
		
		System.out.println(ans);
	}
}
