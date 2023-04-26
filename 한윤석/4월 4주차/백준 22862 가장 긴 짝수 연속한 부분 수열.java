public class B22862_가장긴짝수연속한부분수열large {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int nums [] = new int [N];
		int s = 0; //시작인덱스
		int e = 0; //끝 인덱스
		int cnt = 0; //s부터 e사이의 홀수 개수
		int ans = 0;
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) nums[i] = Integer.parseInt(st.nextToken());

		if(nums[0] % 2 != 0) cnt++;
		
		while(s <= e) {
			if(cnt <= K) { //s 부터 e까지의 홀수 개수가 K개 이하이면
				ans = Math.max(ans, e - s + 1 - cnt);
				if(e + 1 < N) { //맨 마지막 인덱스가 아니면 계속 끝 인덱스 증가 시킴
					e++;
					if(nums[e] % 2 != 0) cnt++;
				}else { //e가 이미 맨 마지막 인덱스이면 s를 뒤로 당김
					if(nums[s] % 2 != 0) cnt--;
					s++;
				}
			}else {//s 부터 e까지의 홀수 개수가 K개 초과이면
				if(nums[s] % 2 != 0) cnt--; // 현재 s 자리가 홀수이면 홀수 개수 -1
				s++; 
			}
		}
		System.out.println(ans);
	}
}
