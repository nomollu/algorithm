public class B12764_싸지방에간준하 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Map<Integer, Node> cpt = new HashMap<>(); 
		int idx = 0;
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.add(new Node(start, end, 0));
		}
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			boolean isFind = false;
			
			for(int key : cpt.keySet()) {
				Node val = cpt.get(key);
				
				if(n.s < val.s || n.s < val.e) continue;
				
				isFind = true;
				cpt.put(key, new Node(n.s, n.e, val.cnt + 1));
				break;
			}
			
			//새로 컴퓨터 추가
			if(!isFind) {
				cpt.put(idx++, new Node(n.s, n.e, 1));
			}
		}
		
		Set<Integer> keyset = cpt.keySet();
		System.out.println(keyset.size());
		StringBuilder sb = new StringBuilder();
		for(int i : keyset) {
			Node n = cpt.get(i);
			sb.append(n.cnt).append(" ");
		}
		System.out.println(sb);
	}
	
	static class Node implements Comparable<Node>{
		int s, e, cnt;
		public Node(int s, int e, int cnt) {
			this.s = s;
			this.e = e;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Node o) {
			if(this.s == o.s) return this.e - o.e;
			else return this.s - o.s;
		}
	}
}
