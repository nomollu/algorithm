public class B2661_좋은수열 {
	
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		backtrack("1");
	}

	static boolean backtrack(String s) {
		if(s.length() == N) {
			System.out.println(s);
			return true;
		}
		
		for(int i=1; i<=3; i++) {
			if((s.charAt(s.length()-1) - '0') == i) continue;
			
			if(!isValid(s, i)) continue;
			if(backtrack(s+Integer.toString(i))) return true;
		}
		
		return false;
	}
	
	static boolean isValid(String s, int num) {
		s += num;
		for(int i=0; i<s.length()-1; i++) { // 시작점
			for(int j=1; j<=s.length()/2; j++) { //길이
				if(i + j + j > s.length()) continue;
				
				String standard = s.substring(i, i+j);
				String compare = s.substring(i+j, i+j+j);
				if(standard.equals(compare)) return false;
			}
		}
		
		return true;
	}
}
