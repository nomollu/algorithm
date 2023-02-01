public class B14675_단절점과단절선 {
	
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		List<Integer> edges [] = new LinkedList [N+1];
		
		for(int i=1; i<=N; i++) edges[i] = new LinkedList<>();
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			edges[start].add(end);
			edges[end].add(start);
		}
		
		int q = Integer.parseInt(br.readLine());
		
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			if(t == 1) {
				if(edges[k].size()>1) System.out.println("yes");
				else System.out.println("no");
			}
			else System.out.println("yes");
		}
		
		System.out.println(sb);
	}
	
}
