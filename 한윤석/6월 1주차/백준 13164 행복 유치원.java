public class B13164_행복유치원 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<Div> gaps = new ArrayList<>(); // 뒷 수와의 차이들 
		ArrayList<Div> selected = new ArrayList<>(); // 뒷 수와의 차이들 중 가장 큰 K-1개 뽑은 것
		int [] h = new int [N]; // input 
		int idx = 0; // selected에서 현재 사용중인 구간
		int div = 0; // 구간 시작점
		int ans = 0; 
		st = new StringTokenizer(br.readLine());
		
		// 입력받으면서 뒷 수와의 차이를 gaps에 저장해둠
		for(int i=0; i<N; i++) {
			h[i] = Integer.parseInt(st.nextToken());
			if(i > 0) gaps.add(new Div(i-1, h[i] - h[i-1]));
		}
		
		Collections.sort(gaps, cp); // 뒷 수와의 차이 큰 순서로 내림차순
		// K개로 나누어야 하므로 구간은 K-1개가 됨
		for(int i=0; i<K-1; i++) selected.add(gaps.get(i));
		
		// 구간을 인덱스 순으로 오름차순
		Collections.sort(selected, cp2);
		
		for(int i=0; i<N; i++) {
			if(idx < K-1) { // 아직 선택할 구간 남았으면
				Div d = selected.get(idx);
				if(d.i == i) { // 현재 인덱스가 내가 나누어야 할 구간이라면
					ans += h[d.i] - h[div]; //차잇값만큼 정답에 더하고
					div = i+1; // 구간의 시작점을 현재로 갱신해주고
					idx++; // 사용할 구간 인덱스 증가시켜줌
				}
			}else if(i == N-1) ans += h[i] - h[div];
		}
		
		System.out.println(ans);
	}
	
	// h[i] - h[i-1] 의 차잇값으로 정렬 위함
	static Comparator<Div> cp = new Comparator<Div>() {
		public int compare(Div o1, Div o2) {
			return o2.g - o1.g;
		};
	};
	
	// 선택된 분할 구간을 인덱스별로 정렬 위함
	static Comparator<Div> cp2 = new Comparator<Div>() {
		public int compare(Div o1, Div o2) {
			return o1.i - o2.i;
		};
	};
	
	static class Div{
		int i, g; // 인덱스, 뒷 번호와의 차이
		public Div(int i, int g) {
			this.i = i;
			this.g = g;
		}
	}
}
