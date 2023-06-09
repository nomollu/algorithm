public class Pk진수에서소수개수구하기 {

	static String converted = ""; // k진수로 바꾼 수
	
	public static void main(String[] args) {
		System.out.println(solution(49, 10));
	}

	static public int solution(int n, int k) {
        int answer = 0;
        converted = toKNum(n, k);
        int len = converted.length();
        boolean flag = false; // 소수인지 판별중
        String curNum = ""; // 소수인지 판별할 수
        
        for(int i=0; i<len; i++) {
        	char c = converted.charAt(i);
        	
        	if(c != '0') {
        		curNum += c;
        		if(!flag) flag = true;
        		if(i == len - 1) {
        			long parsed = Long.parseLong(curNum);
        			if(isPrime(parsed)) answer++;
        		}
        	}else {
        		if(flag) {
        			long parsed = Long.parseLong(curNum);
        			if(isPrime(parsed)) answer++;
        			curNum = "";
        			flag = false;
        		}
        	}
        }
        
        return answer;
    }
	
	// n을 k진수로 바꾼 수를 리턴
	static String toKNum(int n, int k) {
		String cur = "";
		
		while(true) {
			int quot = n / k;
			int remain = n % k;
			cur = remain + cur;
			
			if(quot == 0) break;
			
			n = quot;
		}
		
		return cur;
	}
	
	static boolean isPrime(long n) {
		if(n <= 1) return false;
		if(n == 2 || n == 3) return true;
		for(long i=2; i<=Math.sqrt(n); i++) if(n % i == 0) return false;
		return true;
	}
}
