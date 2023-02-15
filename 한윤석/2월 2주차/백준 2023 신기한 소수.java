public class B2023_신기한소수 {
	
	static int N;
	static StringBuilder sb = new StringBuilder();
	static int heads [] = {2,3,5,7}; //신기한 소수의 맨 앞자리는 2357뿐

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		for(int i=0; i<4; i++) dfs(Integer.toString(heads[i]));
		
		System.out.println(sb);
	}
	
	static void dfs(String s) {
		if(s.length() == N) {
			sb.append(s).append("\n");
			return;
		}
		
		for(int i=1; i<=9; i++) {
			if(isPrime(Integer.parseInt(s+i))) dfs(s + i);
		}
	}
	
	static boolean isPrime(int n) {
		if(n == 1) return false;
		for(int i=2; i<=Math.sqrt(n); i++) {
			if(n%i == 0) return false;
		}
		return true;
	}
}
