public class B11441_합구하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int memo [] = new int[N];
		
		for(int i=0; i<N; i++) {
			if(i == 0) memo[i] = Integer.parseInt(st.nextToken());
			else memo[i] = memo[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			
			if(start == 0) System.out.println(memo[end]);
			else System.out.println(memo[end] - memo[start-1]);
		}
	}

}
