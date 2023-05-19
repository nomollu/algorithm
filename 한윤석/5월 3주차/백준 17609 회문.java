public class B17609_회문 {

	static String s; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			s = br.readLine();
			int l = 0;
			int r = s.length()-1;
			
			if(isPalindrome(l, r)) System.out.println(0);
			else if(isSimilarPalindrome(l, r)) System.out.println(1);
			else System.out.println(2);
		}
	}
	
	static boolean isPalindrome(int l, int r) {
		while(l <= r) {
			char a = s.charAt(l);
			char b = s.charAt(r);
			
			if(a != b) return false;
			
			l++;
			r--;
		}
		return true;
	}
	
	static boolean isSimilarPalindrome(int l, int r) {
		while(l <= r) {
			char a = s.charAt(l);
			char b = s.charAt(r);
			
			if(a != b) {
				boolean ac = isPalindrome(l+1, r);
				boolean bc = isPalindrome(l, r-1);
				
				if(!ac && !bc) return false;
				else return true;
			}
			l++;
			r--;
		}
		return true;
	}
	
}
