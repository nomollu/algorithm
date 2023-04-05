public class B1735_분수합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int bj1 = sc.nextInt();
		int bm1 = sc.nextInt();
		int bj2 = sc.nextInt();
		int bm2 = sc.nextInt();
		int bj = bj1 * bm2 + bj2 * bm1;
		int bm = bm1 * bm2;
		int gcd = GCD(bm, bj);
		
		System.out.println(bj/gcd + " " + bm/gcd);
	}
	
	static int GCD(int a, int b) {
		if(a % b == 0) return b;
		return GCD(b, a%b);
	}
}
