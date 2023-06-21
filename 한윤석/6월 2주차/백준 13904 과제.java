public class B13904_과제 {

	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Work> pq = new PriorityQueue<>(cp1); // 남은 일자가 많은 순으로 내림차순한 큐
		PriorityQueue<Work> canDo = new PriorityQueue<>(cp2); // 점수가 높은 순으로 내림차순한 큐
		int ans = 0;
		int today = 0; // 일자를 뒤에서부터 셀거라서 나온 남은 일수 중 제일 큰 일수
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			pq.add(new Work(day, score));
			today = Math.max(today, day);
		}
		
		while(today>= 1) {
			// 오늘 날짜보다 뒤에 해도 되는 과제들 담기
			while(!pq.isEmpty() && pq.peek().day >= today) {
				Work w = pq.poll();
				canDo.add(w);
			}
			
			// 할 수 있는 과제들 중 가장 점수 큰 걸 오늘 하기
			if(!canDo.isEmpty()) {
				Work w = canDo.poll();
				ans += w.score;
			}
			today--;
		}
		
		System.out.println(ans);
	}
	
	static class Work{
		int day, score;
		public Work(int day, int score) {
			this.day = day;
			this.score = score;
		}
	}
	
	static Comparator<Work> cp1 = new Comparator<Work>() {
		public int compare(Work o1, Work o2) {
			return o2.day - o1.day;
		}; 
	};
	
	static Comparator<Work> cp2 = new Comparator<Work>() {
		public int compare(Work o1, Work o2) {
			return o2.score - o1.score;
		}; 
	};
}
