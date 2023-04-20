public class B12764_싸지방에간준하 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int endTime [] = new int [N]; //i번 pc의 마지막 종료 시
		int manCnt [] = new int [N]; //i번 자리를 사용한 사람의 
		int pcCnt = 0; //사용중인 pc 대
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.add(new Node(start, end));
		}
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			boolean canIUsePC = false; //현재 운영중인 pc중에 사용 가능한 것 있는지 여
			
			//첫 이용
			if(pcCnt == 0) {
				endTime[0] = n.e;
				manCnt[0] = 1;
				pcCnt++;
				continue;
			}
			
			for(int i=0; i<pcCnt; i++) {
				if(endTime[i] <= n.s) { // 현재 pc에서 이어서 쓸 수 있으면 
					manCnt[i]++;
					endTime[i] = n.e;
					canIUsePC = true;
					break;
				}
			}
			
			//현재 운영중인 pc중 쓸 수 있는게 없으면 새로 등
			if(!canIUsePC) {
				endTime[pcCnt] = n.e;
				manCnt[pcCnt] = 1;
				pcCnt++;
			}
		}
		
		System.out.println(pcCnt);
		for(int i=0; i<pcCnt; i++) System.out.print(manCnt[i] + " ");
	}
	
	static class Node implements Comparable<Node>{
		int s, e;
		public Node(int s, int e) {
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Node o) {
			return this.s - o.s;
		}
	}
}
