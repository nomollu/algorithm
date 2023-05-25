public class B1027_고층건물 {

	static double [] buildings; // i번 빌딩의 높이
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int canSee [] = new int [N+1]; // i번 빌딩에서 볼 수 있는 빌딩 수
		int ans = 0;
		buildings = new double [N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++) buildings[i] = Double.parseDouble(st.nextToken());
		
		for(int i=1; i<=N; i++) {
			// i번과 j를 잇고
			for(int j=i+1; j<=N; j++) {
				if(i == j) continue;
				
				// 바로 옆 빌딩이면 무조건 서로를 볼 수 있음
				if(j - i == 1) {
					canSee[j]++;
					canSee[i]++;
					continue;
				}
				
				boolean flag = true; // i부터 j사이에 방해되는 빌딩 있나?
				// i, j 사이에 접하지 않는 건물이 몇 개 인지
				for(int k=i+1; k<j; k++) {
					// 방해되는 건물 있으면 종료시킴
					if(isContact(i, j, k)) {
						flag = false;
						break;
					}
				}
				
				// 방해되는 건물 없으면 서로를 볼 수 있음
				if(flag) {
					canSee[i]++;
					canSee[j]++;
				}
			}
		}
		
		for(int i=1; i<=N; i++) ans = Math.max(ans, canSee[i]);
		System.out.println(ans);
	}
	
	static boolean isContact(int i, int j, int k) {
		// 기울기 
		double m = (buildings[i] - buildings[j]) / (i - j); 
		// 직선의 방정식을 y = m(x-x1) + y1 의 형태로 풀었을 때, 이를 풀면
		// y = mx - mx1 + y1이 되는데, 여기서 mx1 + y1을 계산한 값 
		double right = (m * i * -1) + buildings[i];
		
		// [k, buildings[k]] 가 직선의 방정식 값보다 크면 방해되는 것임
		if(buildings[k] >= (m * k) + right) return true;
		return false;
	}
}
