public class B2075_N번째큰수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int ans = 0;
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) pq.add(Integer.parseInt(st.nextToken()));
		}

		for(int i=0; i<N; i++) {
			ans = pq.poll();
		}
		
		System.out.println(ans);
	}
}
