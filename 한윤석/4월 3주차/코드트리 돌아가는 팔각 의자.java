public class C_돌아가는팔각의자 {

	static char chairs [][] = new char [5][9];
	static int cidx, dir;
	static int isTurn [] = new int [5];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=1; i<=4; i++) {
			String input = br.readLine();
			for(int j=1; j<=8; j++) chairs[i][j] = input.charAt(j-1);
		}
		int k = Integer.parseInt(br.readLine());
		
		while(k-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cidx = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			solve();
		}
		findAnswer();
		
	}
	
	static void solve() {
		for(int i=1; i<=4; i++) isTurn[i] = 0;
		
		isTurn[cidx] = dir;
		findTurnChairs(true, cidx-1, dir * -1);
		findTurnChairs(false, cidx+1, dir * -1);
		checkTurns();
	}
	
	/*@params
	 * isLeft : 기준 왼쪽 테이블을 보는 건
	 * chairidx : 현재 내가 몇번째 테이블인지 
	 * nd : 이번에 내가 어느방향으로 도는지 
	 * */
	static void findTurnChairs(boolean isLeft, int chairIdx, int nd) {
		if(chairIdx <= 0 || chairIdx > 4) return;
		
		int myIdx = isLeft ? 3 : 7;
		int compareIdx = isLeft ? 7 : 3;
		int prevChairIdx = isLeft ? chairIdx + 1 : chairIdx - 1;
		int nextChairIdx = isLeft ? chairIdx - 1 : chairIdx + 1;
		
		if(chairs[chairIdx][myIdx] == chairs[prevChairIdx][compareIdx]) {
			isTurn[chairIdx] = 0;
			return;
		}
		else {
			isTurn[chairIdx] = nd;
			findTurnChairs(isLeft, nextChairIdx, nd * -1);
		}
	}
	
	static void checkTurns() {
		for(int i=1; i<=4; i++) {
			
			if(isTurn[i] == 0) continue;
			else turn(i, isTurn[i]);
		}
	}
	
	static void turn(int idx, int d) {
		if(d == 1) {
			char temp = chairs[idx][8];
			for(int i=1; i<=8; i++){
				char temp2 = chairs[idx][i];
				chairs[idx][i] = temp;
				temp = temp2;
			}
		}else {
			char temp = chairs[idx][1];
			for(int i=8; i>0; i--){
				char temp2 = chairs[idx][i];
				chairs[idx][i] = temp;
				temp = temp2;
			}
		}
	}
	
	static void findAnswer() {
		int ans = 0;
		for(int i=1; i<=4; i++) if(chairs[i][1] == '1') ans+= Math.pow(2, i-1);
		System.out.println(ans);
	}
}
