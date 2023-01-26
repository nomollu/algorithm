public class B14650_걷다보니신천역삼small {

	static int select [];
	static int ans = 0;
	static int N;
  
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		select = new int [N];
		
		comb(0);
		System.out.println(ans);
	}
	
	static void comb(int cnt) {
		if(cnt == N) {
			if(select[0] == 0) return;
			
			int cur = 0;
			for(int i=0; i<N; i++) cur += select[i];
			
			if(cur % 3 == 0) ans++;
			
			return;
		}
		
		for(int i=0; i<=2; i++) {
			select[cnt] = i;
			comb(cnt+1);
		}
	}
}
