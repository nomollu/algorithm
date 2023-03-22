public class B6588_골드바흐의추측 {
	static boolean isPrime[] = new boolean [1000001];
	static List<Integer> primes = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		init();
		StringBuilder sb = new StringBuilder();
		while(true) {
			int N = sc.nextInt();
			
			if(N == 0) break;
			
			String ans = "Goldbach's conjecture is wrong.";
			
			for(int p : primes) {
				if(isPrime[N-p]) {
					ans = N + " = " + p + " + " + (N-p);
					break;
				}
			}
			
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static void init() {
		for(int i=2; i<=1000000; i++) {
			boolean flag = true;
			for(int j=2; j<=Math.sqrt(i); j++) {
				if(i % j == 0) {
					flag = false;
					break;
				}
			}
			if(flag) {
				isPrime[i] = true;
				primes.add(i);
			}
		}
	}
}
