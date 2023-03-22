public class B9489_사촌 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Map<Integer, List<Node>> cnt = new HashMap<>(); //{key} depth의 노드들 list
			
			int ans = 0;
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int depth = 0; //현재 트리의 depth
			int kdepth = 0; //k의 부모노드가 있는 depth
			int kparentIdx = 0; // k의 부모노드가 있는 depth에서 idx
			int prev = -1; //바로 직전 번호
			int shiftCnt = 1; //한 depth에서 부모 전환된 수
			int maxShiftCnt = 1; //현재 depth에서 최대 부모 전환 가능 수
			
			if(n == 0 && k == 0) break;
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<n; i++) {
				int cur = Integer.parseInt(st.nextToken());

				
				List<Node> init = new LinkedList<>();
				
				//루트노드 
				if(i == 0) {
					init.add(new Node(cur, cur));
					cnt.put(depth, init);
				}
				// 연속된 수의 집합이 아닌 경우
				else if(cur - 1 > prev) {
					int curDepthCnt = cnt.get(depth).size();

					// 넘어갈 수 있는 부모 노드가 없는 경우 -> 다음 depth로 넘김
					if(maxShiftCnt <= shiftCnt) {
						init.add(new Node(cur, cnt.get(depth).get(0).n));
//					System.out.println(cur + " "+ depth + " " + cnt.get(depth).get(0).n + " "  + curDepthCnt + " " + maxShiftCnt + " " + shiftCnt);
						cnt.put(++depth, init);
						maxShiftCnt = curDepthCnt;
						shiftCnt = 1;
					}
					// 넘어갈 수 있는 부모 노드 있으면 -> 다음 부모의 아래로 들어감
					else {
						List<Node> depthList = cnt.get(depth);
						depthList.add(new Node(cur, cnt.get(depth-1).get(shiftCnt).n));
						cnt.put(depth, depthList);
						shiftCnt++;
					}
				}
				//연속된 수의 집합의 경우
				else if(cur - 1 == prev) {
					List<Node> depthList = cnt.get(depth);
					depthList.add(new Node(cur,depthList.get(depthList.size()-1).parent));
					cnt.put(depth, depthList);
				}
//				System.out.println(cur + " " + depth + " " + maxShiftCnt + " " + shiftCnt );
				
				if(cur == k) {
					kdepth = depth-1;
					kparentIdx = shiftCnt-1;
					if(i ==0) {
						kdepth = 0;
						kparentIdx = 0;
					}
				}
				
				prev = cur;
			}
			
			Node KP = cnt.get(kdepth).get(kparentIdx); //K의 부모 노드
			if(cnt.containsKey(kdepth+1)) {
				for(Node node : cnt.get(kdepth+1)) {
					if(node.parent != KP.n) {
						ans++;
					}
				}
				
			}
			
			System.out.println(ans);
		}
	}
	
	static class Node{
		int parent;
		int n;
		public Node(int n, int parent) {
			this.n = n;
			this.parent = parent;
		}
	}
}
