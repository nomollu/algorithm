public class B20436_ZOAC3 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char left = sc.next().charAt(0);
		char right = sc.next().charAt(0);
		String s = sc.next(); 
		int [] hand = {1,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,1,1,1,0,1};
		int [][] pos = {{1,0}, {2,4},{2,2},{1,2},{0,2},{1,3},{1,4},{1,5},{0,7},{1,6},{1,7},{1,8},{2,6},{2,5},{0,8},{0,9},{0,0},{0,3},{1,1},{0,4},{0,6},{2,3},{0,1},{2,1},{0,5},{2,0}};
		int ans = 0;
		
		for(int i=0; i<s.length(); i++) {
			char cur = s.charAt(i);
			int lIdx = left - 'a';
			int rIdx = right - 'a';
			int cIdx = cur - 'a';
			
			if(hand[cIdx] == 1) {
				ans += Math.abs(pos[lIdx][0] - pos[cIdx][0]) + Math.abs(pos[lIdx][1] - pos[cIdx][1]) + 1;
				left = cur;
			}else {
				ans += Math.abs(pos[rIdx][0] - pos[cIdx][0]) + Math.abs(pos[rIdx][1] - pos[cIdx][1]) + 1;
				right = cur;
			}
			
		}
		
		System.out.println(ans);
	}
}
