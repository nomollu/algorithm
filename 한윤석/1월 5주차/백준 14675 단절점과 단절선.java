public class B14675_단절점과단절선 {
	
	static int N;
	static int edgeList [][];
	static List<Integer> edge [];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		edge = new LinkedList[N+1];
		edgeList = new int [N][2];
		
		for(int i=1; i<=N; i++) edge[i] = new LinkedList<>();
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			edge[end].add(start);
			edge[start].add(end);
			edgeList[i+1][0] = start;
			edgeList[i+1][1] = end;
		}
		
		int q = Integer.parseInt(br.readLine());
		
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			if(t == 1) {
				if(isCutVertex(k)) System.out.println("yes");
				else System.out.println("no");
			}
			else {
				if(isBridge(k)) System.out.println("yes");
				else System.out.println("no");
			}
		}
	}
	
	static boolean isCutVertex(int node) {
		boolean check [] = new boolean [N+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(node == 1 ? 2 : 1);
		check[node] = true;
		
		while(!q.isEmpty()) {
			int n = q.poll();
			for(Integer i : edge[n]) {
				if(check[i]) continue;
				
				check[i] = true;
				q.add(i); 
			}
		}

		for(int i=1; i<=N; i++) if(!check[i]) return true;
		
		return false;
	}
	
	static boolean isBridge(int idx) {
		boolean check [] = new boolean [N+1];
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(edgeList[idx][0] != i && edgeList[idx][1] != i) {
				q.add(i);
				break;
			}
		}
		check[q.peek()] = true;
		
		while(!q.isEmpty()) {
			int n = q.poll();
			
			for(Integer i : edge[n]) {
				if(check[i] || (n == edgeList[idx][0] && i == edgeList[idx][1]) || (n == edgeList[idx][1] && i == edgeList[idx][0])) continue;
				
				check[i] = true;
				q.add(i); 
			}
		}
		
		for(int i=1; i<=N; i++) if(!check[i]) return true;
		
		return false;
	}
	
}
