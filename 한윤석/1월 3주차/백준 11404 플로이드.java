public class B11404_플로이드 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		long d[][] = new long [N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i == j) d[i][j] = 0;
				else d[i][j] = Integer.MAX_VALUE;
			}
		}

		for(int t=0; t<M; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			d[start][end] = Math.min(d[start][end], cost);
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				for(int k=1; k<=N; k++) d[j][k] = Math.min(d[j][k],  d[j][i] + d[i][k]);
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(d[i][j] == Integer.MAX_VALUE) d[i][j] = 0;
				System.out.print(d[i][j] + " ");
			}
			System.out.println();
		}
	}
}
