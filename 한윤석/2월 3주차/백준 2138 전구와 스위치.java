public class B2138_전구와스위치 {

	static int N;
	static char target []; //목표 형태
	static char bulbs[][]; //바꿀 전구 [0]:0번 인덱스 스위치를 누르지 않고 시작한 상태, [1] : 0번 인덱스 스위치를 누르고 시작한 상태
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		target = sc.next().toCharArray();
		String input = sc.next();
		bulbs = new char[2][N];
		bulbs[0] = input.toCharArray();
		bulbs[1] = input.toCharArray();
		
		solve(1, 0, 0); //0번 스위치 누르지 않고 시작
    
    //0번 스위치 누르고 시작
		bulbs[1][0] = bulbs[1][0] == '0' ? '1' : '0';
		bulbs[1][1] = bulbs[1][1] == '0' ? '1' : '0';
		solve(1, 1, 1);
		
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	
	static void solve(int idx, int cnt, int bi) {
		if(idx == N) {
			for(int i=0; i<N; i++) if(bulbs[bi][i] != target[i]) return;
			ans = Math.min(cnt, ans);
			return;
		}
		
    //idx-1번째 전구가 타겟과 같을때
		if(bulbs[bi][idx-1] == target[idx-1]) {
			solve(idx+1, cnt, bi);
		}else {
			for(int i=idx-1; i<=idx+1; i++) {
				if(i == N) continue;
				bulbs[bi][i] = bulbs[bi][i] == '0' ? '1' : '0';
			}
			solve(idx+1, cnt+1, bi);
		}
	}
}
