public class B14699_관악산등산 {
	
	static int N,M; //쉼터 수, 경로 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int ans [] = new int [N+1]; // 정답
		int height [] = new int [N+1]; // i번 쉘터의 높이
		List<Integer> route [] = new LinkedList[N+1]; // i번 쉘터와 연결된 다른 쉘터들
		PriorityQueue<Shellter> pq = new PriorityQueue<>();
		
		for(int i=1; i<=N; i++) {
			int h = Integer.parseInt(st.nextToken());
			pq.add(new Shellter(i, h));
			route[i] = new LinkedList<>();
			height[i] = h;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int big = height[A] > height[B] ? A : B;
			int small = height[A] < height[B] ? A : B;
			
			route[small].add(big); //낮은곳에서 높은 곳으로 가는 경로만 넣음
		}
		
    //제일 높은 곳부터 내려옴
		while(!pq.isEmpty()) {
			Shellter sh = pq.poll();
			int cnt = 0;
      
			for(int i : route[sh.n]) cnt = Math.max(cnt, ans[i]);
      
			ans[sh.n] = cnt+1;
		}

		for(int i=1; i<=N; i++) System.out.println(ans[i]);
	}
	
	static class Shellter implements Comparable<Shellter>{
		int n, h;
		public Shellter(int n, int h) {
			this.n = n;
			this.h = h;
		}
		@Override
		public int compareTo(Shellter o) {
			return o.h - this.h;
		}
	}
}
