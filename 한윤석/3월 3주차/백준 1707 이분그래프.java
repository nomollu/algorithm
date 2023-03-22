public class B1707_이분그래프 {
	
	static int V,E;
	static List<Integer> edges [];
	static boolean visit[]; //방문여부
	static int colors []; //칠한 색깔

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edges = new LinkedList[V+1];
			visit = new boolean[V+1];
			colors = new int [V+1];
			
			for(int i=1; i<=V; i++) edges[i] = new LinkedList<>();
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				edges[u].add(v);
				edges[v].add(u);
			}

			boolean flag = true;
			
			//그래프가 2개 이상일 수도 있어서 모든 노드에 대해서 검사해봐야 함
			for(int i=1; i<=V; i++) {
				if(visit[i]) continue;
				
				visit[i] = true;
				colors[i] = 1;
				
				if(!checkCycle(i, -1, -1)) {
					flag = false;
					break;
				}
			}
			
			if(flag) System.out.println("YES");
			else System.out.println("NO");
		}
	}

	static boolean checkCycle(int n, int prev, int color) {
		for(int next : edges[n]) {
			//이전과 같은 노드면
			if(next == prev) continue;
			//다음 컬러가 내 컬러와 같으면 이분 그래프가 안됨
			if(colors[next] != 0 && colors[next] == colors[n]) return false;
			// 다음이 내 자신이면
			if(n == next) return false;
			//그래프 안되는지 여부 다 체크 후, 방문체크를 맨 마지막에 해줘야 함
			if(visit[next]) continue;
			
			colors[next] = color;
			visit[next] = true;
			if(!checkCycle(next, n, color * -1)) return false;
		}
		
		return true;
	}
}
