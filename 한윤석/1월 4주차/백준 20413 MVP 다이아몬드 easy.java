public class B20413_MVP다이아몬드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int level [] = new int [4]; // S,G,P,D 의 기준치
		int memo [] = new int [N]; //i번째에 얼마를 썼는지
		int ans = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Character, Integer> idx = new HashMap<>(); // S,G,P,D가 level의 몇 번째 인덱스인지
		
		idx.put('B', 0);
		for(int i=0; i<4; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(i == 0) {
				idx.put('S', 1); 
				level[0] = num;
			}
			else if(i == 1) {
				idx.put('G', 2);
				level[1] = num;
			}
			else if(i == 2) {
				idx.put('P', 3);
				level[2] = num;
			}
			else if(i == 3) {
				idx.put('D', 4);
				level[3] = num;
			}
		}
		
		char [] bsgpd = br.readLine().toCharArray();
		
		for(int i=0; i<N; i++) {
			char c= bsgpd[i]; //이번 달의 등급
			int cIdx = idx.get(c); //해당 달이 level에서 몇 번 인덱스인가
			
			if(c == 'D') {
				memo[i] = level[3]; //다이아 등급만큼 쓰는게 최대
				ans += level[3];
			}else {
				memo[i] = level[cIdx]-1; //B라면 S의 기준치 -1이 최대
				if(i == 0) {
					ans += memo[i];
				}
				else {
					memo[i] = level[cIdx] - memo[i-1] - 1;
					ans += memo[i];
				}
			}
		}
		
		System.out.println(ans);
	}
}

