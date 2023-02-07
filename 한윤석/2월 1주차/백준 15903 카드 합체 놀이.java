public class B15903_카드합체놀이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //카드의 개수
		int m = Integer.parseInt(st.nextToken()); //카드 합체를 몇 번 하는지
		long ans = 0;
		PriorityQueue<Long> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) pq.add(Long.parseLong(st.nextToken()));
		
		while(m-- > 0) {
			long x = pq.poll();
			long y = pq.poll();
			long sum = x + y;
			pq.add(sum);
			pq.add(sum);
		}
		
		while(!pq.isEmpty()) ans += pq.poll();
				
		System.out.println(ans);
	}
}
