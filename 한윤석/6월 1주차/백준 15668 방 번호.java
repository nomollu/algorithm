public class B15668_방번호 {

	static String N;
	static int len;
	static boolean check [] = new boolean [10];
	static String ans = "-1";
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.next();
		len = N.length();
		
		backtrack("","",0,0);
		
		System.out.println(ans);
	}
	
	static boolean backtrack(String l, String r, int li, int ri) {
		// 왼쪽, 오른쪽 식 다 만들었으면
		if(li == len && ri == len) {
			// 빈 식이거나, 0인 경우 제외
			if(l.equals("") || r.equals("") || l.equals("0") || r.equals("0"))return false;
			
			int sum = Integer.parseInt(l) + Integer.parseInt(r);
			
			if(Integer.parseInt(N) == sum) {
				ans = l + " + " + r;
				return true;
			}
			
			return false;
		}
		
		//왼쪽식 아직 안 만들어졌으면
		if(li != len) {
			for(int i=0; i<10; i++) {
				if(check[i]) continue;
				
				check[i] = true;
				if(backtrack(l + i, r, li+1, ri)) return true;
				check[i] = false;
			}
			if(li == len - 1) backtrack(l, r, li+1, ri); // 이번 인덱스에 아무 수도 안 넣는 경우도 해봄
		}
		// 왼쪽식 다 만들어졌으면
		else {
			for(int i=0; i<10; i++) {
				if(check[i]) continue;
				
				check[i] = true;
				if(backtrack(l, r+i, li, ri+i)) return true;
				check[i] = false;
			}
			if(ri == len - 1) backtrack(l, r, li, ri+1);
		}
		
		return false;
	}
	
}
