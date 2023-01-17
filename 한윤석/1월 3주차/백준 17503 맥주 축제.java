public class B17503_맥주축제 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //일 수, 마실 맥주 수
		int M = Integer.parseInt(st.nextToken()); //선호도 합
		int K = Integer.parseInt(st.nextToken()); //맥주 종류 수
		PriorityQueue<Beer> beers = new PriorityQueue<>();
		int ans = -1;
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int pref = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			beers.add(new Beer(pref, level));
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int prefSum = 0;
		
		while(!beers.isEmpty()) {
			Beer cur = beers.poll();
			
			pq.add(cur.pref);
			prefSum += cur.pref;
			
			if(pq.size() > N) {
				prefSum -= (pq.poll());
			}
			
			if(prefSum >= M && pq.size() == N) {
				ans = cur.level;
				break;
			}
		}
		
		System.out.println(ans);
	}

	static class Beer implements Comparable<Beer>{
		int pref, level;
		public Beer(int pref, int level) {
			this.pref = pref;
			this.level = level;
		}
		@Override
		public int compareTo(Beer o) {
			if(this.level == o.level) 
				return o.pref - this.pref;
			else 
				return this.level - o.level;
		}
	}
}
