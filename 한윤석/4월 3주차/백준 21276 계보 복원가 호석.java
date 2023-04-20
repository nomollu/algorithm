public class B21276_계보복원가호석 {

	static int N, M; //사람 수, 정보 개수
	static List<String> names = new LinkedList<>();
	static Map<String, Integer> nameToIdx = new HashMap<>(); 
	static Map<Integer, String> idxToName = new HashMap<>();
	static List<Integer> children [];
	static int depth [];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		depth = new int [N];
		M = Integer.parseInt(br.readLine());
		children = new LinkedList[N];
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			String name = st.nextToken();
			children[i] = new LinkedList<>();
			nameToIdx.put(name, i);
			idxToName.put(i, name);
			names.add(name);
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent = st.nextToken();
			int childIdx = nameToIdx.get(child);
			int parentIdx = nameToIdx.get(parent);

			depth[childIdx]++;
			children[parentIdx].add(childIdx);
		}
		
		Collections.sort(names);
		
		List<String> ancestor = new LinkedList<>();
		for(String s : names) {
			int idx = nameToIdx.get(s);
			if(children[idx].size() == 0) ancestor.add(s);
			System.out.print(s + " = ");
			for(int i : children[idx]) System.out.print(idxToName.get(i) + " ");
			System.out.println();
		}
		
		//가문 수 출력
		sb.append(ancestor.size()).append("\n");
		//선조 이름 출력
		for(String s : ancestor) sb.append(s).append(" ");
		sb.append("\n");
		//사람 이름과 자식
		for(String s : names) {
			sb.append(s).append(" ");
		}
	}
}
