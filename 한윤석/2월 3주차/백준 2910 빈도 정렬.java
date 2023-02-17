public class B2910_빈도정렬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Map<Integer, Num> map = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			int n = Integer.parseInt(st.nextToken());
			
			if(map.containsKey(n)) {
				Num num = map.get(n);
				num.cnt++;
				map.put(n, num);
			}else map.put((Integer)n, new Num(n, map.size(), 1));
		}
		
		Set<Integer> keys = map.keySet();
		List<Num> values = new LinkedList<>();
		for(int i:keys) values.add(map.get(i));
		Collections.sort(values);
		
		for(Num n : values) {
			for(int j=0; j<n.cnt; j++) sb.append(n.num).append(" ");
		}
		
		System.out.println(sb);
	}

	static class Num implements Comparable<Num>{
		int num, seq, cnt;
		public Num(int num, int seq, int cnt) {
			this.num = num;
			this.seq = seq;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Num o) {
			if(this.cnt == o.cnt) return this.seq - o.seq;
			return o.cnt - this.cnt;
		}
	}
}
