public class B5567_결혼식 {

	static int N,M; // 동기 수, 친구 관계 수
	static List<Integer> relation []; //[i]와의 친구 관계
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		relation = new LinkedList[N+1];
		int ans = 0;
		boolean visit [] = new boolean[N+1];
		
		for(int i=1; i<=N; i++) relation[i] = new LinkedList<>();
		
		while(M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			relation[a].add(b);
			relation[b].add(a);
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visit[1] = true;
		
		int time = 0;
		while(!q.isEmpty()) {
			if(time >= 2) break; //친구의 친구까지만 초대할거임
			
			int size = q.size();
			for(int i=0; i<size; i++) {
				int cur = q.poll();
				
				for(int j : relation[cur]) {
					if(visit[j]) continue;
					
					visit[j] = true;
					q.add(j);
					ans++;
				}
			}
			time++;
		}
		
		System.out.println(ans);
	}
}
